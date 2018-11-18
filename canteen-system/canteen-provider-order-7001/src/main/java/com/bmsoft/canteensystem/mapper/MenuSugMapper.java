package com.bmsoft.canteensystem.mapper;

import com.bmsoft.canteensystem.entity.MenuSug;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MenuSugMapper {

    @Insert("insert into tb_menu_sug(order_no,user_id,menu_id,sug_content,menu_rating,menu_sug_create_date)" +
            " values (#{orderNo},#{user.userId},#{menu.menuId},#{sugContent},#{menuRating},now())")
    Integer addSugMenu(MenuSug menuSug);

    @Update("update tb_menu_sug set sug_content = #{sugContent} , menu_rating = #{menuRating}," +
            " menu_sug_create_date = now() where menu_sug_id = #{menuSugId}")
    Integer updateSugMenu(MenuSug menuSug);
}
