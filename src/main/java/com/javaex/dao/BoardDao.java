package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	//리스트 조회
	public List<BoardVo> boardList(){
		System.out.println("bdao boardList");
		
		return sqlSession.selectList("board.selectList");
	}
	
	//게시글 조회
	public BoardVo selectOne(int no) {
		System.out.println("bdao selectOne");
		return sqlSession.selectOne("board.selectOne", no);
	}
	
	//조회수 증가
	public int updateHit(int no) {
		System.out.println("bdao updateHit");
		return sqlSession.update("board.updateHit", no);
	}
	
	//게시글 수정
	public int update(BoardVo bvo) {
		System.out.println("bdao update");
		return sqlSession.update("board.update", bvo);
	}
	
	//게시글 삭제
	public int delete(int no) {
		System.out.println("bdao delete");
		return sqlSession.delete("board.delete", no);
	}
	
	//게시글 작성
	public int insert(BoardVo bvo) {
		System.out.println("bdao insert");
		return sqlSession.insert("board.insert", bvo);
	}
	
	//게시글 찾기
	public List<BoardVo> search(String word) {
		System.out.println("bdao word");
		return sqlSession.selectList("board.search", word);
	}
}
