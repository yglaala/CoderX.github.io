package com.bmsoft.canteensystem.controller;

import com.bmsoft.canteensystem.entity.ShoppingCar;
import com.bmsoft.canteensystem.service.ShoppingCarClientService;
import com.bmsoft.canteensystem.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

/**
 * @author chengpeng
 * @create 2018-10-17 19:57
 */
@RestController
public class ShoppingCarClientController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ShoppingCarClientService shoppingCarClientService;

    @RequestMapping(value="/shopCar/add",method = RequestMethod.POST)
    public Msg add(@ModelAttribute("ShoppingCar") ShoppingCar shoppingCar){
        HttpSession session = request.getSession();
        LinkedHashMap user = (LinkedHashMap) session.getAttribute("userInfo");
        shoppingCar.setUserId((Integer) user.get("userId"));
        return shoppingCarClientService.add(shoppingCar);
    }

    @RequestMapping(value="/shopCar/delete/{shoppingCarId}",method = RequestMethod.GET)
    public Msg delete(@PathVariable("shoppingCarId")Integer shoppingCarId){
        return shoppingCarClientService.delete(shoppingCarId);
    }

    @RequestMapping(value="/shopCar/list")
    public Msg list(){
        HttpSession session = request.getSession();
        LinkedHashMap user = (LinkedHashMap) session.getAttribute("userInfo");
        return shoppingCarClientService.list((Integer) user.get("userId"));
    }

    @RequestMapping(value="/shopCar/update",method = RequestMethod.POST)
    public Msg update(ShoppingCar shoppingCar){
        return shoppingCarClientService.update(shoppingCar);
    }

}
