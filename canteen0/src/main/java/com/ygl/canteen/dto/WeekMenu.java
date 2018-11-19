package com.ygl.canteen.dto;

import com.ygl.canteen.model.Menu;

import java.util.List;

public class WeekMenu {

    private static String []weekDays = { "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" ,"星期日"};
    private int dayOfWeek;
    private List<Menu> menus;

    public WeekMenu( int dayOfWeek, List<Menu> menus) {
        this.dayOfWeek = dayOfWeek;
        this.menus = menus;
    }

    public WeekMenu() {
    }


    public String getDayOfWeek() {
        return weekDays[dayOfWeek];
    }

    public WeekMenu setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public WeekMenu setMenus(List<Menu> menus) {
        this.menus = menus;
        return this;
    }

    @Override
    public String toString() {
        return "WeekMenu{" +
                ",dayOfWeek='" + weekDays[dayOfWeek] + '\'' +
                ",menus=" + menus +
                "}\r\n";
    }
}
