package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.entity.Menu;
import com.bmsoft.canteensystem.entity.MenuPropertiesCategory;
import com.bmsoft.canteensystem.entity.MenuPropertiesStyle;
import com.bmsoft.canteensystem.entity.MenuPropertiesTaste;

import java.util.List;

/**
 * 菜品口味Service层
 */
public interface MenuPropertiesTasteService {

    /**
     * 查询所有菜品种类id
     * @return
     */
    public List<MenuPropertiesTaste> findAllMenuPropertiesTasteId();

    /**
     * 根据菜品口味查询菜品信息
     * @param id
     * @return
     */
    public List<Menu> findAllByMenuPropertiesTasteId(Integer id);
}

