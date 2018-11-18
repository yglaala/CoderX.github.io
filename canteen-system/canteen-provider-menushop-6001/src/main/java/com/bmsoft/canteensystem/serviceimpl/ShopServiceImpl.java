package com.bmsoft.canteensystem.serviceimpl;

import com.bmsoft.canteensystem.dao.ShopDao;
import com.bmsoft.canteensystem.entity.Shop;
import com.bmsoft.canteensystem.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 店铺Service实现类
 */
@Service
public class ShopServiceImpl implements ShopService{

    @Resource
    private ShopDao shopDao;

    @Override
    public Shop findByUserId(Integer id) {
        return shopDao.findByUserId(id);
    }

    @Override
    public Shop findByShopName(String shopName) {
        return shopDao.findByShopName(shopName);
    }

    @Override
    public Shop findByShopUserId(Map<String, Object> map) {
        return shopDao.findByShopUserId(map);
    }

    @Override
    public int add(Shop shop) {
        return shopDao.add(shop);
    }

    @Override
    public int update(Shop shop) {
        return shopDao.update(shop);
    }

    @Override
    public Shop searchByMenuId(Integer id) {
        return shopDao.searchByMenuId(id);
    }
}
