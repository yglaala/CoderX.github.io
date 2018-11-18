package com.bmsoft.canteensystem.service.impl;

import com.bmsoft.canteensystem.dto.MatchMenuInfoDto;
import com.bmsoft.canteensystem.entity.MatchMenu;
import com.bmsoft.canteensystem.mapper.MatchMenuMapper;
import com.bmsoft.canteensystem.service.MatchMenuService;
import com.bmsoft.canteensystem.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author liugaoyang
 * @Date 2018/10/21 20:43
 */
@Service
public class MatchMenuServiceImpl implements MatchMenuService {

    @Autowired
    MatchMenuMapper matchMenuMapper;

    private Msg msg;

    /**
     *功能说明：
     * 修改指定id菜品搭配的数量
     * @param matchMenuId
     * @param matchMenuNum
     */
    @Override
    public void updateMatchMenuNum(Integer matchMenuId, Integer matchMenuNum) {
        matchMenuMapper.updateMatchMenuNumByMatchMenuId(matchMenuId,matchMenuNum);
    }


    /**
     *功能说明：
     * 修改接下来7天的菜品搭配的数量
     * @param matchMenuId
     * @param matchMenuNum
     */
    @Override
    public void updateSevenMatchMenuNum(Integer matchMenuId, Integer matchMenuNum) {
        MatchMenuInfoDto matchMenuInfoDto = matchMenuMapper.findByMatchId(matchMenuId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 7; i++) {
            //在原来的基础上添加天数
            long time = matchMenuInfoDto.getMatchMenuDate().getTime() + i*24*60*60*1000;
            Date date = new Date(time);
            String matchMenuDate = sdf.format(date);
            matchMenuMapper.updateMatchMenuNum(matchMenuInfoDto.getMenuId(),matchMenuInfoDto.getMatchMenuTime(),matchMenuDate,matchMenuNum);
        }

    }

    /**
     * 功能说明：
     * 删除指定id菜品搭配
     * @param matchMenuId
     */
    @Override
    public void delMatchMenu(Integer matchMenuId) {
        matchMenuMapper.delMatchMenuByMacthMenuId(matchMenuId);

    }

    /**
     * 功能说明：
     * 删除接下来7天的菜品搭配
     * @param matchMenuId
     */
    @Override
    public void delSevenMatchMenu(Integer matchMenuId) {
        MatchMenuInfoDto matchMenuInfoDto = matchMenuMapper.findByMatchId(matchMenuId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 7; i++) {
            //在原来的基础上添加天数
            long time = matchMenuInfoDto.getMatchMenuDate().getTime() + i*24*60*60*1000;
            Date date = new Date(time);
            String matchMenuDate = sdf.format(date);
            matchMenuMapper.delMatchMenu(matchMenuInfoDto.getMenuId(),matchMenuInfoDto.getMatchMenuTime(),matchMenuDate);
        }
    }

    @Override
    public Msg addMatchMenu(List<MatchMenu> matchMenuList, boolean seven) {
        msg = Msg.success();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取添加菜品的时间
        String getDate = matchMenuList.get(0).getMatchMenuDate();
        int sevenNum = 1;
        //判断是否接下来7天匹配
        if (seven == true){
            sevenNum = 7;
        }
        for (int i = 0; i < sevenNum; i++) {
            long time = 0;
            try {
                //在原来的基础上添加天数
                time = sdf.parse(getDate).getTime() + i * 24 * 60 * 60 * 1000;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date date = new Date(time);
            String matchMenuDate = sdf.format(date);
            String message;
            for (MatchMenu matchMenu : matchMenuList) {
                //设置添加时间
                matchMenu.setMatchMenuDate(matchMenuDate);
                //设置默认数量
                matchMenu.setMatchMenuNum(20);
                //提示信息
                message = matchMenu.getMatchMenuDate()+matchMenu.getMatchMenuTime()+matchMenu.getMenu().getMenuName();
                //查找是否重复
                MatchMenu oldMatchMenu = matchMenuMapper.findMatchMenu(matchMenu);
                if (oldMatchMenu == null) {
                    matchMenuMapper.addMatchMenu(matchMenu);
                    msg.add(message, message+"添加成功");
                }
                if (oldMatchMenu != null) {
                    msg.add(message, message+"添加失败，有重复的数据");
                }
            }

        }
        return msg;
    }
}
