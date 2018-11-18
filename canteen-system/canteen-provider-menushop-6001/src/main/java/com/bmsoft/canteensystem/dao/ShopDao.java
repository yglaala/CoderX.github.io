package com.bmsoft.canteensystem.dao;

import com.bmsoft.canteensystem.entity.Shop;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 店铺Dao层
 */
@Mapper
public interface ShopDao {

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
