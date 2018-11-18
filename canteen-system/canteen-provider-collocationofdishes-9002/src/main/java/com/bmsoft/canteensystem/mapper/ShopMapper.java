package com.bmsoft.canteensystem.mapper;
/**
 * COLLOCATIONOFDISHES-PROVIDER ShopMapper层
 * @Author liugaoyang
 */
import com.bmsoft.canteensystem.entity.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShopMapper {

    @Select("SELECT * FROM tb_shop")
    public List<Shop> findAll();

    @Select("SELECT * FROM tb_shop WHERE shop_id = #{shopId}")
    public Shop findByShopId(Integer shopId);
}
