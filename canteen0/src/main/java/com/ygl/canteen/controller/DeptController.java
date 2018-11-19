package com.ygl.canteen.controller;

import com.ygl.canteen.model.Dept;
import com.ygl.canteen.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private IDeptService deptService;

    @GetMapping("/dept/getUsers")
    @ResponseBody
    public List<Dept> getDepts(){
        return deptService.getDepts();
    }

    @PostMapping("/dept/getEmpno")
    public String getEmpno(int deptId){
        if(deptId == 0){
            return "100";
        }
        return deptService.getEmpno(deptId);
    }
}
