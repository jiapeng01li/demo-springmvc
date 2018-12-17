package com.jplee.springmvc.common;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
/**
 * 
* @ClassName: CustomerExceptionResolver
* @Description: TODO(异常处理器)
* @author jiapeng01.li
* @date 2018年9月20日
*
 */
public class CustomerExceptionResolver implements HandlerExceptionResolver {


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex) {
        //通过我们自定义异常处理器，继承自HandlerExceptionResolver  来实现我们的异常处理
        //自定义我们的异常信息
        String  msg = "";
        //通过ModelAndView 来实现跳转到我们的错误页面，并且将错误信息带回到页面进行显示
        ModelAndView view = new ModelAndView();
        view.setViewName("error");
        //取出我们自定义的异常信息
        if(ex instanceof BaseException){
        	BaseException exception = (BaseException) ex;
            msg = exception.getErrorDesc();
        }else{
            //获取我们的stringWriter来获取我们的异常信息
            StringWriter writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            //通过ex.printStackTrace(printWriter);来向我们的printWriter当中输入异常信息
            ex.printStackTrace(printWriter);
            msg = writer.toString();
        }
        //获取到异常信息之后，通过短信，邮件等技术，通知相关人员
        view.addObject("msg", msg);
        return view;
    }
}
