package com.bmsoft.canteensystem.dao;

import com.bmsoft.canteensystem.entity.Dept;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chengpeng
 * @create 2018-10-16 20:38
 * 部门Dao接口
 */
@Mapper
public interface DeptDao {
    /**
     * 功能描述:通过部门id查找部门信息
     * @param  * @param deptId 
     * @return com.bmsoft.canteensystem.entity.Dept
     */
    Dept findById(Integer deptId);
}
