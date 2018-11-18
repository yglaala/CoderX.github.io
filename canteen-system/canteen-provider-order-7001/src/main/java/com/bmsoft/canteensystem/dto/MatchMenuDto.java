package com.bmsoft.canteensystem.dto;

import com.bmsoft.canteensystem.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class MatchMenuDto {

    private static final int PRE_TIME = 2;
    //0 过期
    //1 没过期
    private String shopMorning;
    private String shopNoon;
    private String shopEvening;
    private String matchMenuDate;
    private String matchMenuTime;
    private Boolean valid;
    private String menuName;

    public MatchMenuDto addInfo() {

        this.valid = false;
        String currentDate = DateUtil.getDate();
        String time = DateUtil.getTime();

        if (currentDate.compareTo(matchMenuDate) < 0) {
            this.valid = true;
        }
        if (currentDate.compareTo(matchMenuDate) == 0) {
            if (time.compareTo(shopMorning) < 0) {
                this.valid = true;
            } else if (time.compareTo(shopNoon) < 0) {
                if (!matchMenuTime.equals("早餐")) {
                    this.valid = true;
                }
            } else if (time.compareTo(shopEvening) < 0) {
                if (matchMenuTime.equals("晚餐")) {
                    this.valid = true;
                }
            }else {}
        }
        return this;
    }

    public MatchMenuDto addStatusInfo() {

        this.valid = false;
        this.shopNoon = DateUtil.getPerTime(PRE_TIME,this.shopNoon);
        this.shopMorning = DateUtil.getPerTime(PRE_TIME,this.shopMorning);
        this.shopEvening = DateUtil.getPerTime(PRE_TIME,this.shopEvening);
        String currentDate = DateUtil.getDate();
        String time = DateUtil.getTime();
        if (currentDate.compareTo(matchMenuDate) < 0) {
            this.valid = true;
        }else if(currentDate.compareTo(matchMenuDate) == 0){
            if(matchMenuTime.equals("早餐")){
                if(time.compareTo(shopMorning) < 0){
                    this.valid = true;
                }
            }else if(matchMenuTime.equals("午餐")){
                if(time.compareTo(shopNoon) < 0){
                    this.valid = true;
                }
            }else {
                if(time.compareTo(shopEvening) < 0){
                    this.valid = true;
                }
            }
        }else {}

        return this;
    }

}
