package com.bmsoft.canteensystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author chengpeng
 * @create 2018-10-14 20:14
 * 烹饪方式实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class MenuPropertiesCategory implements Serializable {
    private Integer menuPropertiesCategroyId;//种类id(烹饪方式)
    private String menuPropertiesCategroyName;//烹饪方式名字
}
