package com.naver.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.naver.model.BbsBean;

public class AdminBbsDAOImpl implements AdminBbsDAO {

	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}//setter DI 설정

	public int getListCount(BbsBean b) {
		int count=0;
		count = this.sqlSession.selectOne("AdminBbs.adminbbs_count",b);
		return count;
	}

	/* 검색 전 후 목록 */
	public List<BbsBean> getBbsList(BbsBean b) {
	return sqlSession.selectList("AdminBbs.adminbbs_list", b);
	}
    /* 관리자 자료실 저장 */
	public void insertBbs(BbsBean b) {
		sqlSession.insert("AdminBbs.adminbbs_in", b);
	}
   /*관리자 자료실 내용보기+수정폼+삭제폼 */
	public BbsBean getBbsCont(int bbs_no) {
		return sqlSession.selectOne("AdminBbs.adminbbs_cont", bbs_no);
	}
	/* 관리자 자료실 수정 */
	public void editBbs(BbsBean b) {
		sqlSession.update("AdminBbs.adminbbs_edit", b);
		
	}

	/* 관리자 자료실 삭제 */
	public void deleteBbs(int bbs_no) {
		sqlSession.delete("AdminBbs.adminbbs_del", bbs_no);
	}
	
	
	
	
	
}
