package com.bmsoft.canteensystem.controller;

import com.bmsoft.canteensystem.entity.Sug;
import com.bmsoft.canteensystem.service.SugService;
import com.bmsoft.canteensystem.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author chengpeng
 * @create 2018-10-18 15:39
 */
@RestController
public class SugController {
    @Autowired
    private SugService sugService;

    @Autowired
    HttpServletRequest httpServletRequest;

    /**
     * 根据反馈信息分页显示反馈信息
     * @param  * @param sug
     * @param pageNum
     * @param pageSize
     * @return com.bmsoft.canteensystem.util.Msg
     */
    @RequestMapping(value = "/sug/list",method = RequestMethod.POST)
    public Msg list(@RequestBody Sug sug, @RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        List<Sug> sugList = sugService.find(sug, pageNum, pageSize);
        Integer total = sugService.getCount(sug);
        return Msg.success().add("sugs",sugList).add("total",total);
    }

    /**
     * 添加反馈信息
     * @param  * @param sug 
     * @return com.bmsoft.canteensystem.util.Msg
     */
    @RequestMapping(value = "/sug/add",method = RequestMethod.POST)
    public Msg add(@RequestBody Sug sug){
       if (sugService.add(sug))
           return Msg.success();
       return Msg.fail();
    }
}
