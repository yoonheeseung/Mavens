package com.naver.dao;

import java.util.List;

import com.naver.model.BoardBean;
import com.naver.model.GongjiBean;

public interface AdminGongjiDAO {

	int getListCount(GongjiBean g); //검색전 후 레코드갯수

	List<BoardBean> getGongjiList(GongjiBean g);//검색 전 후 목록

	void insertG(GongjiBean g);//공지 저장

	GongjiBean getGongjiCont(int gongji_no);//관리자 공지내용+수정+삭제

	void editG(GongjiBean eg);//관리자 공지수정

	void delG(int gongji_no); //관리자 공지 삭제
	

}
