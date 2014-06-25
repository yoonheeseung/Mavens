package com.naver.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.naver.model.BoardBean;
import com.naver.model.GongjiBean;

public class AdminGongjiDAOImpl implements AdminGongjiDAO{

	private SqlSession sqlSession;
	//mybatis 실행 변수

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}//setter DI 설정

	/* 검색 전후 레코드 갯수 */
	public int getListCount(GongjiBean g) {
	 return sqlSession.selectOne("AdminGongji.ag_count", g);
	}

	/* 검색 전후 목록 */
	public List<BoardBean> getGongjiList(GongjiBean g) {
	 return sqlSession.selectList("AdminGongji.ag_list", g);
	}

	/*관리자 공지 저장 */
	public void insertG(GongjiBean g) {
		sqlSession.insert("AdminGongji.ag_in", g);
	}

	/*관리자 공지내용+수정+삭제 */
	public GongjiBean getGongjiCont(int gongji_no) {
	  return sqlSession.selectOne("AdminGongji.ag_cont", gongji_no);
	}

	/* 관리자 공지 수정*/
	public void editG(GongjiBean eg) {
	sqlSession.update("AdminGongji.ag_edit", eg);
		
	}

	public void delG(int gongji_no) {
	sqlSession.delete("AdminGongji.ag_del", gongji_no);
		
	}
	
	
	
	
}
