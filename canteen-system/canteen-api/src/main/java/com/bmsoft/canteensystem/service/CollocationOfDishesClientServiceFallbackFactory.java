package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.entity.Shop;
import com.bmsoft.canteensystem.util.Msg;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author liugaoyang
 * @Date 2018/10/23 17:32
 */
@Component
public class CollocationOfDishesClientServiceFallbackFactory implements FallbackFactory<CollocationOfDishesClientService> {
    @Override
    public CollocationOfDishesClientService create(Throwable throwable) {
        return new CollocationOfDishesClientService() {
            @Override
            public Msg addMatchMenu(String jsonMatchMenus, boolean seven) {
                return Msg.fail().setMsg("服务器出错");
            }

            @Override
            public Msg delMatchMenu(Integer matchMenuId, boolean seven) {
                return Msg.fail().setMsg("服务器出错");
            }

            @Override
            public Msg updateMatchMenuNum(Integer matchMenuId, Integer matchMenuNum, boolean seven) {
                return Msg.fail().setMsg("服务器出错");
            }

            @Override
            public Msg queryMenu(String menuName) {
                return Msg.fail().setMsg("服务器出错");
            }

            @Override
            public Msg menuList(Shop userShopInfo) {
                return Msg.fail().setMsg("服务器出错");
            }

            @Override
            public Msg shopList() {
                return Msg.fail().setMsg("服务器出错");
            }

            @Override
            public Msg listOfDishes(Integer shopId, Date matchMenuDate) {
                return Msg.fail().setMsg("服务器出错");
            }
        };
    }
}
