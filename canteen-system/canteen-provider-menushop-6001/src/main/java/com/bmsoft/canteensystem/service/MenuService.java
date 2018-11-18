package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.entity.Menu;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 菜品信息Service层
 */
public interface MenuService {

    /**
     * 查询菜品信息集合
     * @return
     */
    public List<Menu> findAll(Map<String,Object> map);

    /**
     * 分頁查詢所有菜品信息
     * @return
     */
    public PageInfo<Menu> find(Integer currentPage,Integer pageSize,Map<String,Object> map);

    /**
     * 根据菜品名分页查询菜品信息
     */
    public PageInfo<Menu> findByName1(Integer currentPage,Integer pageSize,Map<String,Object> map);

    /**
     * 通过菜品名模糊查询菜品信息
     * @param
     * @return
     */
    public List<Menu> findByMenuName(Map<String,Object> map);


    /**
     * 通过菜品编号查找菜品
     * @param map
     * @return
     */
    public Menu findByMenuNo(Map<String,Object> map);

    /**
     * 通过菜品查询菜品信息
     * @param map
     * @return
     */
    public List<Menu> findByName(Map<String,Object> map);

    /**
     * 添加菜品
     * @param menu
     * @return
     */
    public int add(Menu menu);

    /**
     * 更新菜品
     * @param menu
     * @return
     */
    public int update(Menu menu);

    /**
     * 删除菜品
     * @param id
     * @return
     */
    public boolean delete(Integer id);


    /**
     * 根据用户id查找店铺id
     * @param userId
     * @return
     */
    public Integer getShopId(Integer userId);

    /**
     * 通过id查找实体
     * @param id
     * @return
     */
    public Menu findById(Integer id);

    /**
     * 通过店铺id查找菜品
     * @param id
     * @return
     */
    public List<Menu> findByShopId(Integer id);


    public Menu findByMenuName1(Map<String,Object> map);

    /**
     * 查询所有菜品信息(用户模块)
     * @return
     */
    public List<Menu> findAllMenuInfo();

    /**
     * 通过菜品名模糊查询,不分页
     * @param menuName
     * @return
     */
    public List<Menu>  fuzzySearchByName(String menuName);

}
