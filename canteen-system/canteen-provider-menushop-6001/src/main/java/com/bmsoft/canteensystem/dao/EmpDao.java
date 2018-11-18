package com.bmsoft.canteensystem.dao;

import com.bmsoft.canteensystem.entity.Emp;
import com.bmsoft.canteensystem.entity.MenuPropertiesTaste;

/**
 * 员工Dao层
 */
public interface EmpDao {

    /**
     * 员工id查找菜品口味
     * @return
     */
    public Emp findByEmpNo(String empNo);

}
