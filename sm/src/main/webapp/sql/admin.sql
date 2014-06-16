/* admin.sql */
create table admin(
admin_no number(10),
admin_id varchar2(30) primary key,
admin_pwd varchar2(300) not null,
admin_name varchar2(30),
admin_date date
);


create sequence admin_no_seq
increment by 1 start with 1 nocache;

select * from bbs;

