package com.bmsoft.canteensystem.serviceimpl;

import com.bmsoft.canteensystem.dao.MenuDao;
import com.bmsoft.canteensystem.dao.MenuPropertiesCategoryDao;
import com.bmsoft.canteensystem.dao.MenuPropertiesTasteDao;
import com.bmsoft.canteensystem.entity.Menu;
import com.bmsoft.canteensystem.entity.MenuPropertiesCategory;
import com.bmsoft.canteensystem.entity.MenuPropertiesStyle;
import com.bmsoft.canteensystem.entity.MenuPropertiesTaste;
import com.bmsoft.canteensystem.service.MenuPropertiesCategoryService;
import com.bmsoft.canteensystem.service.MenuPropertiesTasteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuPropertiesTasteServiceImpl implements MenuPropertiesTasteService{

    @Resource
    private MenuPropertiesTasteDao menuPropertiesTasteDao;

    @Resource
    private MenuDao menuDao;


    @Override
    public List<MenuPropertiesTaste> findAllMenuPropertiesTasteId() {
        return menuPropertiesTasteDao.findAllMenuPropertiesTasteId();
    }

    @Override
    public List<Menu> findAllByMenuPropertiesTasteId(Integer id) {
        return menuDao.findAllByMenuPropertiesTasteId(id);
    }
}
