package com.bmsoft.canteensystem.dao;

import com.bmsoft.canteensystem.entity.MenuPropertiesStyle;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chengpeng
 * @create 2018-10-16 8:42
 */
@Mapper
public interface MenuPropertiesStyleDao {
    /**
     * 功能描述:通过菜系id查询菜系信息
     * @param  * @param menuPropertiesStyleId 
     * @return com.bmsoft.canteensystem.entity.MenuPropertiesStyle
     */
    MenuPropertiesStyle findById(Integer menuPropertiesStyleId);
}
