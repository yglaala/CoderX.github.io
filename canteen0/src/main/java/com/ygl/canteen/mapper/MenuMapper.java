package com.ygl.canteen.mapper;

import org.apache.ibatis.annotations.*;
import com.ygl.canteen.model.Menu;

import java.util.List;

@Mapper
public interface MenuMapper {

    @Insert("insert into t_menu (name,price,createdate,path)" +
            "values(#{name},#{price},now(),#{path})")
    int add(Menu menu);
    @Select("select * from t_menu where name = #{name}")
    Menu getMenuByName(String name);
    @Select("select * from t_menu")
    List<Menu> getMenus();
    @Delete("delete from t_menu where id = #{id}")
    int delete(int id);
    @Update("update t_menu set name = #{name} , price = #{price} ,path = #{path}" +
            " where id = #{id}")
    int update(Menu menu);
    @Select("select * from t_menu where id = #{id}")
    Menu getMenuById(int id);


 }
