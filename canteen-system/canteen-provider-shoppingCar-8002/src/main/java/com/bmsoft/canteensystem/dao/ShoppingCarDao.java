package com.bmsoft.canteensystem.dao;

import com.bmsoft.canteensystem.dto.MenuDto;
import com.bmsoft.canteensystem.dto.ShoppingCarDto;
import com.bmsoft.canteensystem.entity.ShoppingCar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * @author chengpeng
 * @create 2018-10-15 11:51
 * 购物车Dao
 */
@Mapper
public interface ShoppingCarDao {

    /**
     * 功能描述:根据搭配菜品id和用户id查询购物车信息
     * @param  map
     * @return com.bmsoft.canteensystem.entity.ShoppingCar
     */
    ShoppingCar findByMatchMenuIdAndUserId(Map<String,Object> map);

    /**
     * 添加菜品到购物车
     * @param  * @param shoppingCar 
     * @return boolean
     */
    boolean add(ShoppingCar shoppingCar);

    /**
     * 根据购物车id删除购物车信息
     * @param  * @param shopId 
     * @return boolean
     */
    boolean delete(Integer shoppingCarId);

    /**
     * 更新购物车信息
     * @param  * @param shoppingCar 
     * @return boolean
     */
    boolean update(ShoppingCar shoppingCar);

    /**
     * 功能描述:查询购物车信息头
     * @param  * @param userId 
     * @return java.util.List<com.bmsoft.canteensystem.dto.ShoppingCarDto>
     */
    @Select("SELECT" +
            " tb_shop.shop_name," +
            " tb_shop.shop_id," +
            " tb_match_menu.match_menu_date," +
            " tb_match_menu.match_menu_time" +
            " FROM" +
            " tb_shopping_car," +
            " tb_match_menu," +
            " tb_menu," +
            " tb_shop" +
            " WHERE" +
            " tb_shopping_car.match_menu_id = tb_match_menu.match_menu_id" +
            " AND tb_match_menu.menu_id = tb_menu.menu_id" +
            " AND tb_menu.shop_id = tb_shop.shop_id" +
            " AND tb_shopping_car.user_id = #{userId}" +
            " GROUP BY tb_shop.shop_name,tb_match_menu.match_menu_date,tb_match_menu.match_menu_time" +
            " ORDER BY" +
            " tb_shop.shop_name DESC," +
            " tb_match_menu.match_menu_date ASC," +
            " tb_match_menu.match_menu_time ASC")
    List<ShoppingCarDto> findByUserId(Integer userId);

    /**
     * 功能描述:查询购物车信息体
     * @param  * @param params 
     * @return java.util.List<com.bmsoft.canteensystem.dto.MenuDto>
     */
    @SelectProvider(type = ShoppingCarDaoProvider.class,method = "getMenus")
    List<MenuDto> getShoppingCarMenus(Map<String,Object> params);

    /**
     * 功能描述:Provider类
     * @param  * @param null 
     * @return 
     */
    class ShoppingCarDaoProvider{
        public String getMenus(Map<String,Object> params){
            String sql="SELECT" +
                    " tb_shopping_car.shopping_car_id,"+
                    " tb_match_menu.match_menu_id,"+
                    " tb_menu.menu_name," +
                    " tb_menu.menu_pic_path," +
                    " tb_shopping_car.shopping_car_menu_num," +
                    " tb_menu.menu_price," +
                    " tb_match_menu.match_menu_num," +
                    " tb_menu_properties_category.menu_properties_category_name," +
                    " tb_menu_properties_style.menu_properties_style_name," +
                    " tb_menu_properties_taste.menu_properties_taste_name" +
                    " FROM" +
                    " tb_shopping_car," +
                    " tb_match_menu," +
                    " tb_menu," +
                    " tb_shop," +
                    " tb_menu_properties_category," +
                    " tb_menu_properties_style," +
                    " tb_menu_properties_taste" +
                    " WHERE" +
                    " tb_shopping_car.match_menu_id = tb_match_menu.match_menu_id" +
                    " AND tb_match_menu.menu_id = tb_menu.menu_id" +
                    " AND tb_menu.shop_id = tb_shop.shop_id" +
                    " AND tb_menu_properties_category.menu_properties_category_id = tb_menu.menu_properties_category_id" +
                    " AND tb_menu_properties_style.menu_properties_style_id = tb_menu.menu_properties_style_id" +
                    " AND tb_menu_properties_taste.menu_properties_taste_id = tb_menu.menu_properties_taste_id" +
                    " AND tb_shopping_car.user_id = '" +params.get("userId")+"'"+
                    " AND tb_shop.shop_name = '" +params.get("shopName")+"'"+
                    " AND tb_match_menu.match_menu_time = '" +params.get("matchMenuTime")+"'"+
                    " AND tb_match_menu.match_menu_date = '"+params.get("matchMenuDate")+"'";
//            System.out.println(sql);
            return sql;
        }
    }



}
