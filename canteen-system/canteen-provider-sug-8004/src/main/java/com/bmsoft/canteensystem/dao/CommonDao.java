package com.bmsoft.canteensystem.dao;

import com.bmsoft.canteensystem.entity.Dept;
import com.bmsoft.canteensystem.entity.Emp;
import com.bmsoft.canteensystem.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * @author chengpeng
 * @create 2018-10-18 15:20
 */
@Mapper
public interface CommonDao {

    @Select("SELECT * FROM tb_user WHERE user_id=#{userId}")
    @Results({
            @Result(property = "userId",column = "user_id"),
            @Result(property = "emp",column = "emp_no",one = @One(select = "com.bmsoft.canteensystem.dao.CommonDao.findByEmpNo")),
            @Result(property = "userPwd",column = "user_pwd"),
            @Result(property = "roleId",column = "role_id")
    })
    User findByUserId(Integer userId);

    @Select("SELECT * FROM tb_emp WHERE emp_no=#{empNo}")
    @Results({
            @Result(property = "empNo",column = "emp_no"),
            @Result(property = "empNumberId",column = "emp_number_id"),
            @Result(property = "empName",column = "emp_name"),
            @Result(property = "roleId",column = "role_id"),
            @Result(property = "dept",column = "dept_id",one = @One(select = "com.bmsoft.canteensystem.dao.CommonDao.findByDeptId"))

    })
    Emp findByEmpNo(String empNo);

    @Select("SELECT * FROM tb_dept WHERE dept_id=#{deptId}")
    @Results({
            @Result(property = "deptId",column = "dept_id"),
            @Result(property = "deptName",column = "dept_name")
    })
    Dept findByDeptId(Integer deptId);
}
