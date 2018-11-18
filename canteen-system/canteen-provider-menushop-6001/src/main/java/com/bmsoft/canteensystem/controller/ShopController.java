package com.bmsoft.canteensystem.controller;

import com.bmsoft.canteensystem.entity.Shop;
import com.bmsoft.canteensystem.entity.User;
import com.bmsoft.canteensystem.service.ShopService;
import com.bmsoft.canteensystem.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 店铺信息Controller层
 */
@RestController
public class ShopController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ShopService shopService;
    private Msg msg;

    /**
     * 通过用户id查找店铺信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/findByUserId")
    public Msg findByUserId(@RequestParam(value = "userId")Integer userId)throws Exception{
        Shop shop=shopService.findByUserId(userId);
        System.out.println(userId);
       return msg=Msg.success().add("shop",shop);
    }

    /**
     * 添加店铺信息
     * @param shop
     * @return
     * @throws Exception
     */
    @RequestMapping("/add")
    public Msg add(@RequestBody Shop shop){
        Msg msg=new Msg();
        HttpSession session=request.getSession();
        shop.setUser(shop.getUser());
        if(shopService.findByShopName(shop.getShopName())!=null){
            return msg.setMsg("已有该店铺，请勿重复添加！");
        }
        if(shopService.add(shop)<1){
            return msg.setMsg("添加店铺失败！").setCode(200);
        }
      return msg.setMsg("添加店铺成功！").setCode(100).add("userShopInfo",shopService.findByUserId(shop.getUser().getUserId()).setUser(null));
    }

    /**
     * 更新店铺信息
     * @param shop
     * @return
     * @throws Exception
     */
    @RequestMapping("/update")
    public Msg update(@RequestBody Shop shop)throws  Exception{
        Msg msg=new Msg();
        Map<String,Object> map=new HashMap<>();
        map.put("userId",shop.getUser().getUserId());
        map.put("shopName",shop.getShopName());
        map.put("shopId",shop.getShopId());
        if(shopService.findByUserId(shop.getUser().getUserId())==null){
            return msg.setMsg("未查询到该店铺信息！");
        }
        if (shopService.findByShopUserId(map)!=null){
            return msg.setMsg("店铺名重复，请换一个！").setCode(1);
        }
        if(shopService.update(shop)<1){
            msg.setMsg("更新店铺失败！").setCode(200);
        }
        return msg.setMsg("更新店铺成功！").setCode(100);
    }


}
