/* zipcode.sql */
select * from zipcode;

alter table zipcode rename to zipcode1;

select * from zipcode1;

create table zipcode(
no number(10) primary key,
zipcode varchar2(10),/*�����ȣ*/
sido varchar2(20),/* �� �� */
gugun varchar2(30),/* �� �� */
dong varchar2(100),/* ���鵿 */
bunji varchar2(100)/* ���� */
);

alter table zipcode modify no number(10);
select * from zipcode;
select * from zipcode where dong like '%��%';

/* ������ ������
 * 1.�˻����� �ϳ��̻��� ���ڿ� ����:%
 * 2.�˻����� �� �ϳ��� ���ڿ� ����:?
 * 3.�˻����� ~�� ����� ���ڿ��� ã���ִ� Ű����:like
 * 4.�˻� ȿ�� �ӵ��� ����ϱ� ���ؼ� ����Ŭ������ �ε����� ����ϴ� ��쵵 �ִ�.
 * 5.�ߺ����ڵ带 �� �ϳ��� ���ڵ�� �˻��ϴ� Ű����: distinct
 * 6.����Ŭ���� ��뷮 �ڷḦ �����ϴ� ��
 *   ��.����-����-cmd����
 *   ��.��뷮 �����Ͱ� ����� ������ ���� ��η� �̵��Ѵ�.
 *   ��. sqlplus hr/123456 < zipcode-01.sql �����ϸ� ��뷮 �����͸� �Ѳ����� ������ �� �ִ�. 
 * */
