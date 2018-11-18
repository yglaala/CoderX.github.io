package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.entity.Menu;
import com.bmsoft.canteensystem.entity.MenuSug;

import java.util.List;

/**
 * 菜品评价Service层
 */
public interface MenuSugService {

    /**
     * 根据菜品id查询菜品评价
     * @param id
     * @return
     */
    public List<MenuSug> findByMenuId(Integer id);
}
