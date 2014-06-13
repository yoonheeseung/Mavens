/* board.sql */

create table board(
board_no number(20) primary key,
board_name varchar2(30) not null,
board_title varchar2(300) not null,
board_pwd varchar2(30) not null,
board_cont varchar2(4000) not null,
board_hit number(20) default 0,
board_ref number(20),
board_step number(20),
board_level number(20),
board_date date
);

create sequence board_no_seq
increment by 1 start with 1 nocache;

select * from ZIPCODE where rownum < 20;

select * from board;