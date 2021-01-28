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
		System.out.println("GList");
		
		return sqlSession.selectList("guest.selectList");
	}
	
	//등록
	public int insert(GuestVo gvo) {
		
		System.out.println("add" + gvo.toString());
		
		return sqlSession.insert("guest.insert", gvo);
	}
	
	//1명 데이터 가져오기
	public GuestVo getGuest(int no){
		System.out.println("getGuest" + no);
		
		return sqlSession.selectOne("guest.selectOne", no);
	}
	
	//삭제
	public int delete(GuestVo gvo) {
		System.out.println("delete" + gvo);
		
		return sqlSession.delete("guest.delete", gvo);
		
	}

}