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
	public List<GuestVo> guestList() {
		System.out.println("guestList");
		
		return sqlSession.selectList("guest.selectList");
	}
	
	//등록
	public int insert(GuestVo gvo) {
		
		System.out.println("add" + gvo.toString());
		
		return sqlSession.insert("guest.insert", gvo);
	}
	
	//1명 데이터 가져오기
	public GuestVo getGuest(int no){
		System.out.println("dao getGuest" + no);  //여기까지 성공
		//System.out.println(sqlSession.selectOne("guest.selectOne", no).toString()); //여기서 문제
		return sqlSession.selectOne("guest.selectOne", no);
	}
	
	//삭제
	public int delete(int no) {
		System.out.println("delete" + no);
		
		return sqlSession.delete("guest.delete", no);
		
	}

}