package com.bmsoft.canteensystem.serviceimpl;

import com.bmsoft.canteensystem.dao.MenuDao;
import com.bmsoft.canteensystem.dao.MenuPropertiesCategoryDao;
import com.bmsoft.canteensystem.dao.MenuPropertiesStyleDao;
import com.bmsoft.canteensystem.entity.Menu;
import com.bmsoft.canteensystem.entity.MenuPropertiesCategory;
import com.bmsoft.canteensystem.entity.MenuPropertiesStyle;
import com.bmsoft.canteensystem.service.MenuPropertiesCategoryService;
import com.bmsoft.canteensystem.service.MenuPropertiesStyleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuPropertiesStyleServiceImpl implements MenuPropertiesStyleService{

    @Resource
    private MenuPropertiesStyleDao menuPropertiesStyleDao;

    @Resource
    private MenuDao menuDao;

    @Override
    public List<MenuPropertiesStyle> findAllMenuPropertiesStyleId() {
        return menuPropertiesStyleDao.findAllMenuPropertiesStyleId();
    }

    @Override
    public List<Menu> findAllByMenuPropertiesStyleId(Integer id) {
        return menuDao.findAllByMenuPropertiesStyleId(id);
    }
}
