package com.lhw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lhw.pojo.User;
import com.lhw.util.RedisCacheUtil;

@Controller
public class MainController {
	
	@RequestMapping("/toMain")
	public String toMain(HttpServletRequest request, HttpSession session){
		String token = request.getParameter("token"); 
		if(token != null){
			User user = (User) RedisCacheUtil.get(token);
			session.setAttribute("USER_SESSION", user);
			return "main";
		}
		return "redirect:http://localhost:8080/LoginAndRegister/toLogin?url=" + "http://localhost:8080/MainIndex/toMain";
	}
	
	@RequestMapping("/to")
	public String to(){
		return "main";
	}
}
