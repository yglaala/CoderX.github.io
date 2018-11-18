package com.bmsoft.canteensystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author chengpeng
 * @create 2018-10-14 20:33
 * 菜品实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Menu implements Serializable {

    private Integer menuId;//菜品id
    private String menuNo;//菜品编号
    private Double menuPrice;//菜品价格
    private String menuName;//菜品名
    private MenuPropertiesCategory menuPropertiesCategory;//种类id
    private MenuPropertiesStyle menuPropertiesStyle;//菜系id
    private MenuPropertiesTaste menuPropertiesTaste;//口味id
    private String menuPicPath;//菜品图片url
    private Shop shop;//店铺id
}
