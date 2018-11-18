package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.entity.Sug;
import com.bmsoft.canteensystem.util.Msg;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chengpeng
 * @create 2018-10-18 19:17
 */
@FeignClient(value = "SUG-PROVIDER")
public interface SugClientService {
    @RequestMapping(value = "/sug/list",method = RequestMethod.POST)
    Msg list(@ModelAttribute("Sug") Sug sug, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

    @RequestMapping(value = "/sug/add",method = RequestMethod.POST)
    Msg add(@ModelAttribute("Sug") Sug sug);
}
