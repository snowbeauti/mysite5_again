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
		
		System.out.println(uvo.toString());
		
		int count = sqlSession.insert("user.insert", uvo);
		
		return count;
	}
	
	//로그인 -->회원정보 가져오기
	public UserVo selectUser(UserVo uvo) {
		System.out.println("user dao selectUser" + uvo.toString());
		
		UserVo vo = sqlSession.selectOne("user.selectUser", uvo);
		System.out.println(vo.toString());
		
		return vo;
	}
}
