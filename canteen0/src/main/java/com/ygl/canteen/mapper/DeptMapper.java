package com.ygl.canteen.mapper;

import com.ygl.canteen.model.Dept;
import com.ygl.canteen.model.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from t_dept")
    List<Dept> getDepts();

    @Select("select e.* from t_dept d,t_emp e where d.id = e.dept_id and d.id = #{id} order by e.id desc limit 1")
    Emp getMaxEmpId(int deptId);

}
