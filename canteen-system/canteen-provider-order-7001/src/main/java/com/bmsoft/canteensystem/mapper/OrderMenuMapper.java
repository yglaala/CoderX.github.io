package com.bmsoft.canteensystem.mapper;

import com.bmsoft.canteensystem.entity.OrderMenu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface OrderMenuMapper {

    @Insert("insert into tb_order_menu (order_no,match_menu_id,order_menu_num)" +
            "values(#{orderNo},#{matchMenu.matchMenuId},#{orderMenuNum})")
    int addOrderMenu(OrderMenu orderMenu);
}
