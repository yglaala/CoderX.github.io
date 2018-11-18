package com.bmsoft.canteensystem.service;
/**
 * USER-PROVIDER   UserService层
 * @Author liugaoyang
 */

import com.bmsoft.canteensystem.entity.User;
import com.bmsoft.canteensystem.util.Msg;

public interface UserService {

    //注册逻辑接口
    public Msg register(User user, String empNo);

    //登录逻辑接口
    public Msg login(String empNo,String userPwd);
}
