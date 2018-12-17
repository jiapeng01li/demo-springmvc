package com.jplee.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TestInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		 System.out.println("===========HandlerInterceptor1 preHandle");  
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse, Object obj,
			ModelAndView modelandview) throws Exception {	
		System.out.println("===========HandlerInterceptor1 postHandle"); 
		super.postHandle(httpservletrequest, httpservletresponse, obj, modelandview);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse,
			Object obj, Exception exception) throws Exception {
		 System.out.println("===========HandlerInterceptor1 afterCompletion");
		super.afterCompletion(httpservletrequest, httpservletresponse, obj, exception);
	}
}
