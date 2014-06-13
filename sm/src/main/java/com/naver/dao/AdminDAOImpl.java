package com.naver.dao;

import org.apache.ibatis.session.SqlSession;

import com.naver.model.AdminBean;

public class AdminDAOImpl implements AdminDAO {

	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	/* 관리자 아이디,비번,이름을 저장 */
	public void insertAdmin(AdminBean ab) {
	sqlSession.insert("Admin.admin_in", ab);
		
	}
	public AdminBean find(String admin_id) {
     return  sqlSession.selectOne("Admin.admin_id",admin_id);
	
	}
	
	
}
