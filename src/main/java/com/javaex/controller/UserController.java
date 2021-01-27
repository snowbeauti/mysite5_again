package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	// 필드
	@Autowired
	private UserDao udao;

	// 회원가입폼
	@RequestMapping(value = "/joinform", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("/user/joinform");

		return "user/joinForm";
	}

	// 회원가입
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo uvo) {
		System.out.println("/user/join");
		System.out.println("controller.uvo" + uvo.toString());

		udao.insert(uvo);

		return "user/joinOk";
	}

	// 로그인폼
	@RequestMapping(value = "/loginform", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("/user/loginForm");

		return "user/loginForm";
	}

	// 로그인
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo uvo, HttpSession session) {
		System.out.println("/user/login");
		System.out.println("controller.uvo" + uvo.toString());

		UserVo authUser = udao.selectUser(uvo);

		if (authUser == null) {
			System.out.println("로그인실패");

			return "redirect:/user/loginForm?result=fail";
		} else {
			System.out.println("로그인성공");
			session.setAttribute("authUser", authUser);

			
			return "main/index";
		}

	}

	// 로그아웃
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("로그아웃");
		
		session.removeAttribute("authUser");
		session.invalidate();

		return "/main/index";
	}
	
	//회원정보수정폼
	@RequestMapping(value = "/mform")
	public String mform(Model model, HttpSession session) {
		System.out.println("mform");
		
		//세션에 있는 no가져오기
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		System.out.println(authUser);
		
		//회원정보가져오기
		model.addAttribute("uservo", udao.select(authUser.getNo()));
		
		
		return "user/modifyForm";
	}

	//회원정보수정
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute UserVo uvo, HttpSession session) {
		System.out.println("modify");
			
			System.out.println(uvo);	
			
			udao.update(uvo);
			
			session.setAttribute("authUser", uvo);
			
			return "redirect:/";
	}
}
