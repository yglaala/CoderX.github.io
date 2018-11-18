package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.entity.Menu;
import com.bmsoft.canteensystem.entity.MenuPropertiesCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜品种类Service层
 */
public interface MenuPropertiesCategoryService {

    /**
     * 查找所有菜品类型id
     * @return
     */
    public List<MenuPropertiesCategory> findAllMenuPropertiesCategoryId();


    /**
     * 根据菜品类型id查找菜品
     * @param id
     * @return
     */
    public List<Menu> findAllByMenuPropertiesCategoryId(Integer id);
}
