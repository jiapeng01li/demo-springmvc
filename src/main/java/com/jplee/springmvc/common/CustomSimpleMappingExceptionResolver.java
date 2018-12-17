package com.jplee.springmvc.common; 

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver; 
/*   
 * @ClassName: CustomSimpleMappingExceptionResolver
 * @Description: TODO(异常处理器)
 * @author jiapeng01.li
 * @date 2018年9月20日
 */

public class CustomSimpleMappingExceptionResolver extends  
        SimpleMappingExceptionResolver {
	private static final Logger logger = LoggerFactory.getLogger(CustomSimpleMappingExceptionResolver.class);
  
    @Override  
    protected ModelAndView doResolveException(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex) {  
    	
    	logger.info("-------------------------->系统异常", ex);
        // 为选择的错误视图公开模型和视图。
        String viewName = determineViewName(ex, request);  

        //返回消息
        ModelAndView mv = new ModelAndView();
        mv.setViewName(viewName);
        StringWriter sw = new StringWriter(); 
        ex.printStackTrace(new PrintWriter(sw, true)); 
        mv.addObject("exp", sw.toString());
        
        if (viewName != null) {// JSP格式返回  
            if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request  
                    .getHeader("X-Requested-With")!= null && request  
                    .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {  
                // 如果不是异步请求  
                // 如果指定，则应用HTTP状态代码进行错误视图。 
                // 只在处理顶级请求时才应用它。  
                Integer statusCode = determineStatusCode(request, viewName);  
                if (statusCode != null) {  
                    applyStatusCodeIfPossible(request, response, statusCode);  
                }
                logger.error(ex.getMessage(), ex);
//                return getModelAndView(viewName, ex, request); 
                return mv;
            } else {// JSON格式返回  
                try { 
                	response.setContentType("text/html; charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");
                	logger.error("request param:" + request.getAttribute("request_body"));
					logger.error("request URL:" + request.getRequestURL());
					logger.error(ex.getMessage(), ex);
					
                    PrintWriter writer = response.getWriter();  
                    writer.write(ex.getMessage());  
                    writer.flush();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
                return null;  
  
            }  
        } else {  
        	return super.doResolveException(request, response, handler, ex);
        }  
    }  
}  
