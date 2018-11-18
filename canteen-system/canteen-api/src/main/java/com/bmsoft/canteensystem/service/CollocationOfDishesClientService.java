package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.entity.Shop;
import com.bmsoft.canteensystem.util.Msg;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * @Author liugaoyang
 * @Date 2018/10/22 11:39
 */
@FeignClient(value = "COLLOCATIONOFDISHES-PROVIDER",fallbackFactory = CollocationOfDishesClientServiceFallbackFactory.class)
public interface CollocationOfDishesClientService {

    /**
     * 功能说明：
     * 添加菜品搭配
     * @param jsonMatchMenus
     * @param seven
     * @return
     */
    @RequestMapping(value = "/collocationofdishes/addmatchmenu",method = RequestMethod.POST)
    public Msg addMatchMenu(@RequestParam("jsonMatchMenus") String jsonMatchMenus,@RequestParam("seven") boolean seven);

    /**
     * 功能说明：
     * 删除菜品搭配
     * @param matchMenuId
     * @param seven
     * @return
     */
    @RequestMapping(value = "/collocationofdishes/delmatchmenu",method = RequestMethod.POST)
    public Msg delMatchMenu(@RequestParam("matchMenuId") Integer matchMenuId,@RequestParam("seven") boolean seven);

    /**
     * 功能说明：
     * 修改菜品搭配的数量
     * @param matchMenuId
     * @param matchMenuNum
     * @param seven
     * @return
     */
    @RequestMapping(value = "/collocationofdishes/updatematchmenunum",method = RequestMethod.POST)
    public Msg updateMatchMenuNum(@RequestParam("matchMenuId") Integer matchMenuId,@RequestParam("matchMenuNum") Integer matchMenuNum,@RequestParam("seven") boolean seven);

    /**
     * 功能说明：
     * 查询未来7天内的指定商品
     * @param menuName
     * @return
     */
    @RequestMapping(value = "/collocationofdishes/querymenu",method = RequestMethod.POST)
    public Msg queryMenu(@RequestParam("menuName") String menuName);

    /**
     * 功能说明：
     * 获取店铺的所有菜品
     * @return
     */
    @RequestMapping(value = "/collocationofdishes/menulist",method = RequestMethod.GET)
    public Msg menuList(@RequestBody Shop userShopInfo);

    /**
     * 功能描述:
     * 获取所有商铺名称
     * @auther: liugaoyang
     * @date:
     */
    @RequestMapping(value = "/collocationofdishes/shoplist",method = RequestMethod.GET)
    public Msg shopList();

    /**
     * 功能描述:
     * 获取菜品搭配列表
     * @param shopId 商店id
     * @param matchMenuDate 搭配菜品日期
     *
     * @return
     * @author liugaoyang
     */
    @RequestMapping(value = "/collocationofdishes/listofdishes",method = RequestMethod.POST)
    public Msg listOfDishes(@RequestParam("shopId") Integer shopId,@RequestParam("matchMenuDate") Date matchMenuDate);
}
