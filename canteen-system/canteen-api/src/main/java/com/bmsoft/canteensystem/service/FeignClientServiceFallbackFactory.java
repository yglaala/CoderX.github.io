package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.entity.Menu;
import com.bmsoft.canteensystem.entity.Shop;
import com.bmsoft.canteensystem.util.Msg;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FeignClientServiceFallbackFactory  implements FallbackFactory<FeignClientService>{

    @Override
    public FeignClientService create(Throwable throwable) {
        return new FeignClientService() {
            @Override
            public Msg findByUserId(Integer id) {
                return Msg.fail().setMsg("服务错误");
            }

            @Override
            public Msg add(Shop shop) {
                return Msg.fail().setMsg("服务错误");
            }

            @Override
            public Msg update(Shop shop) {
                return Msg.fail().setMsg("服务错误");
            }

            @Override
            public Msg findByShopId(Integer shopId) {
                return Msg.fail().setMsg("服务错误");
            }

            @Override
            public Msg findAllMenuInfo() {
                return Msg.fail().setMsg("服务错误");
            }

            @Override
            public Msg findById(Integer menuId) {
                return Msg.fail().setMsg("服务错误");
            }

            @Override
            public Msg findAll(Integer pageNum, Integer pageSize, Menu menu) {
                return Msg.fail().setMsg("服务错误");
            }

            @Override
            public Msg add(String menu0, MultipartFile uploadPic) {
                return Msg.fail().setMsg("服务错误");
            }

            @Override
            public Msg update(String menu0, MultipartFile uploadPic) {
                return Msg.fail().setMsg("服务错误");
            }

            @Override
            public Msg update1(String menu0) {
                return Msg.fail().setMsg("服务错误");
            }

            @Override
            public Msg delete(String menuIds) {
                return Msg.fail().setMsg("服务错误");
            }

            @Override
            public Msg findAllmenuPropertiesCategoryId() {
                return Msg.fail().setMsg("服务错误");
            }

            @Override
            public Msg fuzzySearchByName(String menuName) {
                return Msg.fail().setMsg("服务错误");
            }

            @Override
            public Msg findBymenuPropertiesCategoryId(Integer menuPropertiesCategoryId) {
                return Msg.fail().setMsg("服务错误");
            }

            @Override
            public Msg findAllmenuPropertiesStyleId() {
                return Msg.fail().setMsg("服务错误");
            }

            @Override
            public Msg findBymenuPropertiesStyleId(Integer menuPropertiesStyleId) {
                return Msg.fail().setMsg("服务错误");
            }

            @Override
            public Msg findAllmenuPropertiesTasteId() {
                return Msg.fail().setMsg("服务错误");
            }

            @Override
            public Msg findBymenuPropertiesTasteId(Integer menuPropertiesTasteId) {
                return Msg.fail().setMsg("服务错误");
            }
        };
    }
}
