package com.bmsoft.canteensystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author chengpeng
 * @create 2018-10-14 20:11
 * 口味数据字典实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class MenuPropertiesTaste implements Serializable {
    private Integer menuPropertiesTasteId;//口味id
    private String menuPropertiesTasteName;//口味名
}
