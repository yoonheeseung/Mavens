package com.naver.model;

public class BbsBean {//자료실 저장 Bean 클래스
	
	private int bbs_no;//글번호
	private String bbs_name;//이름
	private String bbs_title;//제목
	private String bbs_pwd;//비번
	private String bbs_cont;//내용
	private String bbs_file;//이진파일명
	private int bbs_hit;//조회수
	
	/*답변글*/
	private int bbs_ref;//원본글과 답변글을 묶어주는 글그룹 번호 역활
	private int bbs_step;//첫번째 답변글 1,두번째 답변글은 2 원본글은 0
	private int bbs_level;//답변글 정렬순선
	private String bbs_date;//등록 날짜
    
	/* 쪽 나누기(페이징) */
	private int startrow;
	private int endrow;
	
	/* 검색필드 와 검색어 저장변수 */
	private String find_field;//검색필드
	private String find_name;//검색어
	
	public int getBbs_no() {
		return bbs_no;
	}
	public void setBbs_no(int bbs_no) {
		this.bbs_no = bbs_no;
	}
	public String getBbs_name() {
		return bbs_name;
	}
	public void setBbs_name(String bbs_name) {
		this.bbs_name = bbs_name;
	}
	public String getBbs_title() {
		return bbs_title;
	}
	public void setBbs_title(String bbs_title) {
		this.bbs_title = bbs_title;
	}
	public String getBbs_pwd() {
		return bbs_pwd;
	}
	public void setBbs_pwd(String bbs_pwd) {
		this.bbs_pwd = bbs_pwd;
	}
	public String getBbs_cont() {
		return bbs_cont;
	}
	public void setBbs_cont(String bbs_cont) {
		this.bbs_cont = bbs_cont;
	}
	public String getBbs_file() {
		return bbs_file;
	}
	public void setBbs_file(String bbs_file) {
		this.bbs_file = bbs_file;
	}
	public int getBbs_hit() {
		return bbs_hit;
	}
	public void setBbs_hit(int bbs_hit) {
		this.bbs_hit = bbs_hit;
	}
	public int getBbs_ref() {
		return bbs_ref;
	}
	public void setBbs_ref(int bbs_ref) {
		this.bbs_ref = bbs_ref;
	}
	public int getBbs_step() {
		return bbs_step;
	}
	public void setBbs_step(int bbs_step) {
		this.bbs_step = bbs_step;
	}
	public int getBbs_level() {
		return bbs_level;
	}
	public void setBbs_level(int bbs_level) {
		this.bbs_level = bbs_level;
	}
	public String getBbs_date() {
		return bbs_date;
	}
	public void setBbs_date(String bbs_date) {
		this.bbs_date = bbs_date.substring(0, 10);
	}
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
	
	
}
