package com.ygl.canteen.service.impl;

import com.ygl.canteen.util.DateUtil;
import com.ygl.canteen.mapper.DeptMapper;
import com.ygl.canteen.model.Dept;
import com.ygl.canteen.model.Emp;
import com.ygl.canteen.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService implements IDeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> getDepts() {
        return deptMapper.getDepts();
    }

    @Override
    public String getEmpno(int deptId) {

        Emp emp = deptMapper.getMaxEmpId(deptId);
        int id = 1;
        if(emp != null){
            id = emp.getId()+1;
        }
        return id+""+deptId+ DateUtil.getDateStr()+DateUtil.getTimeStr();
    }
}
