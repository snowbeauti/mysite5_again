package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galdao;

	
	//이미지 정보 조회
	public GalleryVo selectOne(int no) {
		System.out.println("galservice selectOne()");
		System.out.println(galdao.selectOne(no));
		
		return galdao.selectOne(no);
		
	}
	
	//이미지 삭제
	public int delete(int no) {
		System.out.println("galservice delete()");
		
		return galdao.delete(no);
	}
	
	//이미지 업로드
	public GalleryVo restore(MultipartFile file, GalleryVo gvo) {
		System.out.println("galservice.restore()");
		System.out.println(file.getOriginalFilename());
		
		//db 저장할 정보 수집
		String saveDir = "C:\\javaStudy\\upload";
		
		
		//오리지널 파일이름
		String orgName = file.getOriginalFilename();
		
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		
		//서버 저장 파일 이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		
		//서버 파일 패스 --> 저장경로
		String filePath = saveDir + "\\" + saveName;
		
		//파일 사이즈
		long fileSize = file.getSize();
		
		//gvo에 저장
		gvo.setFilePath(filePath);
		gvo.setOrgName(orgName);
		gvo.setSaveName(saveName);
		gvo.setFileSize(fileSize);
		
		
		//서버하드디스크 파일저장
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(out);
			
			bos.write(fileData);
			bos.close();
			
		} catch(IOException e) {
			e.printStackTrace();
			
		}
		System.out.println("galservice gvo : "+gvo);
		
		galdao.insert(gvo);
		
		return gvo;

	}

	//리스트+검색+페이징
		public Map<String, Object> galleryList(String keyword, int crtPage){
			System.out.println("galservice galleryList() keyword: " + keyword);
			

			//페이지당 글갯수
			int listCnt = 16;
			
			//현재 페이지
			//crtPage
			crtPage = (crtPage>0) ? crtPage : (crtPage = 1);

			//시작글 번호 startRNum
			int startRNum = (crtPage * listCnt) -15; 	
			
			//끝글번호 endRNum
			int endRNum = crtPage * listCnt;

			
			List<GalleryVo> galleryList = galdao.galleryList(keyword, startRNum, endRNum);
			////////////////////////////////
			//페이징 계산
			
			//페이지당 버튼 갯수
			int pageBtnCount = 5;
			
			
			//전체 글갯수 구하기
			int totalCount = galdao.selectTotalCnt(keyword);
	
			
			//마지막 버튼 번호
			
			int endPageBtnNo;
			if(crtPage<3) {
				endPageBtnNo = 5;
			} else {
				endPageBtnNo = crtPage + 2;
			}
			  	
			 
			//시작 버튼 번호
				
				int startPageBtnNo;
				
				if(crtPage<3) {
					startPageBtnNo = 1;
				} else {
					startPageBtnNo = crtPage - 2;
				}
					
			//다음버튼 boolean
			boolean next;
			
			if(endPageBtnNo * listCnt < totalCount) {
				next = true;
			} else {
				next = false;
				endPageBtnNo = (int)Math.ceil(totalCount/(double)listCnt);
			}
			
			//이전버튼 boolean
			boolean prev;
			
			if(startPageBtnNo != 1) {
				prev = true;
			} else {
				prev = false;
			}
			
			//galleryList, prev, startPageBtnNo, endPageBtnNo, next   --> jsp map 전달
			Map<String, Object> pMap = new HashMap<String, Object>();
			pMap.put("galleryList", galleryList);
			pMap.put("prev", prev);
			pMap.put("next", next);
			pMap.put("endPageBtnNo", endPageBtnNo);
			pMap.put("startPageBtnNo", startPageBtnNo);
			
			return pMap;
		} 
		
	
}
