package com.bmsoft.canteensystem.controller;

import com.bmsoft.canteensystem.dto.ShoppingCarDto;
import com.bmsoft.canteensystem.entity.MatchMenu;
import com.bmsoft.canteensystem.entity.ShoppingCar;
import com.bmsoft.canteensystem.service.ShoppingCarService;
import com.bmsoft.canteensystem.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chengpeng
 * @create 2018-10-15 17:29
 */
@RestController
public class ShoppingCarController {
    @Autowired
    private ShoppingCarService shoppingCarService;

    @Autowired
    HttpServletRequest httpServletRequest;


    @RequestMapping(value = "/shoppingCar/add",method = RequestMethod.POST)
    public Msg add(@RequestBody ShoppingCar shoppingCar){
        Map<String,Object> map=new HashMap<>();
        map.put("userId",shoppingCar.getUserId());
        map.put("matchMenu",new MatchMenu().setMatchMenuId(shoppingCar.getMatchMenu().getMatchMenuId()));
        ShoppingCar shopCar = shoppingCarService.findByMatchMenuIdAndUserId(map);
        if (shopCar!=null){
            int totalNum=shopCar.getShoppingCarMenuNum()+shoppingCar.getShoppingCarMenuNum();
            boolean result = shoppingCarService.update(new ShoppingCar().setShoppingCarId(shopCar.getShoppingCarId()).setShoppingCarMenuNum(totalNum));
            if (result)
                return Msg.success();
            return Msg.fail();
        }
        if (shoppingCarService.add(shoppingCar))
            return Msg.success();
        return Msg.fail();
    }

    @RequestMapping(value = "/shoppingCar/delete/{shoppingCarId}",method = RequestMethod.GET)
    public Msg delete(@PathVariable("shoppingCarId")Integer shoppingCarId){
        if (shoppingCarService.delete(shoppingCarId))
            return Msg.success();
        return Msg.fail();
    }

    @RequestMapping(value = "/shoppingCar/list")
    public Msg list(Integer userId){
        List<ShoppingCarDto> shoppingCarDtos = shoppingCarService.list(userId);
        return Msg.success().add("shoppingCarInfo",shoppingCarDtos);
    }

    @RequestMapping(value = "/shoppingCar/update",method = RequestMethod.POST)
    public Msg update(@RequestBody ShoppingCar shoppingCar){
        if (shoppingCarService.update(shoppingCar))
            return Msg.success();
        return Msg.fail();
    }
}
