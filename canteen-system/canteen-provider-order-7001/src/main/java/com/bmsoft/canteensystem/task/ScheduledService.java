package com.bmsoft.canteensystem.task;

import com.bmsoft.canteensystem.service.OrderService;
import com.bmsoft.canteensystem.util.FtpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ScheduledService {

    @Autowired
    private OrderService orderService;

    @Scheduled(cron = "0 0 0 1 * ? ")
    /*@Scheduled(cron = "0/20 * * * * ? ")*/
    public void scheduled(){

        FtpUtil.uploadXls(orderService.getEmpConsumes());

    }
}
