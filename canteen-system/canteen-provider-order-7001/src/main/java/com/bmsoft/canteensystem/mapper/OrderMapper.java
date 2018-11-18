package com.bmsoft.canteensystem.mapper;

import com.bmsoft.canteensystem.dto.*;
import com.bmsoft.canteensystem.entity.Order;
import com.bmsoft.canteensystem.entity.OrderStatus;
import com.bmsoft.canteensystem.entity.User;
import com.bmsoft.canteensystem.util.DateUtil;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {

    String COMMON_SQL_ORDER = "SELECT tb_order.order_no,sum(tb_order_menu.order_menu_num * binary tb_menu.menu_price) total_price,tb_emp.emp_no," +
            " tb_match_menu.match_menu_date booking_date,tb_match_menu.match_menu_time which_meal,tb_order.order_pay_date," +
            " tb_order_status.order_status_name,tb_order_status.order_status_Id,tb_order.order_create_date,tb_shop.shop_name,tb_emp.emp_name" +
            " FROM tb_order,tb_order_menu,tb_match_menu,tb_menu,tb_order_status,tb_user,tb_shop,tb_emp" +
            " WHERE tb_order.order_no = tb_order_menu.order_no" +
            " AND tb_order_menu.match_menu_id = tb_match_menu.match_menu_id" +
            " AND tb_match_menu.menu_id = tb_menu.menu_id" +
            " AND tb_order.order_status_id = tb_order_status.order_status_id" +
            " AND tb_shop.shop_id = tb_menu.shop_id" +
            " AND tb_user.user_id = tb_order.user_id" +
            " AND tb_emp.emp_no = tb_user.emp_no";

    String COMMON_SQL_OREDER_MENU = "SELECT tb_order_menu.order_no,tb_menu.menu_id,tb_menu.menu_name,tb_menu.menu_price," +
            " tb_order_menu.order_menu_num menu_num,tb_menu_properties_category.menu_properties_category_name," +
            " tb_menu_properties_style.menu_properties_style_name,tb_menu_properties_taste.menu_properties_taste_name" +
            " FROM tb_order_menu,tb_match_menu,tb_menu,tb_menu_properties_category,tb_menu_properties_style," +
            " tb_menu_properties_taste" +
            " WHERE tb_order_menu.match_menu_id = tb_match_menu.match_menu_id" +
            " AND tb_match_menu.menu_id = tb_menu.menu_id" +
            " AND tb_menu.menu_properties_category_id = tb_menu_properties_category.menu_properties_category_id" +
            " AND tb_menu.menu_properties_style_id = tb_menu_properties_style.menu_properties_style_id" +
            " AND tb_menu.menu_properties_taste_id = tb_menu_properties_taste.menu_properties_taste_id" +
            " AND tb_order_menu.order_no = #{orderNo}";

    /**
     * 添加订单
     * @param order
     * @return
     */
    @Insert("insert into tb_order(order_no,order_status_id,user_id,order_pay_status,order_create_date)" +
            "values(#{orderNo},#{orderStatus.orderStatusId},#{user.userId},#{orderPayStatus},now())")
    int add(Order order);

    /***
     * 获取所有订单
     * @param params
     * @return
     */
    @Results ({
            @Result(property = "orderMenuDtos" ,javaType = List.class , column = "order_no"
                    ,many = @Many(select = "com.bmsoft.canteensystem.mapper.OrderMapper.getOrderMenusByOrderNo")),
            @Result(property = "orderNo" , column = "order_no")
    })
    @SelectProvider(type = OrderMapperProvider.class , method = "getOrders")
    List<OrderDto> getOrders(Map<String, Object> params);

    /*@Results({
            @Result(property = "menuSugs" , column = "order_no" ,
            many = @Many(select = "com.bmsoft.canteensystem.mapper.MenuSugMapper.getMenuSugsByOrderNo")),
            @Result(property = "order_no" ,column = "order_no")
    })*/

    /***
     * 获取订单菜品建议
     * @param orderNo
     * @return
     */
    @SelectProvider(type = OrderMapperProvider.class , method = "getOrderMenuSugsByOrderNo")
    List<OrderMenuSugDto> getOrderMenuSugsByOrderNo(String orderNo);

    /**
     * 获取订单菜品
     * @param orderNo
     * @return
     */
    @SelectProvider(type = OrderMapperProvider.class , method = "getOrderMenusByOrderNo")
    List<OrderMenuDto> getOrderMenusByOrderNo(String orderNo);

    /***
     * 获取订单详情
     * @param orderNo
     * @return
     */
    @Results ({
            @Result(property = "orderMenuDtos" ,javaType = List.class , column = "order_no"
                    ,many = @Many(select = "com.bmsoft.canteensystem.mapper.OrderMapper.getOrderMenusByOrderNo")),
            @Result(property = "orderNo" , column = "order_no")
    })
    @Select(COMMON_SQL_ORDER+ " AND tb_order.order_no = #{orderNo}")
    OrderDto getOrderDetailsByOrderNo(String orderNo);

    /**
     * 获取菜品余量
     * @param matchMenuId
     * @return
     */
    @Select("SELECT tb_menu.menu_name,tb_match_menu.match_menu_num from tb_match_menu " +
            ",tb_menu where tb_menu.menu_id = tb_match_menu.menu_id and  tb_match_menu.match_menu_id = #{id}")
    Map<String,Object> getRemainMenu(int matchMenuId);

    /**
     * 更新订单状态
     * @param params
     * @return
     */
    @UpdateProvider(type = OrderMapperProvider.class , method ="UpdateOrderStatus")
    Integer updateOrderStatus(Map<String, Object> params);

    /**
     * 订单取消，菜品回退
     * @param orderNo
     * @return
     */
    @Update("UPDATE tb_order_menu JOIN tb_match_menu ON tb_match_menu.match_menu_id = tb_order_menu.match_menu_id" +
            " SET tb_match_menu.match_menu_num = tb_match_menu.match_menu_num + tb_order_menu.order_menu_num" +
            " WHERE tb_order_menu.order_no = #{orderNo}")
    Integer returnMatchMenu(String orderNo);

    @SelectProvider(type = OrderMapperProvider.class , method = "getEmpConsume")
    List<EmpConsumeDto> getEmpConsume();

    /***
     * 通过订单编号获取状态
     */
    @Results({
            @Result(column = "order_status_id",property = "orderStatus.orderStatusId")
    })
    @Select("select * from tb_order where order_no = #{orderNo}")
    Order getStatusIdByOrderNo(String orderNo);

    @Select("select * from tb_order_status where order_status_id = #{statusId}")
    OrderStatus getOrderStatus(Integer statusId);

    @Select("SELECT tb_shop.*,tb_match_menu.match_menu_date,tb_match_menu.match_menu_time" +
            " FROM tb_match_menu,tb_shop,tb_menu,tb_order_menu,tb_order" +
            " WHERE tb_match_menu.menu_id = tb_menu.menu_id" +
            " AND tb_menu.shop_id = tb_shop.shop_id" +
            " AND tb_match_menu.match_menu_id = tb_order_menu.match_menu_id" +
            " AND tb_order.order_no = tb_order_menu.order_no" +
            " AND tb_order.order_no = #{orderNo}" +
            " GROUP BY tb_shop.shop_id;")
    MatchMenuDto getShopTimeByOrderNo(String orderNo);

    /**
     * 获取订单的所有状态
     * @return
     */
    @Select("select * from tb_order_status where order_status_id < 7")
    List<OrderStatus> getOrderStatuss();
    /*List<OrderMenuDto>*/
    class OrderMapperProvider{
        public String getOrders(Map<String,Object> params) {

            String start = "0000-01-01";
            String end = "9999-12-31";
            User user = (User) params.get("user");
            String sql = COMMON_SQL_ORDER;
            if(user.getRoleId().equals(1)){
                sql += " AND tb_shop.user_id='"+user.getUserId()+"'" +
                        " AND (tb_order.order_status_id < 7 " +
                        " OR (tb_order.order_status_id > 10 " +
                        " AND tb_order.order_status_id < 15)) ";
            }else {
                sql += " AND tb_order.user_id='"+user.getUserId()+"'" +
                        " AND tb_order.order_status_id < 11";
            }
            sql += " AND tb_match_menu.match_menu_date BETWEEN '"+(((params.get("start") !=null) && (params.get("start") !=""))?params.get("start"):start)+"'" +
                    " AND '"+(((params.get("end") !=null) && (params.get("end") !=""))?params.get("end"):end)+"'";
            if(params.get("statusId") != null && !params.get("statusId").equals(0)){
                sql += " AND (tb_order.order_status_id = '"+params.get("statusId")+"'";
                if(user.getRoleId().equals(1)){
                    sql += " OR tb_order.order_status_id = '"+((int)params.get("statusId")+8)+"')";
                }else {
                    sql += " OR tb_order.order_status_id = '"+((int)params.get("statusId")+4)+"')";
                }

            }
            sql += " GROUP BY tb_order.order_no order by tb_order.order_create_date desc";
            System.out.println(sql);
            return sql;
        }

        public String getOrderMenuSugsByOrderNo(String orderNo){
            String sql = "SELECT * FROM ("+COMMON_SQL_OREDER_MENU+") order_menu left join tb_menu_sug on " +
                    "order_menu.menu_id = tb_menu_sug.menu_id GROUP BY order_menu.menu_id";
            return sql;
        }

        public String getOrderMenusByOrderNo(String orderNo){

            return COMMON_SQL_OREDER_MENU;
        }

        public String UpdateOrderStatus(Map<String,Object> params){

            String sql = "update tb_order set order_status_id = '"+params.get("statusId")+"'";
            if((Integer)params.get("statusId") == 4){
                sql += " , order_pay_status = 1 , order_pay_date = now()";
            }
            sql += " where order_no = '"+params.get("orderNo")+"'";
            //System.out.println(sql);
            //System.out.println(sql);
            return sql;
        }

        public String getEmpConsume(){
            String sql = "SELECT all_order.emp_no,all_order.emp_name,sum(all_order.total_price" +
                    ") emp_total_price,tb_dept.dept_name FROM ("+COMMON_SQL_ORDER+" AND tb_order.order_pay_status = '1'" +
                    " AND tb_order.order_pay_date BETWEEN '"+ DateUtil.getFirstDayOfMonth() +"' AND '"+DateUtil.getLastDayOfMonth()+"'" +
                    " GROUP BY tb_order.order_no) all_order,tb_dept,tb_emp" +
                    " WHERE tb_emp.emp_no = all_order.emp_no" +
                    " AND tb_emp.dept_id = tb_dept.dept_id" +
                    " GROUP BY all_order.emp_no";

            System.out.println(sql);
            return sql;
        }
    }
}
