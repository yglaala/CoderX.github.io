package com.bmsoft.canteensystem.service.impl;

import com.bmsoft.canteensystem.dao.ShopDao;
import com.bmsoft.canteensystem.dao.ShoppingCarDao;
import com.bmsoft.canteensystem.dto.MenuDto;
import com.bmsoft.canteensystem.dto.ShoppingCarDto;
import com.bmsoft.canteensystem.entity.Shop;
import com.bmsoft.canteensystem.entity.ShoppingCar;
import com.bmsoft.canteensystem.service.ShoppingCarService;
import com.bmsoft.canteensystem.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author chengpeng
 * @create 2018-10-15 17:26
 */
@Service
public class ShoppingCarServiceImpl implements ShoppingCarService {
    @Autowired
    private ShoppingCarDao shoppingCarDao;
    @Autowired
    private ShopDao shopDao;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String newDate = sdf.format(new Date());

    @Override
    public ShoppingCar findByMatchMenuIdAndUserId(Map<String,Object> map) {
        return shoppingCarDao.findByMatchMenuIdAndUserId(map);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public boolean add(ShoppingCar shoppingCar) {
        boolean flag;
        try {
            flag=shoppingCarDao.add(shoppingCar);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return flag;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public boolean delete(Integer shoppingCarId) {
        boolean flag;
        try {
            flag=shoppingCarDao.delete(shoppingCarId);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return flag;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public boolean update(ShoppingCar shoppingCar) {
        boolean flag;
        try {
            flag=shoppingCarDao.update(shoppingCar);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return flag;
    }

    @Transactional(isolation = Isolation.DEFAULT)
    @Override
    public List<ShoppingCarDto> list(Integer userId) {
        List<ShoppingCarDto>  shoppingCarDtos= shoppingCarDao.findByUserId(userId);
        List<ShoppingCarDto> result=new LinkedList<>();
        for(ShoppingCarDto shoppingCarDto : shoppingCarDtos){
            Map<String , Object> params = new HashMap<>();
            Shop shop = shopDao.findById(shoppingCarDto.getShopId());
            params.put("userId",userId);
            params.put("shopName",shoppingCarDto.getShopName());
            params.put("matchMenuDate",shoppingCarDto.getMatchMenuDate());
            params.put("matchMenuTime",shoppingCarDto.getMatchMenuTime());
            ShoppingCarDto menus = shoppingCarDto.setMenuDtos(shoppingCarDao.getShoppingCarMenus(params));
            List<MenuDto> menuDtos = menus.getMenuDtos();
            for (MenuDto menuDto:menuDtos){
                if (newDate.compareTo(shoppingCarDto.getMatchMenuDate())>0){
                    menuDto.setStatusInfo("信息过期");
                }else if (newDate.compareTo(shoppingCarDto.getMatchMenuDate())==0){
                    if ("早餐".equals(shoppingCarDto.getMatchMenuTime())){
                        if (DateUtil.getTimeStr().compareTo(shop.getShopMorning())<0){
                            if (menuDto.getMatchMenuNum()>=menuDto.getShoppingCarMenuNum())
                                menuDto.setStatusInfo("状态良好");
                            else
                                menuDto.setStatusInfo("余量不足");
                        }else {
                            menuDto.setStatusInfo("不可下单");
                        }
                    }else if ("午餐".equals(shoppingCarDto.getMatchMenuTime())){
                        if (DateUtil.getTimeStr().compareTo(shop.getShopNoon())<0){
                            if (menuDto.getMatchMenuNum()>=menuDto.getShoppingCarMenuNum())
                                menuDto.setStatusInfo("状态良好");
                            else
                                menuDto.setStatusInfo("余量不足");
                        }else {
                            menuDto.setStatusInfo("不可下单");
                        }
                    }else if ("晚餐".equals(shoppingCarDto.getMatchMenuTime())){
                        if (DateUtil.getTimeStr().compareTo(shop.getShopEvening())<0){
                            if (menuDto.getMatchMenuNum()>=menuDto.getShoppingCarMenuNum())
                                menuDto.setStatusInfo("状态良好");
                            else
                                menuDto.setStatusInfo("余量不足");
                        }else {
                            menuDto.setStatusInfo("不可下单");
                        }
                    }
                }else {
                    if (menuDto.getMatchMenuNum()>=menuDto.getShoppingCarMenuNum())
                        menuDto.setStatusInfo("状态良好");
                    else
                        menuDto.setStatusInfo("余量不足");
                }
            }
            result.add(shoppingCarDto);
        }
        return result;
    }
}