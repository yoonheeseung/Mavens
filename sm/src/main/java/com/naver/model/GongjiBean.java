package com.naver.model;

public class GongjiBean {

	/* 공지사항은 관리자 단 먼저 작성하고 사용자단 작성한다. */
	private int gongji_no;
	private String gongji_name;
	private String gongji_title;
	private String gongji_pwd;
	private String gongji_cont;
	private int gongji_hit;
	private String gongji_date;
	
	/* 페이징 변수 */

	private int startrow;
	private int endrow;
	
	/*검색어 와 검색필드 */
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

	public int getGongji_no() {
		return gongji_no;
	}

	public void setGongji_no(int gongji_no) {
		this.gongji_no = gongji_no;
	}

	public String getGongji_name() {
		return gongji_name;
	}

	public void setGongji_name(String gongji_name) {
		this.gongji_name = gongji_name;
	}

	public String getGongji_title() {
		return gongji_title;
	}

	public void setGongji_title(String gongji_title) {
		this.gongji_title = gongji_title;
	}

	public String getGongji_pwd() {
		return gongji_pwd;
	}

	public void setGongji_pwd(String gongji_pwd) {
		this.gongji_pwd = gongji_pwd;
	}

	public String getGongji_cont() {
		return gongji_cont;
	}

	public void setGongji_cont(String gongji_cont) {
		this.gongji_cont = gongji_cont;
	}

	public int getGongji_hit() {
		return gongji_hit;
	}

	public void setGongji_hit(int gongji_hit) {
		this.gongji_hit = gongji_hit;
	}

	public String getGongji_date() {
		return gongji_date;
	}

	public void setGongji_date(String gongji_date) {
		this.gongji_date = gongji_date.substring(0,10);
	}
}
