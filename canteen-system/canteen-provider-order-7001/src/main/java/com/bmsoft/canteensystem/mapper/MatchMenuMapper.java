package com.bmsoft.canteensystem.mapper;

import com.bmsoft.canteensystem.dto.MatchMenuDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.Map;

@Mapper
public interface MatchMenuMapper {

    @UpdateProvider(type = MatchMenuMapperPovider.class , method = "updateMatchMenuTime")
    int updateMatchMenuTime(Map<String, Object> params);

    @Select("SELECT tb_shop.*,tb_menu.menu_name,tb_match_menu.match_menu_date,tb_match_menu.match_menu_time" +
            " FROM tb_match_menu,tb_shop,tb_menu" +
            " WHERE tb_match_menu.menu_id = tb_menu.menu_id" +
            " AND tb_menu.shop_id = tb_shop.shop_id" +
            " AND match_menu_id = #{matchMenuId}")
    MatchMenuDto selectMatchMenuById(Integer matchMenuId);

    class MatchMenuMapperPovider{

        public String updateMatchMenuTime(Map<String,Object> params) {

            String sql = "update tb_match_menu set" +
                    " match_menu_num = match_menu_num+'"+params.get("orderMenuNum")+"'" +
                    " where match_menu_id = '"+params.get("matchMenuId")+"'";
            //System.out.println(sql);
            return sql;
        }
    }


}
