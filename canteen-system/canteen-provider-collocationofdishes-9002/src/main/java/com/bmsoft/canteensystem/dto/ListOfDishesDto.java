package com.bmsoft.canteensystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author liugaoyang
 * @Date 2018/10/19 14:59
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class ListOfDishesDto implements Serializable {
    private Integer menuId;//菜品id
    private Integer matchMenuId;//菜品搭配id
    private Integer matchMenuNum;//菜品数量
    private Integer menuSaleSumNum;//商品总销量
    private float menuPrice;//菜品价格
    private float menuAvgRating;//菜品平均评价
    private String menuName;//菜品名称
    private String menuPicPath;//菜品图片路径

}
