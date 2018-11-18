package com.bmsoft.canteensystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderDto {

    private String orderNo;
    private String empName;
    private String shopName;
    private Integer orderStatusId;
    private String orderStatusName;
    private String bookingDate;
    private String whichMeal;
    private String orderCreateDate;
    private List<OrderMenuDto> orderMenuDtos;
    private String orderPayDate;
    //private String orderPayStatus;
    private Float totalPrice;
    private Map<String,Object> btns;

    public void addBtns(Integer roleId){

        btns = new HashMap<>();
        //管理员
        if(roleId == 1){
            //待接单
            if(orderStatusId == 1){
                btns.put("btn1Name","接受订单");
                btns.put("btn1Url","/order/update/2");
                btns.put("btn2Name","拒绝订单");
                btns.put("btn2Url","/order/update/3");
            }
            //已接单
            else if(orderStatusId == 2){
                btns.put("btn1Name","确认消费");
                btns.put("btn1Url","/order/update/4");
                btns.put("btn2Name","取消订单");
                btns.put("btn2Url","/order/update/5");
            }
            //员工已删除订单
            else if(orderStatusId > 10 && orderStatusId <15){
                btns.put("btn1Name","删除订单");
                btns.put("btn1Url","/order/update/16");
            }
            else {
                btns.put("btn1Name","删除订单");
                btns.put("btn1Url","/order/update/"+(orderStatusId+4)+"");
            }
        }
        else {
            if(orderStatusId == 1 || orderStatusId ==2){
                btns.put("btn1Name","取消订单");
                btns.put("btn1Url","/order/update/6");
            }else if(orderStatusId > 6 && orderStatusId < 11){
                btns.put("btn1Name","删除订单");
                btns.put("btn1Url","/order/update/16");
            } else {
                btns.put("btn1Name","删除订单");
                btns.put("btn1Url","/order/update/"+(orderStatusId+8)+"");
            }
            if(orderPayDate != "" && orderPayDate != null){
                btns.put("btn1Name","评价订单菜品");
                btns.put("btn1Url","/order/menus");
                btns.put("btn2Name","删除订单");
                if(orderStatusId > 6 && orderStatusId < 11){
                    btns.put("btn2Url","/order/update/16");
                }else {
                    btns.put("btn2Url","/order/update/"+(orderStatusId+8)+"");
                }
            }
        }

    }
}
