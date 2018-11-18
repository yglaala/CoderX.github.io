package com.bmsoft.canteensystem.dao;

import com.bmsoft.canteensystem.entity.MatchMenu;
import com.bmsoft.canteensystem.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/***
 * 菜品搭配Dao层
 */
@Mapper
public interface MatchMenuDao {

    /**
     * 通过菜品id查询菜品搭配信息
     * @param map
     * @return
     */
    public List<MatchMenu> searchByMenuId(Map<String,Object> map);


}
