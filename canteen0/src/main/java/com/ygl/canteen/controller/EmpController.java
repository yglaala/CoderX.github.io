package com.ygl.canteen.controller;

import com.ygl.canteen.util.Msg;
import com.ygl.canteen.model.Emp;
import com.ygl.canteen.model.Role;
import com.ygl.canteen.service.IEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class EmpController {

    @Autowired
    private IEmpService empService;
    @GetMapping("/emp/signin")
    public String signin(){
        return "emp/signin";
    }

    @GetMapping("/emp/login")
    public String login(){
        return "emp/login";
    }

    @GetMapping("/emp/main")
    public String main(){
        return "emp/main";
    }
    @GetMapping("/emp/head")
    public String head(){
        return "/emp/head";
    }
    @GetMapping("/emp/left")
    public String left(){
        return "/emp/left";
    }
    @GetMapping("/emp/exit")
    public String exit(HttpServletRequest request){
        request.getSession().removeAttribute("emp");
        return "emp/login";
    }

    @PostMapping("/emp/signin")
    @ResponseBody
    public Msg add(@ModelAttribute("emp") Emp emp ,
                      @RequestParam("confirmPwd") String confirmPwd){
        if(emp.getDept().getId() == 0 || emp.getName() == "" ||
                emp.getUsername() == "" || emp.getPwd() == "" || confirmPwd == ""){
            return new Msg("不能有空值");
        }
        if(!confirmPwd.equals(emp.getPwd())){
            return new Msg("两次输入密码不一致");
        }
        /*emp.setRole(new Role(1));*/
        int i = empService.add(emp);
        if(i == -1){
            return new Msg("该用户名已被注册");
        }
        if(i < 1){
            return new Msg(200,"注册失败");
        }
        return new Msg(100,"注册成功");
    }

    @PostMapping("/emp/login")
    @ResponseBody
    public Msg login(String username , String pwd , HttpServletRequest request){

        Emp emp = empService.checkLogin(username,pwd);
        if(empService.checkLogin(username,pwd) != null){
            request.getSession().setAttribute("emp",emp);
            return new Msg(100,"登陆成功");
        }
        return new Msg(200,"登陆失败");
    }
}
