package com.naver.dao;

import java.util.List;

import com.naver.model.BbsBean;
import com.naver.model.BoardBean;

public interface AdminBoardDAO {

	int getListCount(BoardBean b); //검색 전후 갯수

	List<BoardBean> getBoardList(BoardBean b); //검색 전 후 목록

	void insertBoard(BoardBean b); //저장

	BoardBean getCont(int board_no);//내용보기+수정+삭제

	void editBoard(BoardBean b);//수정

	void delBoard(int board_no);//삭제
	
	
	

	
	


}
