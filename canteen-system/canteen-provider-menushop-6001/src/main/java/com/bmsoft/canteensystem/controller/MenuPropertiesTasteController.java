package com.bmsoft.canteensystem.controller;

import com.bmsoft.canteensystem.entity.MenuPropertiesTaste;
import com.bmsoft.canteensystem.service.MenuPropertiesTasteService;
import com.bmsoft.canteensystem.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜品口味Controller层
 */
@RestController
public class MenuPropertiesTasteController {

    @Autowired
    private MenuPropertiesTasteService menuPropertiesTasteService;

    @RequestMapping("/menuPropertiesTaste")
    public Msg findAllmenuPropertiesTasteId()throws Exception{
        List<MenuPropertiesTaste> menuPropertiesCategories=menuPropertiesTasteService.findAllMenuPropertiesTasteId();
        System.out.println(menuPropertiesCategories);
        return Msg.success().add("menuPropertiesCategories",menuPropertiesCategories);
    }

    @RequestMapping("/findAllByMenuPropertiesTasteId")
    public Msg findBymenuPropertiesTasteId(@RequestParam(value = "menuPropertiesTasteId") Integer menuPropertiesTasteId)throws Exception{
        return  Msg.success().add("Info",menuPropertiesTasteService.findAllByMenuPropertiesTasteId(menuPropertiesTasteId));
    }
}
