package com.bmsoft.canteensystem.controller;

import com.alibaba.fastjson.JSON;
import com.bmsoft.canteensystem.entity.Menu;
import com.bmsoft.canteensystem.entity.User;
import com.bmsoft.canteensystem.service.FeignClientService;
import com.bmsoft.canteensystem.util.Msg;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bmsoft.canteensystem.entity.Shop;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

@RestController
public class FeignController_Consumer
{

	@Autowired
	private FeignClientService feignClientService;

	@Autowired
	private HttpServletRequest request;

	@RequestMapping("/findByUserId")
	public Msg findByUserId(){
		HttpSession session=request.getSession();
		LinkedHashMap userInfo= (LinkedHashMap) session.getAttribute("userInfo");
		Shop shop=new Shop();
		shop.setUser(new User().setUserId((Integer) userInfo.get("userId")));
		return  feignClientService.findByUserId(shop.getUser().getUserId());
	}

	@RequestMapping("/add")
	public Msg add(@ModelAttribute("Shop") Shop shop){
		HttpSession session=request.getSession();
		LinkedHashMap userInfo= (LinkedHashMap) session.getAttribute("userInfo");
		shop.setUser(new User().setUserId((Integer)userInfo.get("userId")));
		Msg msg = feignClientService.add(shop);
		System.out.println(msg.getInfo().get("userShopInfo").toString());
		session.setAttribute("userShopInfo",msg.getInfo().get("userShopInfo"));
		return msg;
	}

	@RequestMapping("/update")
	public Msg update(@ModelAttribute("Shop") Shop shop){
		HttpSession session=request.getSession();
		LinkedHashMap userInfo= (LinkedHashMap) session.getAttribute("userInfo");
		LinkedHashMap userShopInfo= (LinkedHashMap) session.getAttribute("userShopInfo");
		shop.setUser(new User().setUserId((Integer)userInfo.get("userId")));
		shop.setShopId((Integer) userShopInfo.get("shopId"));
		return feignClientService.update(shop);
	}

	@RequestMapping(value = "/menu/findByShopId")
	public Msg findByShopId(@RequestParam("shopId") Integer shopId)throws  Exception{
		return feignClientService.findByShopId(shopId);
	}

	@RequestMapping(value = "/menu/findAllMenuInfo")
	public Msg findAllMenuInfo()throws  Exception{
		return  feignClientService.findAllMenuInfo();
	}

	@RequestMapping("/menu/findByMenuId")
	public Msg findById(@RequestParam(value = "menuId") Integer menuId)throws  Exception{
		return feignClientService.findById(menuId);
	}


	@RequestMapping("/menu/findAllByPage")
	public Msg findAll(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize, @ModelAttribute("Menu") Menu menu)throws  Exception{
		HttpSession session=request.getSession();
		LinkedHashMap userInfo= (LinkedHashMap) session.getAttribute("userInfo");
		menu.setShop(new Shop().setUser(new User().setUserId((Integer) userInfo.get("userId"))));
		return feignClientService.findAll(pageNum,pageSize,menu);
	}


	@RequestMapping("/menu/add")
	public Msg add(@ModelAttribute("menu") Menu menu, MultipartFile uploadPic){
		HttpSession session=request.getSession();
		LinkedHashMap userInfo= (LinkedHashMap) session.getAttribute("userInfo");
		LinkedHashMap userShopInfo= (LinkedHashMap) session.getAttribute("userShopInfo");
		menu.setShop(new Shop().setShopId((Integer) userShopInfo.get("shopId")).setUser(new User().setUserId((Integer) userInfo.get("userId"))));
        String menu0 = JSON.toJSONString(menu);
		return  feignClientService.add(menu0,uploadPic);
	}


	@RequestMapping("/menu/update")
	public Msg update(@ModelAttribute("menu") Menu menu, MultipartFile uploadPic){
		HttpSession session=request.getSession();
		LinkedHashMap userInfo= (LinkedHashMap) session.getAttribute("userInfo");
		LinkedHashMap userShopInfo= (LinkedHashMap) session.getAttribute("userShopInfo");
		menu.setShop(new Shop().setShopId((Integer) userShopInfo.get("shopId")).setUser(new User().setUserId((Integer) userInfo.get("userId"))));
		String menu0 = JSON.toJSONString(menu);
		if(uploadPic==null){
			return  feignClientService.update1(menu0);
		}
		return  feignClientService.update(menu0,uploadPic);
	}

	@RequestMapping("/menu/delete")
	public Msg delete(@RequestParam(value = "menuId") String menuIds)throws  Exception{
		return feignClientService.delete(menuIds);
	}

    /**
     * 查询所有菜品种类信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/menuPropertiesCategory")
    public Msg findAllmenuPropertiesCategoryId()throws  Exception{
        return feignClientService.findAllmenuPropertiesCategoryId();
    }

	@RequestMapping("/menu/fuzzySearch")
	public Msg fuzzySearchByName(@RequestParam(value = "menuName")String menuName){
    	return feignClientService.fuzzySearchByName(menuName);
	}

    /**
     * 根据种类id查询菜品信息
     * @param menuPropertiesCategoryId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAllByMenuPropertiesCategoryId")
    public Msg findBymenuPropertiesCategoryId(@RequestParam(value = "menuPropertiesCategoryId") Integer menuPropertiesCategoryId)throws  Exception{
      return   feignClientService.findBymenuPropertiesCategoryId(menuPropertiesCategoryId);
    }


    @RequestMapping("/menuPropertiesStyle")
    public Msg findAllmenuPropertiesStyleId()throws  Exception{
        return feignClientService.findAllmenuPropertiesStyleId();
    }

    @RequestMapping("/findAllByMenuPropertiesStyleId")
	public Msg findBymenuPropertiesStyleId(@RequestParam(value = "menuPropertiesStyleId") Integer menuPropertiesStyleId)throws  Exception{
        return feignClientService.findBymenuPropertiesStyleId(menuPropertiesStyleId);
    }


    @RequestMapping("/menuPropertiesTaste")
    public Msg findAllmenuPropertiesTasteId()throws Exception{
			return feignClientService.findAllmenuPropertiesTasteId();
    }

    @RequestMapping("/findAllByMenuPropertiesTasteId")
    public Msg findBymenuPropertiesTasteId(@RequestParam(value = "menuPropertiesTasteId") Integer menuPropertiesTasteId)throws Exception{
		return feignClientService.findBymenuPropertiesTasteId(menuPropertiesTasteId);
    }
}
