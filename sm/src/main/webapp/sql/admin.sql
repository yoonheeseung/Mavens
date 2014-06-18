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



String[] phone={"010","011","016","018","019"};
		String[] email={"naver.com","daum.net","gmail.com","nate.com","korea.com","직접입력"};
		
		
		
create table phone(
no integer,
phoneno varchar2(3));

create table email(
no integer,
email varchar2(20));

insert into phone values(1,'010');
insert into phone values(2,'011');
insert into phone values(3,'016');
insert into phone values(4,'018');
insert into phone values(5,'019');


insert into email values(1,'naver.com');
insert into email values(2,'daum.net');
insert into email values(3,'gmail.com');
insert into email values(4,'nate.com');
insert into email values(5,'korea.com');
insert into email values(6,'직접입력');
