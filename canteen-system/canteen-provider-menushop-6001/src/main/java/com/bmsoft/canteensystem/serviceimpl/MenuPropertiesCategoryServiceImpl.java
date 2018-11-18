package com.bmsoft.canteensystem.serviceimpl;

import com.bmsoft.canteensystem.dao.MenuDao;
import com.bmsoft.canteensystem.dao.MenuPropertiesCategoryDao;
import com.bmsoft.canteensystem.entity.Menu;
import com.bmsoft.canteensystem.entity.MenuPropertiesCategory;
import com.bmsoft.canteensystem.service.MenuPropertiesCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuPropertiesCategoryServiceImpl implements MenuPropertiesCategoryService{

    @Resource
    private MenuPropertiesCategoryDao menuPropertiesCategoryDao;

    @Resource
    private MenuDao menuDao;

    @Override
    public List<MenuPropertiesCategory> findAllMenuPropertiesCategoryId() {
        return menuPropertiesCategoryDao.findAllMenuPropertiesCategoryId();
    }

    @Override
    public List<Menu> findAllByMenuPropertiesCategoryId(Integer id) {
        return menuDao.findAllByMenuPropertiesCategoryId(id);
    }
}
