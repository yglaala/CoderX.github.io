package com.ygl.canteen.controller;

import com.ygl.canteen.util.Constants;
import com.ygl.canteen.util.DateUtil;
import com.ygl.canteen.util.Msg;
import com.ygl.canteen.model.Menu;
import com.ygl.canteen.service.IMenuService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class MenuController {

    @Autowired
    private IMenuService menuService;

    /*@GetMapping("/emp/menu/menus")
    @ResponseBody
    public Object getAllMenus(){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                menuService.getMenus();
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(25);
        for(int i = 0 ; i < 10000 ; i++){
            executorService.submit(runnable);
        }
        return menuService.getMenus();
    }
*/

    @GetMapping("/emp/menu/add")
    public String add() {

        return "emp/menu/add";
    }

    @GetMapping("/emp/menu/update/{id}")
    public String update(@PathVariable("id") int id,HttpServletRequest request){

        request.setAttribute("id",id);
        return "emp/menu/add";
    }

    @GetMapping("/emp/menu/details/{id}")
    public String details(@PathVariable("id") int id,HttpServletRequest request){

        request.setAttribute("id",id);
        return "emp/menu/details";
    }

    @GetMapping("/emp/menu/list")
    public String list(){
        return "emp/menu/list";
    }

    @GetMapping("/emp/menu/getdetails/{id}")
    @ResponseBody
    public Menu getMenu(@PathVariable int id){

        /*System.out.println("update");*/
        return menuService.getMenuById(id);
    }

    @PostMapping("/emp/menu/delete")
    @ResponseBody
    public Msg delete(int id){

        if(menuService.delete(id) < 1){
            return new Msg(200,"删除成功");
        }
        return new Msg(100,"删除失败");
    }

    @PostMapping("/emp/menu/insert")
    @ResponseBody
    public Msg insert(Menu menu, MultipartFile uploadPic) {

        //添加菜品，菜品名不能重复
        if(menu.getId() == 0){
            if(menuService.getMenuByName(menu.getName()) != null){
                return new Msg(100,"不能添加重复的菜品");
            }
        }

        //上传图片
        menu.setPath(processFile(uploadPic));

        //添加
        if(menu.getId() == 0){
            if(menuService.add(menu) < 1){
                return new Msg(100,"添加信息出错");
            }
            return new Msg(200,"添加成功");
        }

        //修改
        if(menuService.update(menu) < 1){
            return new Msg(100,"修改失败");
        }
        return new Msg(200,"修改成功");
    }



    @PostMapping("/emp/menu/getMenus")
    @ResponseBody
    public List<Menu> getMenus(){

        return menuService.getMenus();
    }

    //处理上传文件
    private String processFile(MultipartFile uploadPic){

        String showPath = null;
        try {
            File uploadDir = new File(Constants.PIC_UPLOAD_PATH+"/" + DateUtil.getDateStr());

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File file = new File(uploadDir.getPath() + "/" + DateUtil.getTimeStr() +
                    uploadPic.getOriginalFilename().substring(uploadPic.getOriginalFilename().lastIndexOf(".")));
            showPath = Constants.PIC_SHOW_PATH+uploadDir.getName()+"/"+file.getName();

            FileInputStream is = null;
            FileOutputStream os = null;

            is = (FileInputStream) uploadPic.getInputStream();
            os = new FileOutputStream(file);
            IOUtils.copy(is, os);
            os.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return showPath;
    }
}
