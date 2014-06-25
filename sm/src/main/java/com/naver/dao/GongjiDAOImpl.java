package com.naver.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.naver.model.GongjiBean;

public class GongjiDAOImpl implements GongjiDAO{
	private SqlSession sqlSession;
	//mybatis 쿼리문 실행 변수


	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	/* 최신 공지 목록 5개 보기 */
	public List<GongjiBean> getIndex_gongjiList() {
		return sqlSession.selectList("Gongji.ig_list");
		//Gongji가 namespace명, ig_list가 id명
	}

	/* 조회수 증가 */
	public void updateHit(int gongji_no) {
		sqlSession.update("Gongji.ig_count", gongji_no);
	}

	/* 공지내용 */
	public GongjiBean getGongjiCont(int gongji_no) {
		return sqlSession.selectOne("Gongji.ig_cont", gongji_no);
	}

	/*공지 총 개수*/
	public int getListCount() {
		int count=0;
		count=sqlSession.selectOne("Gongji.ig_count2");
		return count;
	}
    /*공지목록*/
	public List<GongjiBean> getGongjiList(GongjiBean g) {
		return sqlSession.selectList("Gongji.ig_list2",g);
	}	


}
