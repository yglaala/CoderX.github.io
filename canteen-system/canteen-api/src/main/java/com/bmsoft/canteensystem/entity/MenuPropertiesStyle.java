package com.bmsoft.canteensystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author chengpeng
 * @create 2018-10-14 20:13
 * 菜系实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class MenuPropertiesStyle implements Serializable {
    private Integer menuPropertiesStyleId;//菜系id
    private String menuPropertiesStyleName;//菜系名
}
