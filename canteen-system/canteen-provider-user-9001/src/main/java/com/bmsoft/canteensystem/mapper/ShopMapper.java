package com.bmsoft.canteensystem.mapper;
/**
 * USER-PROVIDER ShopMapper
 * @Author liugaoyang
 */
import com.bmsoft.canteensystem.entity.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ShopMapper {

    /**
     * 通过userId获取Shop对象
     * @param userId
     * @return
     */
    @Select("SELECT * FROM tb_shop WHERE user_id = #{userId}")
    public Shop findByUserId(Integer userId);
}
