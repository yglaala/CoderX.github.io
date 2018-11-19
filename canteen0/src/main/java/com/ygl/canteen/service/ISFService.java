package com.ygl.canteen.service;

import com.ygl.canteen.model.Menu;
import com.ygl.canteen.model.SF;
import com.ygl.canteen.dto.WeekMenu;

import java.sql.SQLException;
import java.util.List;

public interface ISFService {

    List<SF> getToday();
    List<WeekMenu> getWeek();
    int add(List<SF> sfs) throws SQLException;
    List<Menu> getMenus(String date);
}
