package com.naver.dao;

import java.util.List;

import com.naver.model.BbsBean;

public interface AdminBbsDAO {

	int getListCount(BbsBean b);//검색 전후 레코드 갯수

	List<BbsBean> getBbsList(BbsBean b); //검색 전 후 목록

	void insertBbs(BbsBean b);//관리자 자료실 저장

	BbsBean getBbsCont(int bbs_no);//내용보기+수정폼+삭제폼

	void editBbs(BbsBean b);//관리자 자료실 수정

	void deleteBbs(int bbs_no);//관리자 자료실 삭제
	
	

}
