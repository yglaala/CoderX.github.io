package com.bmsoft.canteensystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author chengpeng
 * @create 2018-10-17 15:20
 * 购物车头信息传输类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class ShoppingCarDto {

    private Integer shopId;
    private String shopName;
    private String matchMenuDate;
    private String matchMenuTime;
    private List<MenuDto> menuDtos;

}
