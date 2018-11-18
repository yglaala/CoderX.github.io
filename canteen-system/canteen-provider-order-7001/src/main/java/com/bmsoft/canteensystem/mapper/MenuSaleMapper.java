package com.bmsoft.canteensystem.mapper;

import com.bmsoft.canteensystem.dto.MenuSaleDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuSaleMapper {

    @Insert("INSERT INTO tb_menu_sale (menu_id, menu_sale_num,menu_sale_date) SELECT" +
            " tb_match_menu.menu_id,tb_order_menu.order_menu_num,CURRENT_DATE()" +
            " FROM tb_order_menu JOIN tb_match_menu ON tb_order_menu.match_menu_id =" +
            " tb_match_menu.match_menu_id WHERE tb_order_menu.order_no = #{orderNo}")
    Integer addMenuSaleByOrderNo(String orderNo);


    @Select("SELECT shop_menu.*,IF (isnull(menu_sale.sale_num),0,(menu_sale.sale_num))" +
            " AS sale_num FROM (SELECT tb_menu.menu_id,tb_menu.menu_name FROM tb_shop," +
            " tb_menu,tb_user WHERE tb_shop.shop_id = tb_menu.shop_id " +
            " AND tb_shop.user_id = tb_user.user_id AND tb_user.user_id = #{userId}) shop_menu" +
            " LEFT JOIN (SELECT sum(tb_menu_sale.menu_sale_num) sale_num,tb_menu.menu_id" +
            " FROM tb_menu JOIN tb_menu_sale ON tb_menu.menu_id = tb_menu_sale.menu_id WHERE" +
            " tb_menu_sale.menu_sale_date BETWEEN #{start} AND #{end} GROUP BY tb_menu.menu_id" +
            " ) menu_sale ON shop_menu.menu_id = menu_sale.menu_id")
    List<MenuSaleDto> getMenuSaleByParams(Map<String, Object> params);

}
