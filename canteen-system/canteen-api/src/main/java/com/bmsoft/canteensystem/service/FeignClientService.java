package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.entity.Menu;
import com.bmsoft.canteensystem.entity.Shop;
import com.bmsoft.canteensystem.util.Msg;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "SHOP-MENU-PROVIDER",fallbackFactory = FeignClientServiceFallbackFactory.class)
public interface FeignClientService {

    /**
     * 店铺模块
     * @return
     */
	@RequestMapping("/findByUserId")
	public Msg findByUserId(@RequestParam(value = "userId") Integer userId);

	@RequestMapping("/add")
	public Msg add(@RequestBody Shop shop);

	@RequestMapping("/update")
	public Msg update(@RequestBody Shop shop);

	/**
	 * 根据店铺Id查询菜品信息
	 *
	 * @param shopId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/menu/findByShopId")
	public Msg findByShopId(@RequestParam("shopId") Integer shopId);


    /**
     * 菜品信息模块
     */

	/**
	 * 查询所有菜品信息
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/menu/findAllMenuInfo")
	public Msg findAllMenuInfo();

	/**
	 * 根据菜品Id查询菜品信息
	 *
	 * @param menuId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/menu/findByMenuId")
	public Msg findById(@RequestParam(value = "menuId") Integer menuId);

	/**
	 * 分页查询菜品信息
	 * @param pageNum
	 * @param pageSize
	 * @param menu
	 * @return
	 */
	@RequestMapping(value = "/menu/findAllByPage")
    public Msg findAll(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize, @RequestBody Menu menu);


	/**
	 * 菜品信息添加
	 * @param menu0
	 * @param uploadPic
	 * @return
	 */
	@RequestMapping(value = "/menu/add",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Msg add(@RequestParam("menu0") String menu0, @RequestPart(value = "uploadPic") MultipartFile uploadPic);


	/**
	 * 菜品信息修改
	 * @param menu0
	 * @param uploadPic
	 * @return
	 */
	@RequestMapping(value = "/menu/update",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Msg update(@RequestParam("menu0") String menu0, @RequestPart(value = "uploadPic") MultipartFile uploadPic);


	@RequestMapping(value = "/menu/update1")
	public Msg update1(@RequestParam("menu0") String menu0);

	/**
	 * 菜品信息删除
	 * @param menuIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("menu/delete")
	public Msg delete(@RequestParam(value = "menuId") String menuIds);

	/**
	 * 查询所有菜品种类信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/menuPropertiesCategory")
	public Msg findAllmenuPropertiesCategoryId();


	/**
	 * 通过菜品名模糊查询(用户模块，不分页)
	 * @param menuName
	 * @return
	 */
	@RequestMapping("/menu/fuzzySearch")
	public Msg fuzzySearchByName(@RequestParam(value = "menuName") String menuName);

    /**
     * 根据种类id查询菜品信息
     * @param menuPropertiesCategoryId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAllByMenuPropertiesCategoryId")
    public Msg findBymenuPropertiesCategoryId(@RequestParam(value = "menuPropertiesCategoryId") Integer menuPropertiesCategoryId);


    @RequestMapping("/menuPropertiesStyle")
    public Msg findAllmenuPropertiesStyleId();

    @RequestMapping("/findAllByMenuPropertiesStyleId")
	public Msg findBymenuPropertiesStyleId(@RequestParam(value = "menuPropertiesStyleId") Integer menuPropertiesStyleId);


    @RequestMapping("/menuPropertiesTaste")
    public Msg findAllmenuPropertiesTasteId();

    @RequestMapping("/findAllByMenuPropertiesTasteId")
    public Msg findBymenuPropertiesTasteId(@RequestParam(value = "menuPropertiesTasteId") Integer menuPropertiesTasteId);



}
