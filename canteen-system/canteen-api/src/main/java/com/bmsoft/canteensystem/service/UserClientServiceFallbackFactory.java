package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.entity.Emp;
import com.bmsoft.canteensystem.entity.User;
import com.bmsoft.canteensystem.util.Msg;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Author liugaoyang
 * @Date 2018/10/23 17:35
 */
@Component
public class UserClientServiceFallbackFactory implements FallbackFactory<UserClientService> {
    @Override
    public UserClientService create(Throwable throwable) {
        return new UserClientService() {
            @Override
            public Msg login(String empNo, String userPwd) {
                return Msg.fail().setMsg("服务器出错");
            }

            @Override
            public Msg register(User user, String empNo) {
                return Msg.fail().setMsg("服务器出错");
            }

            @Override
            public Msg registerValidate(Emp emp, String base64Data, String snapData) {
                return Msg.fail().setMsg("服务器出错");
            }

            @Override
            public Msg loginOut() {
                return Msg.fail().setMsg("服务器出错");
            }
        };
    }
}
