package com.bmsoft.canteensystem.serviceimpl;

import com.bmsoft.canteensystem.dao.MatchMenuDao;
import com.bmsoft.canteensystem.entity.MatchMenu;
import com.bmsoft.canteensystem.entity.Menu;
import com.bmsoft.canteensystem.service.MatchMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatchMenuServiceImpl implements MatchMenuService{

    @Resource
    private MatchMenuDao matchMenuDao;


    @Override
    public List<MatchMenu> searchByMenuId(Map<String,Object> map) {
        return matchMenuDao.searchByMenuId(map);
    }
}
