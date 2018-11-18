package com.bmsoft.canteensystem.controller;

import com.bmsoft.canteensystem.entity.Shop;
import com.bmsoft.canteensystem.service.CollocationOfDishesClientService;
import com.bmsoft.canteensystem.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * @Author liugaoyang
 * @Date 2018/10/22 14:27
 */
@RestController
public class CollocationOfDishes_Consumer {

    @Autowired
    HttpServletRequest request;

    @Autowired
    private CollocationOfDishesClientService collocationOfDishesClientService;
    /**
     * 功能说明：
     * 添加菜品搭配
     * @param jsonMatchMenus
     * @param seven
     * @return
     */
    @RequestMapping(value = "/collocationofdishes/addmatchmenu")
    public Msg addMatchMenu(String jsonMatchMenus,boolean seven){
        return collocationOfDishesClientService.addMatchMenu(jsonMatchMenus,seven);
    }

    /**
     * 功能说明：
     * 删除菜品搭配
     * @param matchMenuId
     * @param seven
     * @return
     */
    @RequestMapping(value = "/collocationofdishes/delmatchmenu")
    public Msg delMatchMenu(Integer matchMenuId,boolean seven){
        return collocationOfDishesClientService.delMatchMenu(matchMenuId, seven);
    }

    /**
     * 功能说明：
     * 修改菜品搭配的数量
     * @param matchMenuId
     * @param matchMenuNum
     * @param seven
     * @return
     */
    @RequestMapping(value = "/collocationofdishes/updatematchmenunum")
    public Msg updateMatchMenuNum(Integer matchMenuId, Integer matchMenuNum, boolean seven){
        return collocationOfDishesClientService.updateMatchMenuNum(matchMenuId, matchMenuNum, seven);
    }

    /**
     * 功能说明：
     * 查询未来7天内的指定商品
     * @param menuName
     * @return
     */
    @RequestMapping(value = "/collocationofdishes/querymenu")
    public Msg queryMenu(String menuName){
        return collocationOfDishesClientService.queryMenu(menuName);
    }

    /**
     * 功能说明：
     * 获取店铺的所有菜品
     * @return
     */
    @RequestMapping(value = "/collocationofdishes/menulist")
    public Msg menuList(){
        HttpSession session = request.getSession();
        //模拟session存入设置
//        session.setAttribute("userShopInfo",new Shop().setShopId(1));
        //在session中取出userShopInfo信息

        LinkedHashMap userShopInfoMap = (LinkedHashMap)session.getAttribute("userShopInfo");
//        System.out.println(userShopInfoMap.get("shopId"));
        Shop userShopInfo = new Shop().setShopId((Integer) userShopInfoMap.get("shopId"));
//        Shop userShopInfo = (Shop)session.getAttribute("userShopInfo");
        return collocationOfDishesClientService.menuList(userShopInfo);
    }

    /**
     * 功能描述:
     * 获取所有商铺名称
     * @auther: liugaoyang
     * @date:
     */
    @RequestMapping(value = "/collocationofdishes/shoplist")
    public Msg shopList(){
        return collocationOfDishesClientService.shopList();
    }

    /**
     * 功能描述:
     * 获取菜品搭配列表
     * @param shopId 商店id
     * @param matchMenuDate 搭配菜品日期
     *
     * @return
     * @author liugaoyang
     */
    @RequestMapping(value = "/collocationofdishes/listofdishes")
    public Msg listOfDishes(Integer shopId, Date matchMenuDate){
        return collocationOfDishesClientService.listOfDishes(shopId, matchMenuDate);
    }
}
