package com.bmsoft.canteensystem.controller;

import com.alibaba.fastjson.JSON;
import com.bmsoft.canteensystem.Util.DateUtil4;
import com.bmsoft.canteensystem.entity.MatchMenu;
import com.bmsoft.canteensystem.entity.Menu;
import com.bmsoft.canteensystem.entity.Shop;
import com.bmsoft.canteensystem.service.MatchMenuService;
import com.bmsoft.canteensystem.service.MenuService;
import com.bmsoft.canteensystem.service.MenuSugService;
import com.bmsoft.canteensystem.service.ShopService;
import com.bmsoft.canteensystem.util.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜品信息CONTROLLER层
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
    private Msg msg;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private MatchMenuService matchMenuService;

    @Autowired
    private MenuSugService menuSugService;

    @Autowired
    private ShopService shopService;
    /**
     * 查询所有菜品信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAllMenuInfo")
    public Msg findAllMenuInfo()throws  Exception{
        return Msg.success().add("info",menuService.findAllMenuInfo());
    }

    /**
     * 分頁查詢菜品信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAllByPage")
    public Msg findAll(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize,@RequestBody Menu menu) throws Exception {

        Map<String,Object> map=new HashMap<>();
        map.put("menuName",StringUtil.formatLike(menu.getMenuName()));
        map.put("userId",menu.getShop().getUser().getUserId());
        if (pageNum < 1 && pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 5;
        }
        if(menu.getMenuName()!=null && menu.getMenuName()!=""){
          return Msg.success().add("info",menuService.findByName1(pageNum,pageSize,map));
        }
        return Msg.success().add("info",menuService.find(pageNum, pageSize,map));
    }

    /**
     * 通过菜品名模糊查询(用户模块，不分页)
     * @param menuName
     * @return
     */
    @RequestMapping("/fuzzySearch")
    public Msg fuzzySearchByName(@RequestParam(value = "menuName")String menuName){
        if(menuName!=null){
            return  Msg.success().add("menuInfo",menuService.fuzzySearchByName(StringUtil.formatLike(menuName)));
        }else{
            return Msg.fail();
        }

    }

    /**
     * 根据菜品Id查询菜品信息
     *
     * @param menuId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findByMenuId")
    public Msg findById(@RequestParam(value = "menuId") Integer menuId) throws Exception {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date now=new Date();
        String currentDate=sdf.format(now);
        Map<String,Object> map=new HashMap<>();
        map.put("menuId",menuId);
        map.put("matchMenuDate",currentDate);
        Shop shop=shopService.searchByMenuId(menuId);
        List<MatchMenu> matchMenuList=matchMenuService.searchByMenuId(map);
        for (int i=0;i<matchMenuList.size();i++){
            MatchMenu matchMenu= matchMenuList.get(i);
            Date date;
            switch (matchMenu.getMatchMenuTime()){
                case "早餐":
                    //将搭配菜品日期和时间组合出可操作的时间
                    date = DateUtil4.getDate(matchMenu.getMatchMenuDate(),shop.getShopMorning());
                    break;
                case "午餐":
                    //将搭配菜品日期和时间组合出可操作的时间
                    date = DateUtil4.getDate(matchMenu.getMatchMenuDate(),shop.getShopNoon());
                    break;
                /*case "晚餐"*/
                default:
                    //将搭配菜品日期和时间组合出可操作的时间
                    date = DateUtil4.getDate(matchMenu.getMatchMenuDate(),shop.getShopEvening());
                    break;
            }
            int compareTo = now.compareTo(date);
            switch (compareTo){
                case -1:
                    break;
                default:
                    matchMenuList.remove(i);
                    i--;
            }
        }
        msg = Msg.success().add("menu",  menuService.findById(menuId)).add("matchmenu",matchMenuList).add("menusug",menuSugService.findByMenuId(menuId));;
        return msg;
    }

    /**
     * 根据店铺Id查询菜品信息
     *
     * @param shopId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findByShopId")
    public Msg findByShopId(@RequestParam("shopId") Integer shopId) throws Exception {
        List<Menu> menu = menuService.findByShopId(shopId);
        msg = Msg.success().add("menu", menu);
        return msg;
    }

    /**
     * 添加菜品信息
     *
     * @param
     * @retur
     * @throws Exception
     */
    @RequestMapping("/add")
    public Msg add(String menu0,@RequestPart(value = "uploadPic",required = false)MultipartFile uploadPic) throws Exception {
        Msg msg=new Msg();
        Menu menu = JSON.parseObject(menu0,Menu.class);
        Map<String,Object> map=new HashMap<>();
        map.put("userId",menu.getShop().getUser().getUserId());
        map.put("menuName",menu.getMenuName());
        map.put("shopId",menu.getShop().getShopId());
        if (menu.getMenuId() == null) {
            if (menuService.findByName(map) != null && menuService.findByName(map).size()!=0) {
                return msg.setMsg("本店铺已有该菜品，请换一种菜品添加！");
            }
        }
        if(menu.getMenuId()==null){
           if(menuService.findByMenuNo(map)!=null){
               return msg.setMsg("菜品编号重复！");
           }else{
               int x=(int)((Math.random()*9+1)*10000);
               String c="chuan";
               menu.setMenuNo(c+x);
           }
        }

        //上传图片
        if(uploadPic!=null){
            menu.setMenuPicPath(processFile(uploadPic));
        }
        //添加
            if ( menuService.add(menu)< 1) {
                return  msg.setMsg("添加失败");
            }else
            return msg.setMsg("添加成功").setCode(100);
    }

    public  String processFile(MultipartFile uploadPic){

        String showPath = null;
        try {
            File uploadDir = new File(Constants.PIC_UPLOAD_PATH+"/" + DateUtil.getDateStr());

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File file = new File(uploadDir.getPath() + "/" + DateUtil.getTimeStr() +
                    uploadPic.getOriginalFilename().substring(uploadPic.getOriginalFilename().lastIndexOf(".")));
            showPath = Constants.PIC_SHOW_PATH+"/"+uploadDir.getName()+"/"+file.getName();

            ByteArrayInputStream is = null;
            FileOutputStream os = null;

            is = (ByteArrayInputStream) uploadPic.getInputStream();
            os = new FileOutputStream(file);
            IOUtils.copy(is, os);
            os.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return showPath;
    }
        /**
         * 更新菜品信息
         *
         * @param menu0
         * @return
         * @throws Exception
         */
        @RequestMapping("/update")
        public Msg update (String menu0,@RequestPart(value = "uploadPic",required = false)MultipartFile uploadPic) throws Exception {
            Menu menu=JSON.parseObject(menu0,Menu.class);
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("menuName",menu.getMenuName());
            map.put("userId",menu.getShop().getUser().getUserId());
            map.put("menuId",menu.getMenuId());
            map.put("shopId",menu.getShop().getShopId());
            Msg msg=new Msg();

            //上传图片
            if(uploadPic!=null){
                menu.setMenuPicPath(processFile(uploadPic));
            }
            if(menuService.findById(menu.getMenuId())==null){
               return msg.setMsg("找不到该菜品，请确认是否有该菜品！");
            }
            if (menuService.findByName(map)!=null && menuService.findByName(map).size()!=0){
                if(menuService.findByMenuName1(map)!=null){
                    return  msg.setMsg("已有该菜品，不能重复修改！").setCode(1);
                }
            }
                if (menuService.update(menu)<1){
                    return msg.setMsg("修改失败");
                }
            return msg.setMsg("修改成功");
        }

    @RequestMapping("/update1")
    public Msg update1 (String menu0) throws Exception {
        Menu menu=JSON.parseObject(menu0,Menu.class);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("menuName",menu.getMenuName());
        map.put("userId",menu.getShop().getUser().getUserId());
        map.put("menuId",menu.getMenuId());
        map.put("shopId",menu.getShop().getShopId());
        Msg msg=new Msg();
        //上传图片
        menu.setMenuPicPath(null);

        if(menuService.findById(menu.getMenuId())==null){
            return msg.setMsg("找不到该菜品，请确认是否有该菜品！");
        }
        if (menuService.findByName(map)!=null && menuService.findByName(map).size()!=0){
            if(menuService.findByMenuName1(map)!=null){
                return  msg.setMsg("已有该菜品，不能重复修改！").setCode(1);
            }

        }
        if (menuService.update(menu)<1){
            return msg.setMsg("修改失败");
        }
        return msg.setMsg("修改成功");
    }

        /**
         * 删除菜品
         *
         * @param menuIds
         * @return
         * @throws Exception
         */
        @RequestMapping("/delete")
        public Msg delete (@RequestParam(value = "menuId") String menuIds) throws Exception {
            String menuIdsStr[] = menuIds.split(",");
            for (String m : menuIdsStr) {
               String pic= menuService.findById(Integer.parseInt(m)).getMenuPicPath();
               if(pic!=null){
                   File file=new File(pic.replace(Constants.PIC_SHOW_PATH,Constants.PIC_UPLOAD_PATH));
                   if(file.exists()){
                       file.delete();
                   }
               }
                menuService.delete(Integer.parseInt(m));
            }
            msg = Msg.success();
            msg.setMsg("删除成功！");
            return msg;
        }

        @RequestMapping("/set")
        public Object set(){
            request.getSession().setAttribute("admin","dsadsad");
            return null;
        }

        @RequestMapping("/get")
        public Object get(){

            return request.getSession().getAttribute("admin");
        }
    }