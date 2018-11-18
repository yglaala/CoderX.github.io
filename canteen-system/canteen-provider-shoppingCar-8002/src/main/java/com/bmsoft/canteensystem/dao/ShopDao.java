package com.bmsoft.canteensystem.dao;

import com.bmsoft.canteensystem.entity.Shop;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chengpeng
 * @create 2018-10-16 8:28
 */
@Mapper
public interface ShopDao {
    /**
     * 功能描述:通过商铺id查询商铺信息
     * @param  * @param shopId 
     * @return com.bmsoft.canteensystem.entity.Shop
     */
    Shop findById(Integer shopId);
}
