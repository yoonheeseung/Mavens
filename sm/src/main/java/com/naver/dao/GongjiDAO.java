package com.naver.dao;

import java.util.List;

import com.naver.model.GongjiBean;

public interface GongjiDAO {

	List<GongjiBean> getIndex_gongjiList();//최신 공지 목록 5개 가져오기

	void updateHit(int gongji_no);//조회수 증가

	GongjiBean getGongjiCont(int gongji_no);//공지 내용

	int getListCount();//전체 수

	List<GongjiBean> getGongjiList(GongjiBean g);

	
	

}
