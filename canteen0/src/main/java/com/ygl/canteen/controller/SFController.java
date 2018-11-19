package com.ygl.canteen.controller;

import com.ygl.canteen.util.DateUtil;
import com.ygl.canteen.util.Msg;
import com.ygl.canteen.util.StringUtil;
import com.ygl.canteen.model.Emp;
import com.ygl.canteen.model.Menu;
import com.ygl.canteen.model.SF;
import com.ygl.canteen.service.ISFService;
import com.ygl.canteen.dto.WeekMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SFController {

    @Autowired
    private ISFService sfService;

    @GetMapping("/emp/sf/add")
    public String sf() {
        return "emp/sf/add";
    }

    @PostMapping("/emp/sf/getDateCanChooseMenu")
    @ResponseBody
    public List<Menu> getDateCanChooseMenu(String date) {
        List<Menu> menus = sfService.getMenus(date);
        System.out.println(menus);
        return menus;
    }

    @PostMapping("/emp/sf/insert")
    @ResponseBody
    public Msg insert(HttpServletRequest request,String date) throws SQLException {

        System.out.println(date);
        if(date != null && date != ""){
            if(DateUtil.compareToCurrentDate(date)){
                return new Msg(100,"选菜的时间应大于或等于当前时间");
            }
        }

        String[] menuIds = request.getParameterValues("menu");
        if (menuIds == null || menuIds.length < 0) {
            return new Msg(100, "至少选择一道菜");
        }

        List<SF> sfs = new ArrayList<>();
        SF sf =  null;
        for (String menuId : menuIds) {
            sf = new SF();
            sf.setMenu(new Menu(StringUtil.StringToInt(menuId)));
            sf.setEmp((Emp)request.getSession().getAttribute("emp"));
            if (date != null && date != "") {
                sf.setCreatedate(date);
            } else {
                sf.setCreatedate(DateUtil.getDate());
            }
            sfs.add(sf);
        }

        if(sfService.add(sfs) < sfs.size()){
            return new Msg(100,"添加失败");
        }
        return new Msg(200,"添加成功");
    }

    @GetMapping("/emp/sf/getDay")
    public String day(){
        return "emp/sf/today";
    }

    @GetMapping("/emp/sf/getWeek")
    public String week(){
        return "emp/sf/week";
    }

    @PostMapping("/emp/sf/today")
    @ResponseBody
    public List<SF> getToday(){

        System.out.println("today");
        List<SF> sfs = sfService.getToday();
        System.out.println(sfs);
        return sfs;
    }

    @PostMapping("/emp/sf/week")
    @ResponseBody
    public List<WeekMenu> getWeek(){
        return sfService.getWeek();
    }
}
