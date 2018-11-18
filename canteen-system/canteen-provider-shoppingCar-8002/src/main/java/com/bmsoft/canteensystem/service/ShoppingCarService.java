package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.dto.ShoppingCarDto;
import com.bmsoft.canteensystem.entity.ShoppingCar;

import java.util.List;
import java.util.Map;

/**
 * @author chengpeng
 * @create 2018-10-15 17:25
 * 购物车Service接口
 */
public interface ShoppingCarService {

    /**
     * 功能描述:根据搭配菜品id和用户id查询购物车信息
     * @param   map
     * @return com.bmsoft.canteensystem.entity.ShoppingCar
     */
    ShoppingCar findByMatchMenuIdAndUserId(Map<String,Object> map);
    /**
     * 功能描述:添加菜品到购物车
     * @param  * @param shoppingCar
     * @return boolean
     */
    boolean add(ShoppingCar shoppingCar);

    /**
     * 功能描述:根据购物车id删除购物车信息
     * @param  * @param shoppingCarId 
     * @return boolean
     */
    boolean delete(Integer shoppingCarId);

    /**
     * 功能描述:更新购物车数量
     * @param  * @param shoppingCar 
     * @return boolean
     */
    boolean update(ShoppingCar shoppingCar);

    /**
     * 功能描述:遍历购物车信息
     * @param  * @param userId 
     * @return java.util.List<com.bmsoft.canteensystem.dto.ShoppingCarDto>
     */
    List<ShoppingCarDto> list(Integer userId);
}
