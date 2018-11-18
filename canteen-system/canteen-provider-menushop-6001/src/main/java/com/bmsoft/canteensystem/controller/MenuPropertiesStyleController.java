package com.bmsoft.canteensystem.controller;

import com.bmsoft.canteensystem.entity.MenuPropertiesStyle;
import com.bmsoft.canteensystem.service.MenuPropertiesStyleService;
import com.bmsoft.canteensystem.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜品种类Controller层
 */
@RestController
public class MenuPropertiesStyleController {

    @Autowired
    private MenuPropertiesStyleService menuPropertiesStyleService;

    @RequestMapping("/menuPropertiesStyle")
    public Msg findAllmenuPropertiesStyleId()throws Exception{
        List<MenuPropertiesStyle> menuPropertiesCategories=menuPropertiesStyleService.findAllMenuPropertiesStyleId();
        System.out.println(menuPropertiesCategories);
        return Msg.success().add("menuPropertiesCategories",menuPropertiesCategories);
    }

    @RequestMapping("/findAllByMenuPropertiesStyleId")
    public Msg findBymenuPropertiesStyleId(@RequestParam(value = "menuPropertiesStyleId") Integer menuPropertiesStyleId)throws Exception{
        return  Msg.success().add("Info",menuPropertiesStyleService.findAllByMenuPropertiesStyleId(menuPropertiesStyleId));
    }
}
