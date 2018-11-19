package com.ygl.canteen.service;

import com.ygl.canteen.model.Dept;
import com.ygl.canteen.model.Emp;

public interface IEmpService {

    int getRoleId(int deptId);
    int add(Emp emp);
    Emp getEmpByUsername(String username);
    Emp checkLogin(String username,String pwd);
    Emp getEmpById(int id);
}
