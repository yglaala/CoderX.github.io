package com.ygl.canteen.mapper;

import com.ygl.canteen.dto.DeptCost;
import com.ygl.canteen.model.*;
import com.ygl.canteen.model.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    /**
     * 更新订餐时间
     *
     * @param time
     * @return
     */
    @Update("update t_advancedtime set maxtime = #{maxtime}")
    int updateAdvanceTime(int time);

    /**
     * 将订单信息插入订单表
     *
     * @param order
     * @return
     */
    @Insert("insert into t_order(orderno,emp_id,status_id1,status_id2,orderdate,createdate,cost)" +
            " values (#{orderno},#{emp.id},#{status1.id},#{status2.id},#{orderdate},now(),#{cost})")
    int add(Order order);

    /**
     * 将订单的菜品信息插入订单菜品表
     *
     * @param orderMenu
     * @return
     */
    @Insert("insert into t_ordermenu (orderno,menu_id,num,price) values (#{order.orderno},#{menu.id},#{num},#{price})")
    int addOrderMenu(OrderMenu orderMenu);


    /**
     * 更改管理员订单状态
     * @param order
     * @return
     */
    @Update("update t_order set status_id1 = #{status1.id} where orderno = #{orderno}")
    @Options(useGeneratedKeys = false)
    int updateStatus1(Order order);

    /**
     * 更改员工订单状态
     * @param order
     * @return
     */
    @Update("update t_order set status_id2 = #{status2.id} where orderno = #{orderno}")
    @Options(useGeneratedKeys = false)
    int updateStatus2(Order order);

    /**
     * 通过订单号获取订单的详细信息
     *
     * @param orderno
     * @return
     */
    @Results({
            @Result(property = "emp", javaType = Emp.class, column = "emp_id",
                    one = @One(select = "com.ygl.canteen.mapper.EmpMapper.getEmpById")),
            @Result(property = "status1", javaType = Status.class, column = "status_id1",
                    one = @One(select = "com.ygl.canteen.mapper.StatusMapper.getStatusById")),
            @Result(property = "status2", javaType = Status.class, column = "status_id2",
                    one = @One(select = "com.ygl.canteen.mapper.StatusMapper.getStatusById")),
            @Result(property = "orderno", column = "orderno"),
            @Result(property = "orderMenus", javaType = List.class, column = "orderno",
                    many = @Many(select = "com.ygl.canteen.mapper.OrderMapper.getOrderMenusByOrderno"))
    })
    @Select("select * from t_order where orderno = #{orderno} order by orderdate asc,orderno desc")
    Order getEmpOrdersDetails(String orderno);

    /**
     * 通过员工号获取订单
     * @param empId
     * @return
     */
    @Results({
            @Result(property = "emp", javaType = Emp.class, column = "emp_id",
                    one = @One(select = "com.ygl.canteen.mapper.EmpMapper.getEmpById")),
            @Result(property = "status2", javaType = Status.class, column = "status_id2",
                    one = @One(select = "com.ygl.canteen.mapper.StatusMapper.getStatusById")),
            @Result(property = "orderno", column = "orderno"),
            @Result(property = "orderMenus", javaType = List.class, column = "orderno",
                    many = @Many(select = "com.ygl.canteen.mapper.OrderMapper.getOrderMenusByOrderno"))
    })
    @Select("select * from t_order where emp_id = #{empId} and status_id2<5 order by orderdate asc,orderno desc")
    List<Order> getOrdersByEmpId(int empId);

    /**
     * 获取所有订单
     * @return
     */
    @Results({
            @Result(property = "emp", javaType = Emp.class, column = "emp_id",
                    one = @One(select = "com.ygl.canteen.mapper.EmpMapper.getEmpById")),
            @Result(property = "status1", javaType = Status.class, column = "status_id1",
                    one = @One(select = "com.ygl.canteen.mapper.StatusMapper.getStatusById")),
            @Result(property = "orderno", column = "orderno"),
            @Result(property = "orderMenus", javaType = List.class, column = "orderno",
                    many = @Many(select = "com.ygl.canteen.mapper.OrderMapper.getOrderMenusByOrderno"))
    })
    @Select("select * from t_order where status_id1 < 5 order by orderdate asc,orderno desc")
    List<Order> getOrders();

    /**
     * 获取订单表中最大id+1
     *
     * @return
     */
    @Select("select id+1 from t_order order by id desc limit 1")
    String getMaxId();

    /**
     * 获取订餐时间
     *
     * @return
     */
    @Select("select maxtime from t_advancedtime")
    int getMaxTime();

    /**
     * 通过id获取菜品信息
     *
     * @param id
     * @return
     */
    @Select("select price from t_menu where id = #{id}")
    float getPriceByMenu(int id);

    @Update("update t_order set cost = 1 , costdate = now() where orderno = #{orderno}")
    int updateCost(String orderno);

    @Results({
            @Result(property = "menu" , javaType = Menu.class, column = "menu_id",
            one = @One(select = "com.ygl.canteen.mapper.MenuMapper.getMenuById"))
    })
    @Select("select * from t_ordermenu where orderno = #{orderno}")
    List<OrderMenu> getOrderMenusByOrderno(String orderno);

    /**
     * 获取员工的消费情况
     * @param empId
     * @return
     */
    @Results({
            @Result(property = "emp", javaType = Emp.class, column = "emp_id",
                    one = @One(select = "com.ygl.canteen.mapper.EmpMapper.getEmpById")),
            @Result(property = "status1", javaType = Status.class, column = "status_id1",
                    one = @One(select = "com.ygl.canteen.mapper.StatusMapper.getStatusById")),
            @Result(property = "orderno", column = "orderno"),
            @Result(property = "orderMenus", javaType = List.class, column = "orderno",
                    many = @Many(select = "com.ygl.canteen.mapper.OrderMapper.getOrderMenusByOrderno"))
    })
    @Select("select * from t_order where emp_id = #{empId} and cost = 1")
    List<Order> getOrderByEmpCost(int empId);

    /**
     * 获取所有部门某一段时间的消费
     * @param date
     * @return
     */
    @SelectProvider(type = OrderMapperProvider.class , method = "getDeptCost")
    List<DeptCost> getDeptCostByDate(String date);


    class OrderMapperProvider{
        public String getDeptCost(String date) {
            String sql = "select d.name, sum(op.orderprice) totalCost, count(*) orderNums " +
                    "from t_order o , t_emp e ,t_dept d , " +
                    "(select sum(price*num) orderprice,orderno from t_ordermenu " +
                    "GROUP BY orderno) op where o.orderno = op.orderno " +
                    " and o.emp_id = e.id  and e.dept_id = d.id and o.cost = 1 ";
            if(date != null && !date.equals("")){
                sql += "and o.costdate like CONCAT(#{date},'%')";
            }
            /*System.out.println("date :"+ date);
            System.out.println(sql);*/
            sql += " group by d.id";
            return sql;
        }
    }
}
