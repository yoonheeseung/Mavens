package com.naver.model;

public class MemberBean {
	private String join_id;
	private String join_pwd;
	private String join_name;
	private String join_zip;
	private String join_zip2;
	private String join_addr;
	private String join_addr2;
	private String join_phone01;
	private String join_phone02;
	private String join_phone03;
	private String mail_id;
	private String mail_domain;
	private String join_date;
	private int join_state;//가입회원 1, 탈퇴회원 2
	private String join_delcont;//탈퇴사유
	private String join_deldate;//탈퇴 날짜
	
	
	/* 페이징 */
	private int startrow;
	private int endrow;
	
	/*검색*/
	private String find_field;
	private String find_name;
	
	
	
	public int getStartrow() {
		return startrow;
	}
	public void setStartrow(int startrow) {
		this.startrow = startrow;
	}
	public int getEndrow() {
		return endrow;
	}
	public void setEndrow(int endrow) {
		this.endrow = endrow;
	}
	public String getFind_field() {
		return find_field;
	}
	public void setFind_field(String find_field) {
		this.find_field = find_field;
	}
	public String getFind_name() {
		return find_name;
	}
	public void setFind_name(String find_name) {
		this.find_name = find_name;
	}
	
	
	public String getJoin_id() {
		return join_id;
	}
	public void setJoin_id(String join_id) {
		this.join_id = join_id;
	}
	public String getJoin_pwd() {
		return join_pwd;
	}
	public void setJoin_pwd(String join_pwd) {
		this.join_pwd = join_pwd;
	}
	public String getJoin_name() {
		return join_name;
	}
	public void setJoin_name(String join_name) {
		this.join_name = join_name;
	}
	public String getJoin_zip() {
		return join_zip;
	}
	public void setJoin_zip(String join_zip) {
		this.join_zip = join_zip;
	}
	public String getJoin_zip2() {
		return join_zip2;
	}
	public void setJoin_zip2(String join_zip2) {
		this.join_zip2 = join_zip2;
	}
	public String getJoin_addr() {
		return join_addr;
	}
	public void setJoin_addr(String join_addr) {
		this.join_addr = join_addr;
	}
	public String getJoin_addr2() {
		return join_addr2;
	}
	public void setJoin_addr2(String join_addr2) {
		this.join_addr2 = join_addr2;
	}
	public String getJoin_phone01() {
		return join_phone01;
	}
	public void setJoin_phone01(String join_phone01) {
		this.join_phone01 = join_phone01;
	}
	public String getJoin_phone02() {
		return join_phone02;
	}
	public void setJoin_phone02(String join_phone02) {
		this.join_phone02 = join_phone02;
	}
	public String getJoin_phone03() {
		return join_phone03;
	}
	public void setJoin_phone03(String join_phone03) {
		this.join_phone03 = join_phone03;
	}
	public String getMail_id() {
		return mail_id;
	}
	public void setMail_id(String mail_id) {
		this.mail_id = mail_id;
	}
	public String getMail_domain() {
		return mail_domain;
	}
	public void setMail_domain(String mail_domain) {
		this.mail_domain = mail_domain;
	}
	public String getJoin_date() {
		return join_date;
	}
	public void setJoin_date(String join_date) {
		this.join_date = join_date.substring(0, 10);
	}
	public int getJoin_state() {
		return join_state;
	}
	public void setJoin_state(int join_state) {
		this.join_state = join_state;
	}
	public String getJoin_delcont() {
		return join_delcont;
	}
	public void setJoin_delcont(String join_delcont) {
		this.join_delcont = join_delcont;
	}
	public String getJoin_deldate() {
		return join_deldate;
	}
	public void setJoin_deldate(String join_deldate) {
		this.join_deldate = join_deldate;
	}
	
	

}
