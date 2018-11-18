package com.bmsoft.canteensystem.dao;

import com.bmsoft.canteensystem.entity.MenuPropertiesCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chengpeng
 * @create 2018-10-16 8:41
 */
@Mapper
public interface MenuPropertiesCategoryDao {
    /**
     * 功能描述:通过烹饪方式id查询烹饪信息
     * @param  * @param menuPropertiesCategroyId 
     * @return com.bmsoft.canteensystem.entity.MenuPropertiesCategory
     */
    MenuPropertiesCategory findById(Integer menuPropertiesCategroyId);
}
