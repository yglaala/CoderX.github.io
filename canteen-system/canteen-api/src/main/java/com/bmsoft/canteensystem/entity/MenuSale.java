package com.bmsoft.canteensystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author chengpeng
 * @create 2018-10-14 20:20
 * 销售数量实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class MenuSale  implements Serializable {

    private Integer menuSaleId;//销售数量id
    private Menu menu;//菜品id
    private Integer MenuSaleNum;//销售数量
}
