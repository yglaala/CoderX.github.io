package com.bmsoft.canteensystem.dao;

import com.bmsoft.canteensystem.entity.Menu;
import com.bmsoft.canteensystem.entity.MenuPropertiesCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜品种类Dao层
 */
@Mapper
public interface MenuPropertiesCategoryDao {

    /**
     * 根据id查找菜品种类
     * @param id
     * @return
     */
    public MenuPropertiesCategory findById(Integer id);

    /**
     * 查找所有菜品类型id
     * @return
     */
    public List<MenuPropertiesCategory> findAllMenuPropertiesCategoryId();


}
