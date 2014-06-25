package com.naver.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.naver.model.BbsBean;
import com.naver.model.MemberBean;

public class AdminMemberDAOImpl implements AdminMemberDAO {

	private SqlSession sqlSession;

	// mybatis 쿼리문 실행 변수

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}// setter DI 설정

	public int getListCount(MemberBean m) {
		return this.sqlSession.selectOne("AdminMember.am_count", m);
	}

	//
	public List<MemberBean> getBbsList(MemberBean m) {
		return sqlSession.selectList("AdminMember.am_list", m);
	}

	// 관리자 회원정보+수정폼
	public MemberBean getMember(String id) {
		return sqlSession.selectOne("AdminMember.am_info", id);
	}

	// 회원정보 수정
	public void editM(MemberBean m) {
		sqlSession.update("AdminMember.am_edit", m);
	}

	// 관리자 회원삭제
	public void deleteM(String id) {
		sqlSession.delete("AdminMember.am_del", id);
	}

}
