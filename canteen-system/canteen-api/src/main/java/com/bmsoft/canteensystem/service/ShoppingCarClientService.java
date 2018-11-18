package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.entity.ShoppingCar;
import com.bmsoft.canteensystem.util.Msg;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author chengpeng
 * @create 2018-10-17 19:38
 */
@FeignClient(value = "SHOPPINGCAR-PROVIDER",fallbackFactory = ShoppingCarClientFactory.class)
public interface ShoppingCarClientService {
    @RequestMapping(value = "/shoppingCar/add",method = RequestMethod.POST)
    Msg add(@RequestBody ShoppingCar shoppingCar);

    @RequestMapping(value = "/shoppingCar/delete/{shoppingCarId}",method = RequestMethod.GET)
    Msg delete(@PathVariable("shoppingCarId") Integer shoppingCarId);

    @RequestMapping(value = "/shoppingCar/list",method = RequestMethod.GET)
    Msg list(@RequestParam("userId") Integer userId);

    @RequestMapping(value = "/shoppingCar/update",method = RequestMethod.POST)
    Msg update(ShoppingCar shoppingCar);
}
