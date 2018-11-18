package com.bmsoft.canteensystem.controller;
/**
 * USER-CONSUMER   contronller层
 * @Author liugaoyang
 * 调用公共api的service接口调用相关服务提供者
 */

import com.bmsoft.canteensystem.entity.Emp;
import com.bmsoft.canteensystem.entity.Shop;
import com.bmsoft.canteensystem.entity.User;
import com.bmsoft.canteensystem.service.UserClientService;
import com.bmsoft.canteensystem.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

@RestController
public class UserController_Consumer {
    @Autowired
    private UserClientService userClientService;

    @Autowired
    HttpServletRequest request;

    private Msg msg;

    /**
     * 登录
     * @param empNo 员工号
     * @param userPwd 密码
     * @return
     */
    @RequestMapping(value = "/user/login")
    public Msg login(String empNo,String userPwd){
        msg = userClientService.login(empNo,userPwd);
        //判断用户信息是否验证成功
        if (msg.getCode() == 100){
            //将用户信息保存到session
            HttpSession session = request.getSession();
            session.setAttribute("userInfo",msg.getInfo().get("userInfo"));
            session.setAttribute("userShopInfo",msg.getInfo().get("userShopInfo"));
            System.out.println(session.getAttribute("userInfo").toString());
        }
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
    public Msg register(User user,String empNo){
        return userClientService.register(user,empNo);
    }

    /**
     * 注册验证
     * 只有通过了验证注册才能注册
     * @param emp 获取员工信息：主要是empNo 员工号
     * @param base64Data 身份证图片base64码
     * @param snapData 人脸图片base64码
     * @return
     */
    @RequestMapping(value = "/user/registervalidate",method = RequestMethod.POST)
    public Msg registerValidate(Emp emp, String base64Data,String snapData){
        return userClientService.registerValidate(emp,base64Data,snapData);
    }

    /**
     * 注销
     * 释放session
     * @return
     */
    @RequestMapping(value = "/user/loginout")
    public Msg loginOut(){
        HttpSession session = request.getSession();
        //释放session
        session.invalidate();
        msg = Msg.success().setMsg("注销成功");
        return msg;
    }

    /**
     * 登录拦截
     * 判断session是否存在
     * @return
     */
    @RequestMapping(value = "/user/issession")
    public Msg isSession(){
        HttpSession session = request.getSession();
//        LinkedHashMap userInfoMap = (LinkedHashMap)session.getAttribute("userInfo");
//        User userInfo = (User) userInfoMap.get("userInfo");
        if (session.getAttribute("userInfo") == null){
            msg = Msg.fail().setMsg("未登录，请先登录");
            return msg;
        }
//        LinkedHashMap userShopInfoMap = (LinkedHashMap)session.getAttribute("userShopInfo");
//        Shop userShopInfo = new Shop().setShopId((Integer) userShopInfoMap.get("shopId"));
        msg = Msg.success().setMsg("已登录")/*.add("userInfo",userInfo).add("userShopInfo",userShopInfo)*/;
        return msg;
    }
}
