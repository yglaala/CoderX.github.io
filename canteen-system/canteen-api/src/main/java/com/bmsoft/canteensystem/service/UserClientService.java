package com.bmsoft.canteensystem.service;
/**
 * USER-PROVIDER 服务接口
 * @Author liugaoyang
 * 将USER-PROVIDER 服务已接口形式暴露出来
 */

import com.bmsoft.canteensystem.entity.Emp;
import com.bmsoft.canteensystem.entity.User;
import com.bmsoft.canteensystem.util.Msg;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "USER-PROVIDER",fallbackFactory = UserClientServiceFallbackFactory.class)
public interface UserClientService {

    /**
     * 登录
     * @param empNo 员工号
     * @param userPwd 密码
     * @return
     */
    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public Msg login(@RequestParam("empNo") String empNo, @RequestParam("userPwd") String userPwd);

    /**
     * 注册
     * 需要通过了注册验证
     * @param user 获取用户信息：这里主要获取userPwd 密码
     * @param empNo 员工号
     * @return
     */
    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    public Msg register(@RequestBody User user, @RequestParam("empNo") String empNo);

    /**
     * 注册验证
     * 只有通过了验证注册才能注册
     * @param emp 获取员工信息：主要是empNo 员工号
     * @param base64Data 身份证图片base64码
     * @param snapData 人脸图片base64码
     * @return
     */
    @RequestMapping(value = "/user/registervalidate",method = RequestMethod.POST)
    public Msg registerValidate(@RequestBody Emp emp, @RequestParam("base64Data") String base64Data, @RequestParam("snapData") String snapData);

    /**
     * 注销
     * 释放session
     * @return
     */
    @RequestMapping("/user/loginout")
    public Msg loginOut();
}
