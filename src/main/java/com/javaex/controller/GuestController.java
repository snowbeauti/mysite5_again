package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestDao;
import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping(value = "/gbook")
public class GuestController {

	// 필드
	@Autowired
	private GuestDao gdao;
	
	@Autowired
	private GuestService gservice;

	// 리스트
	@RequestMapping(value = "/addlist", method = { RequestMethod.GET, RequestMethod.POST })
	public String addlist(Model model) {
		System.out.println("/gbook/addlist");

		List<GuestVo> gList = gservice.GList();
		System.out.println(gList.toString());
		
		model.addAttribute("gList", gList);

		return "guestbook/AddList";
	}

	// 등록
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public String add(@ModelAttribute GuestVo gvo) {
		System.out.println("/gbook/add");

		int count = gservice.add(gvo);

		return "redirect:/gbook/addlist";
	}
	
	
	// 삭제폼
	@RequestMapping(value = "/dform", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteform() {
		System.out.println("/gbook/deleteForm");

		return "guestbook/DeleteForm";
	}
	
	// 삭제
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no")int no, 
						 @RequestParam("password") String password) {
		System.out.println("/gbook/delete");
		GuestVo gvo = gservice.getguest(no);

		if (gvo.getPassword().equals(password)) {
			System.out.println(gvo + "삭제");

			gservice.delete(gvo);

		} else {
			return "redirect:/gbook/dform?no=" + no + "&result=fail";
		}

		return "redirect:/gbook/addlist";
	}

}