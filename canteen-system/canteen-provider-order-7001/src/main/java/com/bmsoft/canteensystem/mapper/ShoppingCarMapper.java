package com.bmsoft.canteensystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface ShoppingCarMapper {

    @Select("delete from tb_shopping_car where match_menu_id = #{matchMenuId} and user_id = #{userId}")
    Integer deleteShopCarByMatchId(Map<String, Object> params);
}
