package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.entity.Shop;

import java.util.Map;

/**
 * 店铺信息Service
 */
public interface ShopService {

    /**
     * 通过用户id查找店铺
     * @param id
     * @return
     */
    public Shop findByUserId(Integer id);

    /**
     * 通过店铺名查找店铺信息
     * @param shopName
     * @return
     */
    public Shop findByShopName(String shopName);

    /**
     * 通过用户id、店铺名查询
     * @param map
     * @return
     */
    public Shop findByShopUserId(Map<String,Object> map);

    /**
     * 添加店铺信息
     * @param shop
     * @return
     */
    public int add(Shop shop);

    /**
     * 修改店铺信息
     * @param shop
     * @return
     */
    public int update(Shop shop);

    /**
     * 根据菜品id查找店铺信息
     * @param id
     * @return
     */
    public Shop searchByMenuId(Integer id);
}
