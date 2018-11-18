package com.bmsoft.canteensystem.service.impl;

import com.bmsoft.canteensystem.entity.MenuSug;
import com.bmsoft.canteensystem.mapper.MenuSugMapper;
import com.bmsoft.canteensystem.service.MenuSugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuSugServiceImpl implements MenuSugService {


    @Autowired
    private MenuSugMapper menuSugMapper;

    @Transactional(isolation = Isolation.DEFAULT)
    @Override
    public boolean addMenuSugs(List<MenuSug> menuSugs) {

        Integer count = 0;
        for(MenuSug menuSug : menuSugs){

            if(menuSug.getMenuSugId() != 0){
                count += menuSugMapper.updateSugMenu(menuSug);
            }
            else {
                count += menuSugMapper.addSugMenu(menuSug);
            }
        }
        System.out.println(count);
        if(count < menuSugs.size()){
            throw new RuntimeException();
        }
        return true;
    }
}
