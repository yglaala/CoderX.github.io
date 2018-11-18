package com.bmsoft.canteensystem.controller;

import com.bmsoft.canteensystem.entity.MenuPropertiesCategory;
import com.bmsoft.canteensystem.service.MenuPropertiesCategoryService;
import com.bmsoft.canteensystem.util.Msg;
import com.sun.tools.corba.se.idl.InterfaceGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜品类型Controller层
 */
@RestController
public class MenuPropertiesCategoryController {

    @Autowired
    private MenuPropertiesCategoryService menuPropertiesCategoryService;

    /**
     * 查询所有菜品种类信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/menuPropertiesCategory")
    public Msg findAllmenuPropertiesCategoryId()throws Exception{
        return Msg.success().add("menuPropertiesCategories",menuPropertiesCategoryService.findAllMenuPropertiesCategoryId());
    }

    /**
     * 根据种类id查询菜品信息
     * @param menuPropertiesCategoryId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAllByMenuPropertiesCategoryId")
    public Msg findBymenuPropertiesCategoryId(@RequestParam(value = "menuPropertiesCategoryId") Integer menuPropertiesCategoryId)throws Exception{
        return  Msg.success().add("Info",menuPropertiesCategoryService.findAllByMenuPropertiesCategoryId(menuPropertiesCategoryId));
    }
}
