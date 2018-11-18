package com.bmsoft.canteensystem.filter;

import com.alibaba.fastjson.JSON;
import com.bmsoft.canteensystem.util.Msg;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class LoginFilter extends ZuulFilter {

    private static final Logger LOG = LoggerFactory.getLogger(LoginFilter.class);
    @Override
    public String filterType() {
        return "pre";
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
        HttpServletRequest request = ctx.getRequest();
        //System.out.println("loginFilter");
        String url = request.getRequestURL().toString();
        //System.out.println(url.substring(url.lastIndexOf("/")).equals("login"));
        if(!url.substring(url.lastIndexOf("/")+1).equals("login") && !url.substring(url.lastIndexOf("/")+1).equals("issession")
        && !url.substring(url.lastIndexOf("/")+1).equals("register") && !url.substring(url.lastIndexOf("/")+1).equals("registervalidate")){
            if(request.getSession().getAttribute("userInfo") == null){
                LOG.info("用户未登录");
                //ctx.setSendZuulResponse(false);
                //ctx.setResponseStatusCode(401);
                ctx.setResponseBody(JSON.toJSONString(Msg.fail().setMsg("用户未登录")));
                ctx.getResponse().setContentType("text/html;charset=UTF-8");
            }
        }

        //System.out.println(request.getSession().getAttribute("userInfo"));
        return null;
    }
}
