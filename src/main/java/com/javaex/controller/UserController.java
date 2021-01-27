package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	//필드
	@Autowired
	private UserDao udao;
	
	//회원가입폼
	@RequestMapping(value = "/joinform", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("/user/joinform");
		
		return "user/joinForm";
	}
	/*
	//회원가입
	@RequestMapping(value = "/join", method = {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo uvo) {
		System.out.println("/user/join");
		System.out.println("controller.uvo" + uvo.toString());
		
		udao.insert(uvo);
		
		return "user/joinOk";
	}
	
	
	//로그인폼
	@RequestMapping(value = "/loginform", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("/user/loginForm");
		
		return"/user/loginForm";
	}

	//로그인
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo uvo, HttpSession session) {
		System.out.println("/user/login");
		System.out.println("controller.uvo" + uvo.toString());
		
		UserVo authUser = udao.selectUser(uvo);
		
		if(authUser == null) {
			System.out.println("로그인실패");
			
			return "redirect:/user/loginForm?result=fail";
		} else {
			System.out.println("로그인성공");
			session.setAttribute("authUser", authUser);
		
		 	return "/main/index";
		}
	}*/
}
