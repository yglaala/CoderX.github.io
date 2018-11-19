package com.ygl.canteen.service;

import com.ygl.canteen.model.Menu;

import java.util.List;

public interface IMenuService {

    int add(Menu menu);
    Menu getMenuByName(String name);
    List<Menu> getMenus();
    int delete(int id);
    int update(Menu menu);
    Menu getMenuById(int id);
}
