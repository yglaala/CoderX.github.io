package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.entity.MatchMenu;
import com.bmsoft.canteensystem.entity.Menu;

import java.util.List;
import java.util.Map;

/**
 * 菜品搭配Service层
 */
public interface MatchMenuService {

    /**
     * 通过菜品id查询菜品搭配信息
     * @param map
     * @return
     */
    public List<MatchMenu> searchByMenuId(Map<String,Object> map);
}
