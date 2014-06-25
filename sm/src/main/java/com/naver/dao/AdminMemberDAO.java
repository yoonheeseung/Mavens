package com.naver.dao;

import java.util.List;

import com.naver.model.BbsBean;
import com.naver.model.MemberBean;

public interface AdminMemberDAO {

	int getListCount(MemberBean m);//검색전 후 리스트 갯수

	List<MemberBean> getBbsList(MemberBean m);//검색 전 후 목록
	
	MemberBean getMember(String id);//관리자 회원정보	

	void editM(MemberBean m);//정보수정

	void deleteM(String id);//관리자 회원 삭제


	
	

}
