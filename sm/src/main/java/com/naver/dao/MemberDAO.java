package com.naver.dao;

import java.util.List;

import com.naver.model.EmailBean;
import com.naver.model.MemberBean;
import com.naver.model.PhoneBean;
import com.naver.model.ZipcodeBean;

public interface MemberDAO {

	List<PhoneBean> getphoneList();//phoneList

	List<EmailBean> getemailList();//emailList

	MemberBean idcheck(String join_id);//아이디 중복 체크

	List<ZipcodeBean> zip(String string);//우편번호+주소 검색

	void insertMember(MemberBean m);//회원저장

	MemberBean pwd_find(MemberBean pm);//비번 찾기

	void edit_pm(MemberBean rm);//임시비번 저장

	MemberBean loginCheck(String login_id);//로그인 인증

	void editMember(MemberBean eb);//정보 수정

	void delMember(MemberBean dm);//회원탈퇴
	

}
