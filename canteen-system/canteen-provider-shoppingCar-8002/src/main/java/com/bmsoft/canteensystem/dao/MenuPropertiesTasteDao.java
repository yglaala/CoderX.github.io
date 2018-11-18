package com.bmsoft.canteensystem.dao;

import com.bmsoft.canteensystem.entity.MenuPropertiesTaste;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chengpeng
 * @create 2018-10-16 8:43
 */
@Mapper
public interface MenuPropertiesTasteDao {
    /**
     * 功能描述:通过口味id查询菜品口味信息
     * @param  * @param menuPropertiesTasteId 
     * @return com.bmsoft.canteensystem.entity.MenuPropertiesTaste
     */
    MenuPropertiesTaste findById(Integer menuPropertiesTasteId);
}
