package com.jplee.springmvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

		@RequestMapping(value="/helloworld", method=RequestMethod.GET)
		public String helloworld() {
			System.out.println("hello world");
			return "helloworld";
		}
		
		/*@RequestMapping(value="/login")
		public String login() {
			System.out.println("login");
			return "login";
		}*/
		
		@RequestMapping(value="/index", method=RequestMethod.POST)
		public String index(@CookieValue("JSESSIONID")String sessionId, @RequestParam(value="username")String username,String password) {
			System.out.println("sessionId:"+sessionId+"username:"+username+"password:"+password);
			return "redirect:helloworld";
		}
		
		
		@RequestMapping("/")
		public ModelAndView login(HttpSession session){
			System.out.println(session.getId());
			ModelAndView mav = new ModelAndView();
			mav.setViewName("login");
			return mav;
		}
}
