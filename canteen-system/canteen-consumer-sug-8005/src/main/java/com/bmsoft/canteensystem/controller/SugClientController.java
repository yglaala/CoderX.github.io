package com.bmsoft.canteensystem.controller;

import com.bmsoft.canteensystem.entity.Sug;
import com.bmsoft.canteensystem.service.SugClientService;
import com.bmsoft.canteensystem.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

/**
 * @author chengpeng
 * @create 2018-10-18 19:19
 */
@RestController
public class SugClientController {
    @Autowired
    private SugClientService sugClientService;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/sug/list",method = RequestMethod.POST)
    public Msg list(@ModelAttribute("Sug") Sug sug, @RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        return sugClientService.list(sug,pageNum,pageSize);
    }

    @RequestMapping(value = "/sug/add",method = RequestMethod.POST)
    public Msg add(@ModelAttribute("Sug") Sug sug){
        HttpSession session = request.getSession();
        LinkedHashMap user = (LinkedHashMap) session.getAttribute("userInfo");
        sug.setUserId((Integer)user.get("userId"));
        return sugClientService.add(sug);
    }
}
