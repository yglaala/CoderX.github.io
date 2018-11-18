package com.bmsoft.canteensystem.service.impl;

import com.bmsoft.canteensystem.entity.Shop;
import com.bmsoft.canteensystem.mapper.ShopMapper;
import com.bmsoft.canteensystem.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author liugaoyang
 * @Date 2018/10/21 14:20
 */
@Service
public class ShopServicrImpl implements ShopService {

    @Autowired
    ShopMapper shopMapper;

    /**
     * 功能说明：
     * 查询所有店铺
     * @return
     * @author liugaoyang
     */
    @Override
    public List<Shop> ShopList() {
        return shopMapper.findAll();
    }
}
