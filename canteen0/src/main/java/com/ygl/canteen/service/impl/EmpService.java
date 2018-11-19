package com.ygl.canteen.service.impl;

import com.ygl.canteen.mapper.EmpMapper;
import com.ygl.canteen.model.Emp;
import com.ygl.canteen.service.IEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpService implements IEmpService {

    @Autowired
    private EmpMapper empMapper;
    @Override
    public int getRoleId(int deptId) {

        if(empMapper.getDeptById(deptId).getName().equals("食堂")){
            return 1;
        }
        return 2;
    }

    @Override
    public int add(Emp emp) {
        if(empMapper.getEmpByUsername(emp.getUsername()) != null){
            return -1;
        }
        return empMapper.add(emp);
    }

    @Override
    public Emp getEmpByUsername(String username) {

        return empMapper.getEmpByUsername(username);
    }

    @Override
    public Emp checkLogin(String username, String pwd) {

        Emp emp = empMapper.getEmpByUsername(username);
        if(emp != null && pwd.equals(emp.getPwd())){
            return emp;
        }
        return null;
    }

    @Override
    public Emp getEmpById(int id) {
        return empMapper.getEmpById(id);
    }
}
