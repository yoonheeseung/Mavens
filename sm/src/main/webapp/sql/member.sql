/* member.sql */

drop table member;

create table member(
join_id   		varchar2(20) primary key,
join_pwd 		varchar2(100) not null,
join_name 		varchar2(20) not null,
join_zip 		varchar2(5) not null,
join_zip2 		varchar2(5) not null,
join_addr 		varchar2(100) not null,
join_addr2   varchar2(80) not null,
join_phone01 varchar2(5),
join_phone02 varchar2(10),
join_phone03 varchar2(10),
mail_id 		varchar2(30),
mail_domain 	varchar2(50),
join_date 		date,/*가입날자 */
join_state 		number(10),/*가입회원 1, 탈퇴회원 2 */
join_delcont 		varchar2(4000),/* 탈퇴 사유 */
join_deldate 		date /*탈퇴 날짜 */
);


select * from phone;
select * from EMAIL;

select * from member;

update member set join_state=1
 where join_id='unix';