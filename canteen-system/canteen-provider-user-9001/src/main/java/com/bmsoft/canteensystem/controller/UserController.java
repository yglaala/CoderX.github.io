package com.bmsoft.canteensystem.controller;
/**
 * USER-PROVIDER   contronller层
 * @Author liugaoyang
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bmsoft.canteensystem.entity.Emp;
import com.bmsoft.canteensystem.entity.User;
import com.bmsoft.canteensystem.service.EmpService;
import com.bmsoft.canteensystem.service.UserService;
import com.bmsoft.canteensystem.util.Constants;
import com.bmsoft.canteensystem.util.Msg;
import com.bmsoft.canteensystem.util.StringUtil;
import com.bmsoft.canteensystem.vo.IDCardInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmpService empService;
    private Msg msg;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    HttpServletRequest request;


    /**
     * 登录
     * @param empNo 员工号
     * @param userPwd 密码
     * @return
     */
    @RequestMapping("/user/login")
    public Msg login(String empNo,String userPwd){
        msg = userService.login(empNo,userPwd);
        return msg;
    }

    /**
     * 注销
     * 释放session
     * @return
     */
    @RequestMapping("/user/loginout")
    public Msg loginOut(){
        HttpSession session = request.getSession();
        //释放session
        session.invalidate();
        msg = Msg.success().setMsg("注销成功");
        return msg;
    }

    /**
     * 注册
     * 需要通过了注册验证
     * @param user 获取用户信息：这里主要获取userPwd 密码
     * @param empNo 员工号
     * @return
     */
    @RequestMapping(value = "/user/register")
    public Msg register(@RequestBody User user,String empNo){
        msg  = userService.register(user,empNo);
        return msg;
    }

    /**
     * 注册验证
     * 只有通过了验证注册才能注册
     * @param emp 获取员工信息：主要是empNo 员工号
     * @param base64Data 身份证图片base64码
     * @param snapData 人脸图片base64码
     * @return
     */
    @RequestMapping(value = "/user/registervalidate")
    public Msg registerValidate(@RequestBody Emp emp, String base64Data, String snapData){
//        System.out.println(base64Data);
        //通过员工号获取员工身份证号码
        String empNumberId = empService.getEmpNumberId(emp.getEmpNo());
        //通过员工号获取员工相片路径
        String empPicPath = empService.getPic(emp.getEmpNo());
        //判断是否取到相应的信息，都没有说明没有这个员工
        if (empNumberId==null&&empPicPath==null){
            return Msg.fail().setMsg("没有此员工，请检查员工号是否正确");
        }
        //调用身份证信息对比方法获取对比结果
        Msg idInfoMsg = checkIdInfo(base64Data,empNumberId);
        //调用人脸对比方法获取对比结果
        Msg faceMsg = checkFace(snapData,empPicPath);

        System.out.println(idInfoMsg.getCode());
        System.out.println(faceMsg.getCode());
        //如果身份信息不符合，返回不符的相关信息
        if (idInfoMsg.getCode() != 100){
            return idInfoMsg;
        }
        //如果人脸信息不符合，返回不符的相关信息
        if (faceMsg.getCode() != 100){
            return faceMsg;
        }
        //成功返回成功信息
        return Msg.success().setMsg("验证成功");
    }

    /**
     *调用身份证接口，提取信息判断返回结果
     * @param base64Data 身份证图片base64码
     * @param empNumberId 身份证号
     * @return
     */
    public Msg checkIdInfo(String base64Data,String empNumberId){
//        System.out.println(base64Data);
        MultiValueMap<String,String> postParameters=new LinkedMultiValueMap<>();
        postParameters.add("base64Data",base64Data);
        //调用身份证信息提取接口
        String jsonstr = restTemplate.postForObject(Constants.ADDRESS_GETFROMPIC,postParameters,String.class);
        //解析返回的json字符串
        String result_list = JSON.parseObject(jsonstr).getJSONArray("result_list").toString();
        String result_list2 = JSON.parseArray(result_list).getJSONObject(0).toString();
        String data = JSON.parseObject(result_list2).getJSONObject("data").toString();
        IDCardInfo userInfo = JSON.parseObject(data,new TypeReference<IDCardInfo>(){});
        //判断是否有信息
        if (userInfo==null){
            return Msg.fail().setMsg("身份图片部清晰，无法获取到信息");
        }
        //对比信息是否一致
        if (empNumberId.equals(userInfo.getId())){
            return Msg.success().setMsg("身份验证成功");
        }
        //信息不一致
        return Msg.fail().setMsg("身份不符");
    }

    /**
     * 调用人脸识别的接口，获取对比返回值将结果返回
     * @param snapData 人脸图片base64码
     * @param empPicPath  员工相片路径
     * @return
     */
    public Msg checkFace(String snapData,String empPicPath){

        MultiValueMap<String,String> postParameters=new LinkedMultiValueMap<>();
        postParameters.add("snapData",snapData);
        postParameters.add("urlB",empPicPath);

        //调用人脸识别接口，返回识别信息
        JSONObject result = JSONObject.parseObject(restTemplate.postForObject(Constants.ADDRESS_COMPARE,postParameters,String.class));
        System.out.println(result);
        //判断是否成功
        if(result.get("code").equals(0)){
            //解析json，获取相似度
            float similarity = StringUtil.strToFlo(JSONObject.parseObject(result.get("data").toString()).
                    get("similarity").toString());
            //判断相似度是否大于85，大于85为同一个人
            if(similarity > 75){
                return Msg.success().setMsg("人脸比对成功").add("info",result);
            }
            return Msg.fail().setMsg("人脸不符").add("info",result);
        }
        return Msg.fail().setCode(0).setMsg("比对出错").add("info",result);
    }

//    @RequestMapping("/get")
//    public Object set(HttpServletRequest request){
//        HttpSession session =request.getSession();
//        System.out.println(session.getAttribute("admin"));
//        return session.getAttribute("admin");
//
//    }

}
