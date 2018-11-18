package com.bmsoft.canteensystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 店铺表
 * @author guoguo
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Shop  implements Serializable {

    private Integer shopId;//店铺id
    private String shopName;//店铺名
    private String shopMorning;//当日早餐最晚下单时间
    private String shopNoon;//当日午餐最晚下单时间
    private String shopEvening;//当日晚餐最晚下单时间
    private User user;//用户
}
