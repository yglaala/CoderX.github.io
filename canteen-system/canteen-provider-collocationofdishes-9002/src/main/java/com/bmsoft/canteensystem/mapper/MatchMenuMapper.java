package com.bmsoft.canteensystem.mapper;


import com.bmsoft.canteensystem.dto.MatchMenuInfoDto;
import com.bmsoft.canteensystem.entity.MatchMenu;
import org.apache.ibatis.annotations.*;

/**
 * @Author liugaoyang
 * @Date 2018/10/21 20:46
 */
@Mapper
public interface MatchMenuMapper {

    @UpdateProvider(type = MatchMenuMapperProvider.class,method = "getUpdateMatchMenuNumByMatchMenuId")
    public void updateMatchMenuNumByMatchMenuId(Integer matchMenuId, Integer matchMenuNum);

    @Select("SELECT * FROM tb_match_menu WHERE match_menu_id = #{matchMenuId}")
    public MatchMenuInfoDto findByMatchId(Integer matchMenuId);

    @UpdateProvider(type = MatchMenuMapperProvider.class,method = "getUpdateMatchMenuNum")
    public void updateMatchMenuNum(Integer menuId, String matchMenuTime, String matchMenuDate, Integer matchMenuNum);

    @Delete("DELETE FROM tb_match_menu WHERE match_menu_id = #{matchMenuId}")
    public void delMatchMenuByMacthMenuId(Integer matchMenuId);

    @DeleteProvider(type = MatchMenuMapperProvider.class,method = "getDelMatchMenu")
    public void delMatchMenu(Integer menuId, String matchMenuTime, String matchMenuDate);

    @Select("SELECT * FROM tb_match_menu WHERE menu_id = #{menu.menuId} AND match_menu_time = #{matchMenuTime} AND match_menu_date = #{matchMenuDate}")
    public MatchMenu findMatchMenu(MatchMenu matchMenu);

    @Insert("INSERT INTO tb_match_menu(menu_id,match_menu_time,match_menu_date,match_menu_num) VALUES (#{menu.menuId},#{matchMenuTime},#{matchMenuDate},#{matchMenuNum})")
    public void addMatchMenu(MatchMenu matchMenu);

    class MatchMenuMapperProvider{
        public String getUpdateMatchMenuNumByMatchMenuId(Integer matchMenuId, Integer matchMenuNum) {
            String sql = "UPDATE tb_match_menu SET match_menu_num = "+matchMenuNum+" WHERE match_menu_id = "+matchMenuId;
            return sql;
        }

        public String getUpdateMatchMenuNum(Integer menuId, String matchMenuTime, String matchMenuDate, Integer matchMenuNum) {
            String sql = "UPDATE tb_match_menu SET match_menu_num = "+matchMenuNum+" WHERE menu_id = "+menuId+" AND match_menu_time = '"+matchMenuTime+"' AND match_menu_date = '"+matchMenuDate+"'";
            return sql;
        }

        public String getDelMatchMenu(Integer menuId, String matchMenuTime, String matchMenuDate) {
            String sql = "DELETE FROM tb_match_menu WHERE menu_id = "+menuId+" AND match_menu_time = '"+matchMenuTime+"' AND match_menu_date = '"+matchMenuDate+"'";
            return sql;
        }

    }
}
