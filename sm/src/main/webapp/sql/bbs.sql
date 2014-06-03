/* bbs.sql */

create table bbs(
bbs_no number(20) primary key,
bbs_name varchar2(30) not null,
bbs_title varchar2(200) not null,
bbs_pwd varchar2(20) not null,
bbs_cont varchar2(4000) not null,
bbs_file varchar2(100),/*이진파일명*/
bbs_hit number(20) default 0,
bbs_ref number(20),
bbs_step number(20),
bbs_level number(20),
bbs_date date
);

create sequence bbs_no_seq
increment by 1 start with 1 nocache;

select * from bbs order by bbs_no desc;