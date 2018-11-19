package com.ygl.canteen.mapper;

import com.ygl.canteen.model.Dept;
import com.ygl.canteen.model.Emp;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmpMapper {

    @Select("select * from t_dept where id = #{id}")
    Dept getDeptById(int deptId);

    @Insert("insert into t_emp (username,pwd,dept_id,role_id,name,empno,createdate)" +
            "values (#{username},#{pwd},#{dept.id},#{role.id},#{name},#{empno},now())")
    int add(Emp emp);


    @Results({
            @Result(property = "role.id",column = "role_id"),
            @Result(property = "dept.id",column = "dept_id")
    })
    @Select("select * from t_emp where username = #{username}")
    Emp getEmpByUsername(String username);
    @Select("select * from t_emp where id = #{id}")
    Emp getEmpById(int id);
}
