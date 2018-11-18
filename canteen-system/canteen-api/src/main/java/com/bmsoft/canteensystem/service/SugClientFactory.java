package com.bmsoft.canteensystem.service;

import com.bmsoft.canteensystem.entity.Sug;
import com.bmsoft.canteensystem.util.Msg;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author chengpeng
 * @create 2018-10-23 14:36
 */
@Component//必须添加组件注解
public class SugClientFactory implements FallbackFactory<SugClientService> {
    @Override
    public SugClientService create(Throwable throwable) {
        return new SugClientService() {
            @Override
            public Msg list(Sug sug, int pageNum, int pageSize) {
                return Msg.fail().setMsg("服务器错误！");
            }

            @Override
            public Msg add(Sug sug) {
                return Msg.fail().setMsg("服务器错误！");
            }
        };
    }
}
