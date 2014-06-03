package com.naver.dao;

import java.util.List;

import com.naver.model.BbsBean;

public interface BbsDAO {

	void insertBbs(BbsBean b);//자료실 저장

	int getListCount(BbsBean b);//검색 전후 레코드 갯수

	List<BbsBean> getBbsList(BbsBean b);//검색 전후 목록
	
	
	
	

}
