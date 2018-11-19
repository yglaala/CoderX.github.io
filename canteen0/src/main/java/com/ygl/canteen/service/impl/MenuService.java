package com.ygl.canteen.service.impl;

import com.ygl.canteen.util.Constants;
import com.ygl.canteen.mapper.MenuMapper;
import com.ygl.canteen.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ygl.canteen.model.Menu;

import java.util.List;

@Service
public class MenuService implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;
    /*注入springboot自动配置好的redisTemplate*/
    /*@Autowired
    private RedisTemplate<Object,Object> redisTemplate;*/

    @Override
    public int add(Menu menu) {

        return menuMapper.add(menu);
    }

    @Override
    public Menu getMenuByName(String name) {
        return menuMapper.getMenuByName(name);
    }

    @Override
    public List<Menu> getMenus() {
        return null;
    }

    //@Override
    //public List<Menu> getMenus() {

        //RedisSerializer redisSerializer = new StringRedisSerializer();
        //redisTemplate.setKeySerializer(redisSerializer);
        //查询缓存
        //List<Menu> menus = (List<Menu>) redisTemplate.opsForValue().get("allMenus");
        /*System.out.println(menus);*/
        /*System.out.println(menus);*/
        //if(null == menus){
            //menus = menuMapper.getMenus();
           // redisTemplate.opsForValue().set("allMenus",menus);
        //}
        /*for(Menu menu : menus){
            menu.setPath("http://"+ Constants.IP +":8080"+menu.getPath());
        }*/
        //return menus;
   // }

    @Override
    public int delete(int id) {
        return menuMapper.delete(id);
    }

    @Override
    public int update(Menu menu) {
        return menuMapper.update(menu);
    }

    @Override
    public Menu getMenuById(int id) {

        Menu menu = menuMapper.getMenuById(id);
        menu.setPath("http://"+ Constants.IP +":8080"+menu.getPath());
        return menu;
    }
}

