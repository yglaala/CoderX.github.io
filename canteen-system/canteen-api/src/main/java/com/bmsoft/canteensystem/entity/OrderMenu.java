package com.bmsoft.canteensystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author chengpeng
 * @create 2018-10-14 20:45
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class OrderMenu  implements Serializable {

    private Integer orderMenuId;
    private String orderNo;
    private MatchMenu matchMenu;//菜品搭配
    private Integer orderMenuNum;
}
