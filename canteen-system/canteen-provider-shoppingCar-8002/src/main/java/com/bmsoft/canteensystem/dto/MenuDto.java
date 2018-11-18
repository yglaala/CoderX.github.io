package com.bmsoft.canteensystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author chengpeng
 * @create 2018-10-17 15:22
 * 购物车体信息传输类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class MenuDto {
    private Integer shoppingCarId;
    private Integer matchMenuId;
    private String menuName;
    private Integer matchMenuNum;
    private String menuPicPath;
    private Integer shoppingCarMenuNum;
    private Integer menuPrice;
    private String menuPropertiesCategoryName;
    private String menuPropertiesStyleName;
    private String menuPropertiesTasteName;
    private String statusInfo;
}
