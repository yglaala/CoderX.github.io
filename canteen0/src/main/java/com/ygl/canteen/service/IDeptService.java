package com.ygl.canteen.service;

import com.ygl.canteen.model.Dept;

import java.util.List;

public interface IDeptService {

    List<Dept> getDepts();
    String getEmpno(int deptId);

}
