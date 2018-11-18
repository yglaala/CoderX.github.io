package com.bmsoft.canteensystem.serviceimpl;

import com.bmsoft.canteensystem.dao.MatchMenuDao;
import com.bmsoft.canteensystem.dao.MenuSugDao;
import com.bmsoft.canteensystem.entity.Menu;
import com.bmsoft.canteensystem.entity.MenuSug;
import com.bmsoft.canteensystem.service.MatchMenuService;
import com.bmsoft.canteensystem.service.MenuSugService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜品评价service层
 */
@Service
public class MenuSugServiceImpl implements MenuSugService{

    @Resource
    private MenuSugDao menuSugDao;

    @Override
    public List<MenuSug> findByMenuId(Integer id) {
        return menuSugDao.findByMenuId(id);
    }
}
