package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.entity.Menu;

import java.util.List;

/**
 * @Author liugaoyang
 * @Date 2018/10/21 14:22
 */
public interface MenuService {

    //获取菜品信息列表
    public List<Menu> menuList(Integer shopId);
}
