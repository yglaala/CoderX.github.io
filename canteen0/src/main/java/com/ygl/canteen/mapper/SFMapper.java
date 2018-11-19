package com.ygl.canteen.mapper;

import com.ygl.canteen.model.Emp;
import com.ygl.canteen.model.Menu;
import com.ygl.canteen.model.SF;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SFMapper {

    @Select("select * from t_menu where id not in (select menu_id from t_sf " +
            "where to_days(createdate) = to_days(#{createdate}))")
    List<Menu> getMenus(String createdate);

    @Insert("insert into t_sf(menu_id,emp_id,createdate) values (#{menu.id},#{emp.id},#{createdate})")
    int add(SF sf);

    @Results({
            @Result(property = "menu", javaType = Menu.class, column = "menu_id",
                    one = @One(select = "com.ygl.canteen.mapper.MenuMapper.getMenuById")),
            @Result(property = "emp.id", column = "emp_id")
    })
    @Select("select * from t_sf where to_days(createdate) = to_days(now())")
    List<SF> getTodayMenu();

    @Results({
            @Result(property = "menu", javaType = Menu.class, column = "menu_id",
                    one = @One(select = "com.ygl.canteen.mapper.MenuMapper.getMenuById")),
            @Result(property = "emp.id"  , column = "emp_id"),
            @Result(property = "createdate", column = "WEEKDAY(createdate)")
    })
    @Select("select *,WEEKDAY(createdate) from t_sf where " +
            "createdate>=FROM_UNIXTIME(UNIX_TIMESTAMP() - WEEKDAY(NOW()) * 86400, '%Y-%m-%d')" +
            "order BY createdate asc")
    List<SF> getWeekMenu();
}
