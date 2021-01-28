package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Service
public class GuestService {

	@Autowired
	private GuestDao gdao;
	
	
	//리스트
	public List<GuestVo> GList(){
		System.out.println("gservice GList");
		
		return gdao.GList();
	}
	
	//등록
	public int add(GuestVo gvo) {
		System.out.println("gservice add");
		
		return gdao.insert(gvo);
	}
	
	//삭제 회원선택
	public GuestVo getguest(int no) {
		System.out.println("gservice getguest");
		
		return gdao.getGuest(no);
	}
	
	//삭제
	public int delete(GuestVo gvo) {
		System.out.println("gservice delete");
		
		return gdao.delete(gvo);
	}
	
}
