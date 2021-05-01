package com.dc.api.filter;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname HttphandelFilterImpl
 * @Description 描述
 * @Date 2021/4/30 14:00
 * @Author diaochuang
 */
public class HandlerInterceptorImpl extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return true;
    }
}
