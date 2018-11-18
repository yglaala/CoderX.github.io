package com.bmsoft.canteensystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author chengpeng
 * @create 2018-10-14 20:16
 * 购物车实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class ShoppingCar implements Serializable {

    private Integer shoppingCarId;//购物车id
    private MatchMenu matchMenu;//搭配菜品id
    private Integer shoppingCarMenuNum;//购买数量
    private Integer userId;//用户id
}
