package com.example.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/*
 * @Author Hman
 * @Description     模拟Token验证 熟悉 zuul过滤器
 * @Date 2019/3/18 18:31
 * @Version  1.0
 **/
@Component
public class TokenFilter extends ZuulFilter {

    //四种类型：pre,routing,error,post
    //pre：主要用在路由映射的阶段是寻找路由映射表的
    //routing:具体的路由转发过滤器是在routing路由器，具体的请求转发的时候会调用
    //error:一旦前面的过滤器出错了，会调用error过滤器。
    //post:当routing，error运行完后才会调用该过滤器，是在最后阶段的
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    //自定义过滤器执行的顺序，数值越大越靠后执行，越小就越先执行
    @Override
    public int filterOrder() {
        return 0;
    }

    //控制过滤器生效不生效，可以在里面写一串逻辑来控制
    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        //System.out.println(request.getRequestURI()); ///apigateway/product/api/v1/product/list
        //System.out.println(request.getRequestURL()); //http://localhost:9000/apigateway/product/api/v1/product/list

        if ("/apigateway/order/feign/findAll".equalsIgnoreCase(request.getRequestURI())){
            return true;
        }

        return false;
    }

    //执行过滤逻辑 (判断有无token)
    @Override
    public Object run() throws ZuulException {
        System.out.println("执行执行过滤器啦~~");
        //JWT
        RequestContext requestContext =  RequestContext.getCurrentContext();
        HttpServletRequest  request = requestContext.getRequest();

        //token对象
        String token = request.getHeader("token");

        if(StringUtils.isBlank((token))){
            token  = request.getParameter("token");
        }


        //登录校验逻辑  根据公司情况自定义 JWT
        if (StringUtils.isBlank(token)) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }


        return null;
    }
}
