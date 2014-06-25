package com.naver.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.naver.model.BoardBean;

public class AdminBoardDAOImpl implements AdminBoardDAO {

	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	/* 검색 전 후 개수 */
	public int getListCount(BoardBean b) {
		return sqlSession.selectOne("AdminBoard.aboard_count", b);
	}

	/* 검색 전 후 목록 */
	public List<BoardBean> getBoardList(BoardBean b) {
		return sqlSession.selectList("AdminBoard.aboard_list", b);
	}

	/* 저장 */
	public void insertBoard(BoardBean b) {
		sqlSession.insert("AdminBoard.aboard_in", b);
	}

	/*내용보기+수정폼+삭제폼*/
	public BoardBean getCont(int board_no) {
		return sqlSession.selectOne("AdminBoard.aboard_cont",board_no);
	}

	//수정
	public void editBoard(BoardBean b) {
	 sqlSession.update("AdminBoard.aboard_edit", b);
	}

	//삭제
	public void delBoard(int board_no) {
		sqlSession.delete("AdminBoard.aboard_del", board_no);
	}

}
