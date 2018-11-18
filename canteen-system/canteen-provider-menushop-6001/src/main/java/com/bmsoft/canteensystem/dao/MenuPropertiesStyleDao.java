package com.bmsoft.canteensystem.dao;

import com.bmsoft.canteensystem.entity.Menu;
import com.bmsoft.canteensystem.entity.MenuPropertiesStyle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜系Dao层
 */
@Mapper
public interface MenuPropertiesStyleDao {

    /**
     * 根据id查找菜系
     * @param id
     * @return
     */
    public MenuPropertiesStyle findById(Integer id);


    /**
     * 查找所有菜品类型id
     * @return
     */
    public List<MenuPropertiesStyle> findAllMenuPropertiesStyleId();



}
