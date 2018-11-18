package com.bmsoft.canteensystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author chengpeng
 * @create 2018-10-14 20:25
 * 菜品评价实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class MenuSug  implements Serializable {

     private String orderNo;
    private Integer menuSugId;//菜品评价id
    private User user;//用户id
    private Menu menu;//菜品id
    private String sugContent;//评价信息
    private Double MenuRating;//评分
    private String menuSugCreateDate;//评价创建时间
}
