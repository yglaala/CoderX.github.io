package com.bmsoft.canteensystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author chengpeng
 * @create 2018-10-14 20:07
 * 订单状态实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class OrderStatus implements Serializable {

    private Integer orderStatusId;//订单状态id
    private String orderStatusName;//订单状态名
}
