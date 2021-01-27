package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	//회원가입-->회원정보저장
	
	public int insert(UserVo uvo) {
		
		System.out.println("insert: " + uvo.toString());
		
		int count = sqlSession.insert("user.insert", uvo);
		
		return count;
	}
	
	//로그인 -->회원정보 가져오기
	public UserVo selectUser(UserVo uvo) {
		System.out.println("selectUser: " + uvo.toString());
		
		UserVo vo = sqlSession.selectOne("user.selectUser", uvo);
		
		return vo;
	}
	
	//회원정보수정1 -->회원정보 가져오기
	public UserVo select(int no) {
		System.out.println("select(no):" + no);
		
		UserVo uvo = sqlSession.selectOne("user.select", no);
		
		System.out.println("uvo: " + uvo);
		return uvo;
	}
	
	//회원정보수정2 -->회원정보 변경하기
	public int update(UserVo uvo) {
		
		int count = sqlSession.update("user.update", uvo);
		
		return count;
	}
	
}
