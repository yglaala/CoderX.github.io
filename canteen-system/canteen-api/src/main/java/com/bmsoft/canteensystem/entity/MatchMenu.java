package com.bmsoft.canteensystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author guoguo
 * 菜品搭配
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class MatchMenu implements Serializable {

    private Integer matchMenuId;//id
    private Menu menu;//菜品
    private String matchMenuDate;//菜品搭配时间
    private String matchMenuTime;//哪一顿
    private Integer matchMenuNum;//数量
}
