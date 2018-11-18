package com.bmsoft.canteensystem.mapper;
/**
 * USER-PROVIDER DeptMapper
 * @Author liugaoyang
 */

import com.bmsoft.canteensystem.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface DeptMapper {
    /**
     * 通过deptId 获取Dept对象
     * @param deptId
     * @return
     */
    @Select("SELECT dept_id,dept_name FROM tb_dept WHERE dept_id = #{deptId}")
    public Dept findById(@Param("deptId") Integer deptId);

}
