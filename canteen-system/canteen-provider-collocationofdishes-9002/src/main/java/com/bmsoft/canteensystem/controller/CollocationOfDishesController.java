package com.bmsoft.canteensystem.controller;
/**
 * COLLOCATIONOFDISHES-PROVIDER CollocationOfDishesController层
 * @Author liugaoyang
 */
import com.alibaba.fastjson.JSON;
import com.bmsoft.canteensystem.dto.QueryMenuInfoDto;
import com.bmsoft.canteensystem.entity.MatchMenu;
import com.bmsoft.canteensystem.entity.Menu;
import com.bmsoft.canteensystem.entity.Shop;
import com.bmsoft.canteensystem.service.CollocationOfDishesService;
import com.bmsoft.canteensystem.service.MatchMenuService;
import com.bmsoft.canteensystem.service.MenuService;
import com.bmsoft.canteensystem.service.ShopService;
import com.bmsoft.canteensystem.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@RestController
public class CollocationOfDishesController {

    @Autowired
    private CollocationOfDishesService collocationOfDishesService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private ShopService shopService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private MatchMenuService matchMenuService;

    private Msg msg;


    /**
     * 功能说明：
     * 添加菜品搭配
     * @param jsonMatchMenus
     * @param seven
     * @return
     */
    @RequestMapping(value = "/collocationofdishes/addmatchmenu")
    public Msg addMatchMenu(String jsonMatchMenus, boolean seven){

        List<MatchMenu> matchMenuList = JSON.parseArray(jsonMatchMenus,MatchMenu.class);
        if (matchMenuList == null){
            msg = Msg.fail().setMsg("没有添加的数据");
            return msg;
        }
        msg = matchMenuService.addMatchMenu(matchMenuList,seven);
        return msg;
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
        if (seven == false){
            matchMenuService.delMatchMenu(matchMenuId);
        }
        if (seven == true){
            matchMenuService.delSevenMatchMenu(matchMenuId);
        }
        msg = Msg.success().setMsg("删除成功");
        return msg;
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
    public Msg updateMatchMenuNum(Integer matchMenuId,Integer matchMenuNum,boolean seven){
        //判断是否勾选接下7天匹配
        if (seven == false){
            matchMenuService.updateMatchMenuNum(matchMenuId,matchMenuNum);
        }
        if (seven == true){
            matchMenuService.updateSevenMatchMenuNum(matchMenuId,matchMenuNum);
        }
        msg = Msg.success().setMsg("修改成功");
        return msg;
    }

    /**
     * 功能说明：
     * 查询未来7天内的指定商品
     * @param menuName
     * @return
     */
    @RequestMapping(value = "/collocationofdishes/querymenu")
    public Msg queryMenu(String menuName){
        List<QueryMenuInfoDto> menuInfoList = collocationOfDishesService.querMenu(menuName);
        if (menuInfoList.size() == 0){
            msg = Msg.fail().setMsg("最近7天内都没有该菜品");
            return msg;
        }
        msg = Msg.success().setMsg("最近7天内有该菜品").add("menuInfoList",menuInfoList);
        return msg;
    }

    /**
     * 功能说明：
     * 获取店铺的所有菜品
     * @return
     */
    @RequestMapping(value = "/collocationofdishes/menulist")
    public Msg menuList(@RequestBody Shop userShopInfo){
        List<Menu> menuList = menuService.menuList(userShopInfo.getShopId());
        if (menuList == null){
            msg = Msg.fail().setMsg("该商铺没有添加菜品信息，请先添加菜品信息");
            return msg;
        }
        msg = Msg.success().add("menuList",menuList);
        return msg;
    }
    /**
     * 功能描述: 
     * 获取所有商铺名称
     * @auther: liugaoyang
     * @date:
     */
    @RequestMapping(value = "/collocationofdishes/shoplist")
    public Msg shopList(){
        List shopList = shopService.ShopList();
        if (shopList == null){
            msg = Msg.fail().setMsg("没有商铺");
        }
        msg = Msg.success().add("shopList",shopList);
        return msg;
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
        System.out.println(matchMenuDate.toString());
        String[]  matchMenuTime = {"早餐","午餐","晚餐"};
        Msg moringMsg = collocationOfDishesService.listOfDishes(shopId,matchMenuDate,matchMenuTime[0]);
        Msg noonMsg = collocationOfDishesService.listOfDishes(shopId,matchMenuDate,matchMenuTime[1]);
        Msg eveningMsg = collocationOfDishesService.listOfDishes(shopId,matchMenuDate,matchMenuTime[2]);
        msg=Msg.success().add("moring",moringMsg).add("noon",noonMsg).add("evening",eveningMsg);
        return msg;
    }

}
