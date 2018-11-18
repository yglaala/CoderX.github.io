package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.entity.MatchMenu;
import com.bmsoft.canteensystem.util.Msg;

import java.util.List;

/**
 * @Author liugaoyang
 * @Date 2018/10/21 20:42
 */
public interface MatchMenuService {

    //修改指定id菜品搭配的数量
    public void updateMatchMenuNum(Integer matchMenuId, Integer matchMenuNum);

    //修改接下来7天的菜品搭配的数量
    public void updateSevenMatchMenuNum(Integer matchMenuId, Integer matchMenuNum);

    //删除指定id菜品搭配
    public void delMatchMenu(Integer matchMenuId);

    //删除接下来7天的菜品搭配
    public void delSevenMatchMenu(Integer matchMenuId);

    //添加菜品搭配
    public Msg addMatchMenu(List<MatchMenu> matchMenuList, boolean seven);
}
