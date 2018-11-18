package com.bmsoft.canteensystem.serviceimpl;

import com.bmsoft.canteensystem.dao.MenuDao;
import com.bmsoft.canteensystem.entity.Menu;
import com.bmsoft.canteensystem.service.MenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;

/**
 * 菜品信息Service实现类
 */
@Service
public class MenuServiceImpl implements MenuService{

    @Resource
    private MenuDao menuDao;

    @Override
    public List<Menu> findAll(Map<String,Object> map) {
        return menuDao.findAll(map);
    }

    @Override
    public PageInfo<Menu> find(Integer currentPage,Integer pageSize,Map<String,Object> map) {
        return PageHelper.startPage(currentPage,pageSize).doSelectPageInfo(() -> menuDao.findAll(map));
    }

    @Override
    public PageInfo<Menu> findByName1(Integer currentPage, Integer pageSize, Map<String,Object> map) {
        return PageHelper.startPage(currentPage,pageSize).doSelectPageInfo(() -> menuDao.findByMenuName(map));
    }

    @Override
    public List<Menu> findByMenuName(Map<String,Object> map) {
        return menuDao.findByMenuName(map);
    }

    @Override
    public Menu findByMenuNo(Map<String, Object> map) {
        return menuDao.findByMenuNo(map);
    }


    @Override
    public  List<Menu> findByName(Map<String, Object> map) {
        return menuDao.findByName(map);
    }

    @Override
    public int add(Menu menu) {
        return menuDao.add(menu);
    }

    @Override
    public int update(Menu menu) {
        return menuDao.update(menu);
    }

    @Override
    public boolean delete(Integer id) {
        return menuDao.delete(id);
    }

    @Override
    public Integer getShopId(Integer userId) {
        return menuDao.getShopId(userId);
    }

    @Override
    public Menu findById(Integer id) {
        return menuDao.findById(id);
    }

    @Override
    public List<Menu> findByShopId(Integer id) {
        return menuDao.findByShopId(id);
    }

    @Override
    public Menu findByMenuName1(Map<String,Object> map) {
        return menuDao.findByMenuName1(map);
    }

    @Override
    public List<Menu> findAllMenuInfo() {
        return menuDao.findAllMenuInfo();
    }

    @Override
    public List<Menu> fuzzySearchByName(String menuName) {
        return menuDao.fuzzySearchByName(menuName);
    }

}
