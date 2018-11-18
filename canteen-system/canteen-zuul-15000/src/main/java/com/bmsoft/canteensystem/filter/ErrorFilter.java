package com.bmsoft.canteensystem.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@Component
public class ErrorFilter extends ZuulFilter {

//    private static Logger log = LoggerFactory.getLogger(ErrorFilter.class);
//
//    @Override
//    public String filterType() {
//        //异常过滤器
//        return "error";
//    }
//
//    @Override
//    public int filterOrder() {
//        //优先级，数字越大，优先级越低
//        return 0;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        //是否执行该过滤器，true代表需要过滤
//        return true;
//    }
//
//    @Override
//    public Object run() {
//
//        RequestContext ctx = RequestContext.getCurrentContext();
//        //log.info("进入异常过滤器");
//
//       //System.out.println(ctx.getResponseBody());
//
//        ctx.setResponseBody(JSON.toJSONString(Msg.SERVICE_EXCEPTION, SerializerFeature.WriteEnumUsingToString));
//        //System.out.println("error!!!!");
//
//        return null;
//
//    }
private static final Logger logger = LoggerFactory.getLogger(ErrorFilter.class);

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();

        logger.info("进入异常过滤器");

        //System.out.println(ctx.getResponseBody());

        ctx.setResponseBody("出现异常");

        return "error";
    }

}