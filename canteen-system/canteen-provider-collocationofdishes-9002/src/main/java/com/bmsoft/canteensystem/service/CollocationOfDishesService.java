package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.dto.QueryMenuInfoDto;
import com.bmsoft.canteensystem.util.Msg;

import java.util.Date;
import java.util.List;

/**
 * @Author liugaoyang
 * @Date 2018/10/19 09:57
 */
public interface CollocationOfDishesService {

    //获取菜品搭配列表
    public Msg listOfDishes(Integer shopId, Date matchMenuDate, String matchMenuTime);

    //查询菜品信息
    public List<QueryMenuInfoDto> querMenu(String menuName);
}
