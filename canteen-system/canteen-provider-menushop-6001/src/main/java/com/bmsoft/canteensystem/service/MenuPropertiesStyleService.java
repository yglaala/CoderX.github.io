package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.entity.Menu;
import com.bmsoft.canteensystem.entity.MenuPropertiesCategory;
import com.bmsoft.canteensystem.entity.MenuPropertiesStyle;

import java.util.List;

/**
 * 菜品种类Service层
 */
public interface MenuPropertiesStyleService {

    /**
     * 查找所有菜品类型id
     * @return
     */
    public List<MenuPropertiesStyle> findAllMenuPropertiesStyleId();

    /**
     * 根据菜品种类id查找菜品
     * @param id
     * @return
     */
    public List<Menu> findAllByMenuPropertiesStyleId(Integer id);
}
