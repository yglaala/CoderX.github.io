package com.bmsoft.canteensystem.service.impl;

import com.bmsoft.canteensystem.dao.SugDao;
import com.bmsoft.canteensystem.entity.Sug;
import com.bmsoft.canteensystem.service.SugService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chengpeng
 * @create 2018-10-18 15:39
 */
@Service
public class SugServiceImpl implements SugService {
    @Autowired
    private SugDao sugDao;
    @Override
    public List<Sug> find(Sug sug, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return sugDao.find(sug);
    }

    @Override
    public Integer getCount(Sug sug) {
        return sugDao.getCount(sug);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public boolean add(Sug sug) {
        boolean flag;
        try {
            flag=sugDao.add(sug);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return flag;
    }
}
