package com.bmsoft.canteensystem.mapper;

import com.bmsoft.canteensystem.dto.ListOfDishesDto;
import com.bmsoft.canteensystem.dto.QueryMenuInfoDto;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.sql.Date;
import java.util.List;

/**
 * @Author liugaoyang
 * @Date 2018/10/19 15:08
 */
@Mapper
public interface CollocationOfDishesMapper {

    /**
     * 查询xx店铺xx日期xx时间段的所有菜品的相关信息
     * @param shopId 商铺id
     * @param matchMenuDate 菜品日期
     * @param matchMenuTime 菜品时间段
     * @return
     */
    @SelectProvider(type = CollocationOfDishesMapperProvider.class,method = "getListOfDishes")
     List<ListOfDishesDto> listOfDishes(Integer shopId, Date matchMenuDate, String matchMenuTime);


    /**
     *
     * @param menuName
     * @param nowDate
     * @param toDate
     * @return
     */
    @SelectProvider(type = CollocationOfDishesMapperProvider.class,method = "getQueryMenuInfo")
    List<QueryMenuInfoDto> queryMenuInfo(String menuName, String nowDate, String toDate);


    class CollocationOfDishesMapperProvider{
        public String getListOfDishes(Integer shopId, Date matchMenuDate, String matchMenuTime){
            String sql = "SELECT\n" +
                    "\tc.*,menu_avg_rating,sum(IFNULL(menu_sale_num,0)) menu_sale_sum_num\n" +
                    "FROM\n" +
                    "\t(\n" +
                    "\t\tSELECT\n" +
                    "\t\t\tm.*, mm.match_menu_num,\n" +
                    "\t\t\tmm.match_menu_id\n" +
                    "\t\tFROM\n" +
                    "\t\t\t(\n" +
                    "\t\t\t\tSELECT\n" +
                    "\t\t\t\t\tmenu_id,\n" +
                    "\t\t\t\t\tmenu_price,\n" +
                    "\t\t\t\t\tmenu_name,\n" +
                    "\t\t\t\t\tmenu_pic_path\n" +
                    "\t\t\t\tFROM\n" +
                    "\t\t\t\t\ttb_menu\n" +
                    "\t\t\t\tWHERE\n" +
                    "\t\t\t\t\tshop_id = "+shopId+"\n" +
                    "\t\t\t) m,\n" +
                    "\t\t\t(\n" +
                    "\t\t\t\tSELECT\n" +
                    "\t\t\t\t\tmenu_id,\n" +
                    "\t\t\t\t\tmatch_menu_num,\n" +
                    "\t\t\t\t\tmatch_menu_id\n" +
                    "\t\t\t\tFROM\n" +
                    "\t\t\t\t\ttb_match_menu\n" +
                    "\t\t\t\tWHERE\n" +
                    "\t\t\t\t\tmatch_menu_date = '"+matchMenuDate+"'\n" +
                    "\t\t\t\tAND match_menu_time = '"+matchMenuTime+"'\n" +
                    "\t\t\t) mm\n" +
                    "\t\tWHERE\n" +
                    "\t\t\tm.menu_id = mm.menu_id\n" +
                    "\t) c\n" +
                    "LEFT OUTER JOIN (\n" +
                    "\tSELECT\n" +
                    "\t\tmenu_id,\n" +
                    "\t\tFORMAT(AVG(menu_rating), 1) menu_avg_rating\n" +
                    "\tFROM\n" +
                    "\t\ttb_menu_sug\n" +
                    "\tGROUP BY\n" +
                    "\t\tmenu_id\n" +
                    ") ms ON c.menu_id = ms.menu_id\n"+
                    "LEFT JOIN tb_menu_sale s ON c.menu_id = s.menu_id\n" +
                    "GROUP BY\n" +
                    "\tmenu_id\n"+
                    "ORDER BY\n" +
                    "\ts.menu_sale_num DESC";
            return sql;
        }

        public String getQueryMenuInfo(String menuName, String nowDate, String toDate){
            String sql = "SELECT s.shop_id,s.shop_name,mm.match_menu_date,mm.match_menu_time FROM tb_menu m\n" +
                    "LEFT JOIN tb_match_menu mm \n" +
                    "ON m.menu_id=mm.menu_id \n" +
                    "LEFT JOIN tb_shop s \n" +
                    "ON m.shop_id=s.shop_id \n" +
                    "WHERE m.menu_name = '"+menuName+"' \n" +
                    "AND mm.match_menu_date >= '"+nowDate+"' \n"+
                    "AND mm.match_menu_date <= '"+toDate+"'";
            return sql;
        }
    }
}
