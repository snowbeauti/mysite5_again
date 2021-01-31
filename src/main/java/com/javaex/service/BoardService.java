package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao bdao;
	
	//리스트
	public List<BoardVo> boardlist(){
		System.out.println("bservice boardList");
		
		return bdao.boardList();
	}
	
	//게시글 가져오기
	public BoardVo read(int no) {
		System.out.println("bservice read");	
		return bdao.selectOne(no);
	}
	
	//조회수
	public int hit(int no) {
		System.out.println("bservice hit");
		return bdao.updateHit(no);
	}
	
	//게시글 수정
	public int modify(BoardVo bvo) {
		System.out.println("bservice modify");
		return bdao.update(bvo);
	}
	
	//게시글 삭제
	public int delete(int no) {
		System.out.println("bservice delete");
		return bdao.delete(no);
	}
	
	//게시글 작성
	public int write(BoardVo bvo) {
		System.out.println("bservice write");
		return bdao.insert(bvo);
	}
	
	//게시글 검색
	public List<BoardVo> searchList(String word) {
		System.out.println("bservice search");
		
		return bdao.search(word);
	}

}
