package com.ygl.canteen.mapper;

import com.ygl.canteen.model.Emp;
import com.ygl.canteen.model.Suggestion;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SugMapper {

    @Insert("insert into t_sug(emp_id,sug,createdate) values (#{emp.id},#{sug},now())")
    int add(Suggestion sug);

    @Results({
        @Result(property = "emp" , javaType = Emp.class , column = "emp_id" ,
                one = @One(select = "com.ygl.canteen.mapper.EmpMapper.getEmpById"))
    })
    @Select("select * from t_sug where to_days(createdate) >= to_days(now())")
    List<Suggestion> getSugs();
}
