package com.javaex.dao;


import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestDao {
	
	
	@Autowired
	private SqlSession sqlSession;
	
	//조회
	public List<GuestVo> GList() {
		
		List<GuestVo> guestList = sqlSession.selectList("guest.selectList");
		return guestList;
	}
	
	//등록
	public int insert(GuestVo gvo) {
		
		System.out.println(gvo.toString());
		int count = sqlSession.insert("guest.insert", gvo);
		
		return count;
	}
	
	//1명 데이터 가져오기
	public GuestVo getGuest(int no){
		System.out.println("dao: getGuest()" + no);
		
		GuestVo gvo = sqlSession.selectOne("guest.selectOne", no);

		return gvo;
	}
	
	//삭제
	public int delete(GuestVo gvo) {
		System.out.println("delete");
		int count = sqlSession.delete("guest.delete", gvo);
		
		return count;
		
	}

}
