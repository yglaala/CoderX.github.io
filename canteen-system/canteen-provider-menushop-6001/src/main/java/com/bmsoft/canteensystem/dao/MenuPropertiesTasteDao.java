package com.bmsoft.canteensystem.dao;

import com.bmsoft.canteensystem.entity.MenuPropertiesCategory;
import com.bmsoft.canteensystem.entity.MenuPropertiesTaste;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜品口味Dao层
 */
@Mapper
public interface MenuPropertiesTasteDao {

    /**
     * 根据id查找菜品口味
     * @param id
     * @return
     */
    public MenuPropertiesTaste findById(Integer id);

    /**
     * 查询所有菜品种类id
     * @return
     */
    public List<MenuPropertiesTaste> findAllMenuPropertiesTasteId();

}
