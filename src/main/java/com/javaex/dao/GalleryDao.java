package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;
	

	
	//글 전체 가져오기 (키워드+페이징)
	public List<GalleryVo> galleryList(String keyword, int startRNum, int endRNum){
		System.out.println("GalleryDao galleryList keyword: " + keyword);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		map.put("startRNum", startRNum);
		map.put("endRNum", endRNum);
		System.out.println("map: " + map);
		
		return sqlSession.selectList("gallery.selectList", map);
	}
	
	//전체 글 개수조회
	public int selectTotalCnt(String keyword) {
		
		return sqlSession.selectOne("gallery.selectTotalCnt", keyword);
	}
	
	//이미지 조회
	public GalleryVo selectOne(int no) {
		System.out.println("GalleryDao.selectOne: " + no);
		return sqlSession.selectOne("gallery.selectOne", no);
	}
	
	//이미지 저장
	public int insert(GalleryVo gvo) {
		System.out.println("GalleryDao.insert()");
		
		return sqlSession.insert("gallery.insert", gvo);
	}
	
	//이미지 삭제
	public int delete(int no) {
		System.out.println("GalleryDao.delete()");
		
		return sqlSession.delete("gallery.delete", no);
	}

}
