package com.ygl.canteen.service.impl;

import com.ygl.canteen.util.Constants;
import com.ygl.canteen.util.DateUtil;
import com.ygl.canteen.util.StringUtil;
import com.ygl.canteen.mapper.SFMapper;
import com.ygl.canteen.model.Menu;
import com.ygl.canteen.model.SF;
import com.ygl.canteen.service.ISFService;
import com.ygl.canteen.dto.WeekMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SFService implements ISFService {

    @Autowired
    private SFMapper sfMapper;

    @Override
    public List<SF> getToday() {
        List<SF> sfs = sfMapper.getTodayMenu();
        for(SF sf : sfs){
            sf.getMenu().setPath("http://"+ Constants.IP +":8080"+sf.getMenu().getPath());
        }
        return sfs;
    }

    @Override
    public List<WeekMenu> getWeek() {
        List<SF> sfs = sfMapper.getWeekMenu();
        List<WeekMenu> weekMenus = new ArrayList<>();
        List<Menu> menus = null;
        WeekMenu weekMenu = null;
        for (int i = 0; i < 7; i++) {
            menus = new ArrayList<>();
            for (SF sf : sfs) {
                weekMenu = new WeekMenu();

                if (StringUtil.StringToInt(sf.getCreatedate()) == i) {

                   /* sf.getMenu().setPath("http://"+ Constants.IP +":8080"+sf.getMenu().getPath());*/
                    menus.add(sf.getMenu());
                }
            }
            weekMenu.setDayOfWeek(i);
            weekMenu.setMenus(menus);
            if (weekMenu.getMenus() != null && weekMenu.getMenus().size() > 0) {
                weekMenus.add(weekMenu);
            }
        }
        /*System.out.println(weekMenus);*/
        /*for(WeekMenu week : weekMenus){
            for(Menu menu : week.getMenus()){
                menu.setPath("http://"+ Constants.IP +":8080"+menu.getPath());
            }
        }*/
        return weekMenus;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    @Override
    public int add(List<SF> sfs) throws SQLException {

        int i = 0;
        for(SF sf : sfs){
            int a = sfMapper.add(sf);
            i += a;
        }
        return i;
    }

    @Override
    public List<Menu> getMenus(String date) {
        if(date == null || date == ""){
            date = DateUtil.getDate();
        }

        List<Menu> menus = sfMapper.getMenus(date);
        for(Menu menu : menus){
            menu.setPath("http://"+ Constants.IP +":8080"+menu.getPath());
        }
        return menus;
    }
}
