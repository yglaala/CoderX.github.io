package com.bmsoft.canteensystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author guoguo
 * 订单实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Order  implements Serializable {
    private String orderNo;
    //private String orderBookDate;//
    private OrderStatus orderStatus ;//订单状态id
    private User user;//用户id
    private String orderCreateDate;//订单创建时间
    private Integer orderPayStatus;//
    private String orderPayDate;//
    private List<OrderMenu> orderMenus;
}
