package com.naver.dao;

import java.util.List;

import com.naver.model.BbsBean;

public interface BbsDAO {

	void insertBbs(BbsBean b);//자료실 저장

	int getListCount(BbsBean b);//검색 전후 레코드 갯수

	List<BbsBean> getBbsList(BbsBean b);//검색 전후 목록

	void updateHit(int bbs_no);//조회수 증가

	BbsBean getCont(int bbs_no);//내용보기+수정폼+답변글폼+삭제폼

	void reply(BbsBean b);//답변 저장

	void updateBbs(BbsBean b);//수정

	void del(int bbs_no);//삭제
	
	
	
	
	
	

}
