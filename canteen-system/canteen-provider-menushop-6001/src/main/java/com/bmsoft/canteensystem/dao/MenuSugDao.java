package com.bmsoft.canteensystem.dao;

import com.bmsoft.canteensystem.entity.MenuSug;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜品建议dao层
 */
@Mapper
public interface MenuSugDao {

    /**
     * 根据菜品id查询菜品评价
     * @param id
     * @return
     */
    public List<MenuSug> findByMenuId(Integer id);
}
