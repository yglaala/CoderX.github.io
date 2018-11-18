package com.bmsoft.canteensystem.mapper;

import com.bmsoft.canteensystem.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author liugaoyang
 * @Date 2018/10/20 10:49
 */
@Mapper
public interface MenuMapper {

    @Select("SELECT * FROM tb_menu WHERE shop_id = #{shopId}")
    public List<Menu> findByShopId(Integer shopId);
}
