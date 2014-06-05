package com.naver.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.naver.model.BbsBean;

public class BbsDAOImpl implements BbsDAO {

	private SqlSession sqlSession;//mybatis 실행 변수

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}//setter DI 의존관계 설정
	/*
	 * 월말평가문제 예상:mybatis쿼리문 실행메서드 정리
	 * 1.insert():레코드 저장. 반환값은 저장 성공시 
	 *            레코드 행 갯수 int형으로 반환
	 * 2.update():레코드 수정
	 * 3.delete():레코드 삭제
	 * 4.selectOne():단 하나의레코드만 반환
	 * 5.selectList():하나이상의 레코드를 반환해서 컬렉션List로 반환
	 */
	/* 자료실 저장 */
	public void insertBbs(BbsBean b) {
		sqlSession.insert("Bbs.bbs_in", b);
		//Bbs 네임스페이스 이름, bbs_in은 insert아이디 이름
	}
	/* 검색 전후 레코드 갯수 */
	public int getListCount(BbsBean b) {
		int count=0;
		count=sqlSession.selectOne("Bbs.bbs_count",b);
		//bbs_count는 select 아이디 이름
		return count;
	}
	/* 검색 전후 목록 */
	public List<BbsBean> getBbsList(BbsBean b) {
		return sqlSession.selectList("Bbs.bbs_list", b);
	}
	/* 조회수 증가 */
	public void updateHit(int bbs_no) {
		sqlSession.update("Bbs.bbs_hit", bbs_no);
	}
	/* 내용보기+수정폼+답변글폼+삭제폼 */
	public BbsBean getCont(int bbs_no) {
	    return sqlSession.selectOne("Bbs.bbs_cont", bbs_no);
	}
	/* 답변 저장 */
	public void reply(BbsBean b) {
		sqlSession.update("Bbs.bbsLevel", b);//답변글 레벨증가
		sqlSession.insert("Bbs.bbs_reply",b);//답변 저장
		
	}
	
}
