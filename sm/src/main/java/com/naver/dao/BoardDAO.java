package com.naver.dao;

import java.util.List;

import com.naver.model.BbsBean;
import com.naver.model.BoardBean;

public interface BoardDAO {

	void insertBoard(BoardBean b);//저장

	int getListCount(BoardBean b);//검색 전후 레코드 갯수

	List<BoardBean> getBoardList(BoardBean b);//검색 전후 목록

	BoardBean getBoardCont(int board_no);//내용보기+수정폼+삭제폼+답변글

	void updateHit(int board_no);//조회수 증가
		

}
