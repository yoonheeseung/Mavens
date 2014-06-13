package com.naver.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.naver.model.BbsBean;
import com.naver.model.BoardBean;

public class BoardDAOImpl implements BoardDAO{
	private SqlSession sqlSession;//mybatis 쿼리문 실행 변수

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}//setter DI 의존관계 설정

	/* 게시판 저장 */
	public void insertBoard(BoardBean b) {
		this.sqlSession.insert("Board.board_in", b);
	//Borad는 네임스페이스 이름, board_in은 insert아이디명
	}

	/* 검색 전 후 레코드 갯수 */
	public int getListCount(BoardBean b) {
		return sqlSession.selectOne("Board.board_count", b);
	}

	/* 검색 전 후 목록 */
	public List<BoardBean> getBoardList(BoardBean b) {
		return sqlSession.selectList("Board.board_list", b);
	}
	/*내용보기+수정폼+삭제폼+답변글폼 */
	public BoardBean getBoardCont(int board_no) {
		return sqlSession.selectOne("Board.board_cont", board_no);
	}

	/* 조회수 증가 */
	public void updateHit(int board_no) {
	 sqlSession.update("Board.board_hit", board_no);
	}
	
	

}
