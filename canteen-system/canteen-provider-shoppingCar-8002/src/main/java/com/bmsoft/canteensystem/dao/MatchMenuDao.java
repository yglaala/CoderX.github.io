package com.bmsoft.canteensystem.dao;

import com.bmsoft.canteensystem.entity.MatchMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chengpeng
 * @create 2018-10-16 8:26
 */
@Mapper
public interface MatchMenuDao {

    /**
     * 功能描述:通过搭配菜品id查询搭配菜品信息
     * @param  * @param matchMenuId 
     * @return com.bmsoft.canteensystem.entity.MatchMenu
     */
    MatchMenu findById(Integer matchMenuId);
}
