package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galservice;
	
	//전체리스트 출력
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword, 
			           @RequestParam(value = "crtPage", required = false, defaultValue = "1") int crtPage,
			           Model model, 
			           HttpSession session) {
		System.out.println("GalleryCnt.list()");
		
 
		Map<String, Object> pMap = galservice.galleryList(keyword, crtPage);
		model.addAttribute("pMap", pMap);
		System.out.println("pMap: " + pMap);
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		model.addAttribute("authUser", authUser);
		
		return "/gallery/list";
	}
	

	//이미지 한개 정보 조회
	@ResponseBody
	@RequestMapping(value = "/select", method = {RequestMethod.GET, RequestMethod.POST})
	public GalleryVo select(@RequestParam("no") int no) {
		System.out.println("GalleryCnt.select()");
		
		return galservice.selectOne(no);
	}


	
	//파일 업로드
		@RequestMapping(value = "/upload", method = {RequestMethod.GET, RequestMethod.POST})
		public String upload(@RequestParam("file") MultipartFile file, 
				             @ModelAttribute GalleryVo gvo,
				             Model model,
				             HttpSession session) {
			System.out.println("Gallery-cnt.upload()");
			System.out.println(gvo);
			
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			gvo.setUser_no(authUser.getNo());
			
			galservice.restore(file, gvo);
			
			
			return "redirect:/gallery/list"; 
		}
		
		//파일 삭제
		@RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
		public String delete(@RequestParam("no") int no) {
			System.out.println("Gallery-cnt.delete()");
			
			galservice.delete(no);
			
			return "redirect:/gallery/list"; 
		}

}
