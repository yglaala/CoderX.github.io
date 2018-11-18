package com.bmsoft.canteensystem.service.impl;

import com.bmsoft.canteensystem.dto.QueryMenuInfoDto;
import com.bmsoft.canteensystem.entity.Shop;
import com.bmsoft.canteensystem.mapper.CollocationOfDishesMapper;
import com.bmsoft.canteensystem.mapper.ShopMapper;
import com.bmsoft.canteensystem.service.CollocationOfDishesService;
import com.bmsoft.canteensystem.util.DateUtil;
import com.bmsoft.canteensystem.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author liugaoyang
 * @Date 2018/10/19 09:59
 */
@Service
public class CollocationOfDishesServiceImpl implements CollocationOfDishesService {

    @Autowired
    ShopMapper shopMapper;

    @Autowired
    CollocationOfDishesMapper collocationOfDishesMapper;

    private Msg msg;


    /**
     * 功能说明：
     * 获取某个店铺某个日期某个时间段搭配的菜品
     * @param shopId 店铺id
     * @param matchMenuDate 日期
     * @param matchMenuTime 时间段
     * @return
     * @author liugaoyang
     */
    @Override
    public Msg listOfDishes(Integer shopId, Date matchMenuDate, String matchMenuTime) {
        //获取当前时间
        Date now = new Date();
        //获取商家设置每个时间段最晚订餐时间
        Shop shop = shopMapper.findByShopId(shopId);
        Date date;
        switch (matchMenuTime){
            case "早餐":
                //将搭配菜品日期和时间组合出可操作的时间
                date = DateUtil.getDate(matchMenuDate,shop.getShopMorning());
                break;
            case "午餐":
                //将搭配菜品日期和时间组合出可操作的时间
                date = DateUtil.getDate(matchMenuDate,shop.getShopNoon());
                break;
            /*case "晚餐"*/
            default:
                //将搭配菜品日期和时间组合出可操作的时间
                date = DateUtil.getDate(matchMenuDate,shop.getShopEvening());
                break;
        }
        //比较当前时间和可操作时间返回（0-相等，-1-小于，1-大于）
        int compareTo = now.compareTo(date);
        switch (compareTo){
            case -1:
               msg = Msg.success().setMsg("可以操作");
               break;
            case 1:
                msg = Msg.fail().setMsg("不可以操作");
                break;
            default:
                msg = Msg.fail().setMsg("不可以操作");
        }
        //Date类型转换
        java.sql.Date sqlDate = new java.sql.Date(matchMenuDate.getTime());
        msg.add("collocationOfDishes",collocationOfDishesMapper.listOfDishes(shopId,sqlDate,matchMenuTime));

        return msg;
    }



    /**
     * 功能说明：
     * 查询未来7天内的指定商品
     * @param menuName
     * @return
     */
    @Override
    public List<QueryMenuInfoDto> querMenu(String menuName) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date getNowDate = new Date();
        String nowDate = sdf.format(getNowDate);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 7);
        Date getToDate = calendar.getTime();
        String toDate = sdf.format(getToDate);
        return collocationOfDishesMapper.queryMenuInfo(menuName,nowDate,toDate);
    }
}
