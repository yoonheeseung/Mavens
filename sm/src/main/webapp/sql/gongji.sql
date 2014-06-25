/* gongji.sql */
create table gongji(
gongji_no 		number primary key,
gongji_name 	varchar2(30) not null,
gongji_title 	varchar2(200) not null,
gongji_pwd 		varchar2(30) not null,
gongji_cont 	varchar2(4000) not null,
gongji_hit 		number(20) default 0,
gongji_date 	date
);

(gongji_no,gongji_name,gongji_title,gongji_pwd,gongji_cont,gongji_date)
create sequence gongji_no_seq
increment by 1 start with 1 nocache;

select * from gongji order by gongji_no desc;

insert into gongji(gongji_no,gongji_name,gongji_title,gongji_pwd,gongji_cont,gongji_date) values (gongji_no_seq.nextval,'관리자','관리자 공지제목1','aaa','관리자 공지 내용 등록1',sysdate);
insert into gongji(gongji_no,gongji_name,gongji_title,gongji_pwd,gongji_cont,gongji_date) values  (gongji_no_seq.nextval,'관리자','관리자 공지제목2','aaa','관리자 공지 내용 등록2',sysdate);
insert into gongji(gongji_no,gongji_name,gongji_title,gongji_pwd,gongji_cont,gongji_date) values  (gongji_no_seq.nextval,'관리자','관리자 공지제목3','aaa','관리자 공지 내용 등록3',sysdate);
insert into gongji(gongji_no,gongji_name,gongji_title,gongji_pwd,gongji_cont,gongji_date) values  (gongji_no_seq.nextval,'관리자','관리자 공지제목4','aaa','관리자 공지 내용 등록4',sysdate);
insert into gongji(gongji_no,gongji_name,gongji_title,gongji_pwd,gongji_cont,gongji_date) values  (gongji_no_seq.nextval,'관리자','관리자 공지제목5','aaa','관리자 공지 내용 등록5',sysdate);
insert into gongji(gongji_no,gongji_name,gongji_title,gongji_pwd,gongji_cont,gongji_date) values  (gongji_no_seq.nextval,'관리자','관리자 공지제목6','aaa','관리자 공지 내용 등록6',sysdate);
insert into gongji(gongji_no,gongji_name,gongji_title,gongji_pwd,gongji_cont,gongji_date) values  (gongji_no_seq.nextval,'관리자','관리자 공지제목7','aaa','관리자 공지 내용 등록7',sysdate);


insert into gongji values  (gongji_no_seq.nextval,'관리자','관리자 공지제목8','aaa','관리자 공지 내용 등록8',0,sysdate);

select * from gongji;


select *
		from
		(select rowNum rNum, gongji_no, gongji_name,
		 gongji_title, gongji_hit, gongji_date from
		(select * from gongji			
		order by gongji_no desc
		 ))
		where rNum <1 and rNum <10;
  