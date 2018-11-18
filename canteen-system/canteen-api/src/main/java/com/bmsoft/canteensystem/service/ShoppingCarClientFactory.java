package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.entity.ShoppingCar;
import com.bmsoft.canteensystem.util.Msg;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author chengpeng
 * @create 2018-10-23 14:30
 */
@Component//必须添加组件注解
public class ShoppingCarClientFactory implements FallbackFactory<ShoppingCarClientService> {
    @Override
    public ShoppingCarClientService create(Throwable throwable) {
        return new ShoppingCarClientService() {
            @Override
            public Msg add(ShoppingCar shoppingCar) {
                return Msg.fail().setMsg("服务器错误！");
            }

            @Override
            public Msg delete(Integer shoppingCarId) {
                return Msg.fail().setMsg("服务器错误！");
            }

            @Override
            public Msg list(Integer userId) {
                return Msg.fail().setMsg("服务器错误！");
            }

            @Override
            public Msg update(ShoppingCar shoppingCar) {
                return Msg.fail().setMsg("服务器错误！");
            }

        };
    }
}
