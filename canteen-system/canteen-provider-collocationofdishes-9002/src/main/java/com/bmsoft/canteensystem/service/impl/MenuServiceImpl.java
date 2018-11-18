package com.bmsoft.canteensystem.service.impl;

import com.bmsoft.canteensystem.entity.Menu;
import com.bmsoft.canteensystem.mapper.MenuMapper;
import com.bmsoft.canteensystem.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author liugaoyang
 * @Date 2018/10/21 14:24
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;
    /**
     * 功能说明：
     * 更具店铺Id获取菜品列表
     * @param shopId
     * @return
     */
    @Override
    public List<Menu> menuList(Integer shopId) {
        return menuMapper.findByShopId(shopId);
    }

}
