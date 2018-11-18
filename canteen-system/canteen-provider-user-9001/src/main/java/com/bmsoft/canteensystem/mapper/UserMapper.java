package com.bmsoft.canteensystem.mapper;

import com.bmsoft.canteensystem.entity.User;
import org.apache.ibatis.annotations.*;
/**
 * USER-PROVIDER UserMapper
 * @Author liugaoyang
 */
@Mapper
public interface UserMapper {

    /**
     * 通过empNo获取User对象（级联获取）
     * @param empNo
     * @return
     */
    @Select("SELECT * FROM tb_user WHERE emp_no = #{empNo}")
    @Results(
            @Result(property = "emp",
            column = "emp_no",
            one = @One(select = "com.bmsoft.canteensystem.mapper.EmpMapper.findById"))
    )
    public User cascadingFindByEmpNo(String empNo);

    /**
     * 通过empNo获取User对象
     * @param empNo
     * @return
     */
    @Select("SELECT * FROM tb_user WHERE emp_no = #{empNo}")
    public User findByEmpNo(String empNo);

    /**
     * 添加用户
     * @param user
     */
    @Insert("INSERT INTO tb_user(user_pwd,role_id,emp_no) VALUES (#{userPwd},#{roleId},#{emp.empNo})")
    public void add(User user);
}
