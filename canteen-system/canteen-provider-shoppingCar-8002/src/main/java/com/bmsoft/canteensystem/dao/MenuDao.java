package com.bmsoft.canteensystem.dao;

import com.bmsoft.canteensystem.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chengpeng
 * @create 2018-10-16 8:26
 */
@Mapper
public interface MenuDao {
    /**
     * 功能描述:通过菜品id查询菜品信息
     * @param  * @param menuId 
     * @return com.bmsoft.canteensystem.entity.Menu
     */
    Menu findById(Integer menuId);
}
