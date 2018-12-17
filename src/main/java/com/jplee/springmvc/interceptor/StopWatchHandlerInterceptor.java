package com.jplee.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * @Comment 性能监控拦截器
 * @Author jiapeng.li
 * @Date 2017年9月20日 上午11:09:04
 * @return
 */
public class StopWatchHandlerInterceptor extends HandlerInterceptorAdapter {
	
	private Logger logger = LoggerFactory.getLogger(StopWatchHandlerInterceptor.class);
	
	//Spring提供的一个命名的ThreadLocal实现
    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long beginTime = System.currentTimeMillis();// 1、开始时间

        logger.info("开始时间："+beginTime);
        System.out.println("开始时间："+beginTime);

        startTimeThreadLocal.set(beginTime);// 线程绑定变量（该数据只有当前请求的线程可见）

        return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object obj, Exception exception) throws Exception {
		long endTime = System.currentTimeMillis();//2、结束时间 
        long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
        long consumeTime = endTime - beginTime;//3、消耗的时间  
        if(consumeTime > 500) {//此处认为处理时间超过500毫秒的请求为慢请求 
        	System.out.println(String.format("%s 花费时间 %d 毫秒", request.getRequestURI(), consumeTime));
            //记录到日志文件
            logger.warn(String.format("%s 花费时间 %d 毫秒", request.getRequestURI(), consumeTime));
        }else{
        	System.out.println(String.format("%s 花费时间 %d 毫秒", request.getRequestURI(), consumeTime));
            logger.info(String.format("%s 花费时间 %d 毫秒", request.getRequestURI(), consumeTime));
        }
	}
}
