package com.bmsoft.canteensystem.dao;

import com.bmsoft.canteensystem.entity.Emp;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chengpeng
 * @create 2018-10-16 20:35
 */
@Mapper
public interface EmpDao {
    /**
     * 功能描述:通过员工编号查询员工信息
     * @param  * @param empNo 
     * @return com.bmsoft.canteensystem.entity.Emp
     */
    Emp findById(String empNo);
}
