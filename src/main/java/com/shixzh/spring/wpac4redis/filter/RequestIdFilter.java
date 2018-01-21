package com.shixzh.spring.wpac4redis.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.filters.RequestFilter;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shixzh.spring.wpac4redis.util.RequestIdUtil;

public class RequestIdFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestFilter.class);
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("RequestIdFilter init");
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String requestId = RequestIdUtil.getRequestId((HttpServletRequest)request);
        // MDC也就是Meta Data Controller 元数据控制器
        MDC.put("requestId", requestId);
        chain.doFilter(request, response);
        RequestIdUtil.requestIdThreadLocal.remove();
        MDC.remove("requestId");
    }

    @Override
    public void destroy() {
        logger.info("RequestIdFilter destroy");
    }

}
