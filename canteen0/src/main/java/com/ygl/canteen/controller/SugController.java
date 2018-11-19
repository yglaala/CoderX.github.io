package com.ygl.canteen.controller;

import com.ygl.canteen.util.Msg;
import com.ygl.canteen.model.Emp;
import com.ygl.canteen.model.Suggestion;
import com.ygl.canteen.service.ISugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class    SugController {

    @Autowired
    private ISugService sugService;

    /*@ResponseBody
    @GetMapping("/sug")
    public List<Suggestion> getSugs(){
        return sugService.getSugs();
    }*/

    @GetMapping("/emp/sug/add")
    public String add(){

        return "emp/sug/add";
    }

    @GetMapping("/emp/sug/list")
    public String list(){
        return "emp/sug/list";
    }

    @PostMapping("/emp/sug/getSugs")
    @ResponseBody
    public List<Suggestion> getSugs(){

        return sugService.getSugs();
    }

    @PostMapping("/emp/sug/insert")
    @ResponseBody
    public Msg insert(Suggestion suggestion, HttpServletRequest request){

        if(suggestion.getSug() == null || suggestion.getSug() == ""){
            return new Msg(100,"建议内容不能为空");
        }
        Emp emp = (Emp)request.getSession().getAttribute("emp");
        suggestion.setEmp(emp);
        if(sugService.add(suggestion) < 1){
            return new Msg(100,"添加失败");
        }
        return new Msg(200,"添加成功");
    }

}
