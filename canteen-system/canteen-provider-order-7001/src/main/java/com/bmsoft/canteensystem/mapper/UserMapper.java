package com.bmsoft.canteensystem.mapper;

import com.bmsoft.canteensystem.dto.OrderDto;
import com.bmsoft.canteensystem.entity.ShoppingCar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select tb_emp.emp_pic_path from tb_user join tb_emp on " +
            "tb_user.emp_no = tb_emp.emp_no where tb_user.user_id = #{userId}")
    String getPicByUserId(Integer userId);

    /*@Select("SELECT tb_shopping_car.shopping_car_id,tb_shopping_car.match_menu_id match_menu_id,tb_shopping_car.shopping_car_menu_num,tb_shopping_car.user_id\n" +
            " FROM tb_shopping_car,tb_menu,tb_match_menu\n" +
            " WHERE tb_shopping_car.match_menu_id=tb_match_menu.match_menu_id AND tb_match_menu.menu_id=tb_menu.menu_id AND user_id=#{userId}\n" +
            " ORDER BY tb_menu.shop_id DESC,tb_match_menu.match_menu_date DESC,tb_match_menu.match_menu_time DESC")
    List<ShoppingCar> getShoppingCar(int id);*/

    @Select("SELECT tb_emp.emp_pic_path FROM tb_order,tb_emp,tb_user WHERE" +
            " tb_order.user_id = tb_user.user_id AND tb_user.emp_no = tb_emp.emp_no" +
            " AND tb_order.order_no = #{orderNo}")
    String getPicByUserOrderNo(String orderNo);
}
