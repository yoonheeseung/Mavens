/* zipcode.sql */
select * from zipcode;

alter table zipcode rename to zipcode1;

select * from zipcode1;

create table zipcode(
no number(10) primary key,
zipcode varchar2(10),/*우편번호*/
sido varchar2(20),/* 시 도 */
gugun varchar2(30),/* 구 군 */
dong varchar2(100),/* 읍면동 */
bunji varchar2(100)/* 번지 */
);

alter table zipcode modify no number(10);
select * from zipcode;
select * from zipcode where dong like '%개%';

/* 월말평가 예상문제
 * 1.검색에서 하나이상의 문자와 매핑:%
 * 2.검색에서 단 하나의 문자와 매핑:?
 * 3.검색에서 ~와 비슷한 문자열을 찾아주는 키워드:like
 * 4.검색 효율 속도를 향상하기 위해서 오라클에서는 인덱스를 사용하는 경우도 있다.
 * 5.중복레코드를 단 하나의 레코드로 검색하는 키워드: distinct
 * 6.오라클에서 대용량 자료를 저장하는 법
 *   가.시작-실행-cmd실행
 *   나.대용량 데이터가 저장된 쿼리문 폴더 경로로 이동한다.
 *   다. sqlplus hr/123456 < zipcode-01.sql 실행하면 대용량 데이터를 한꺼번에 저장할 수 있다. 
 * */
