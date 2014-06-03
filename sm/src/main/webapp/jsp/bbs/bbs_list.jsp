<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%--html5 문서정의 태그--%>
<html>
<head>
<meta charset="UTF-8">
<%-- html5 언어코딩 타입설정 --%>
<title>자료실 목록</title>
<link rel="stylesheet" type="text/css" href="./cs/bbs.css" />
<script src="./js/jquery.js"></script>
<script>
 function find_check(){
	 if($.trim($("#find_name").val())==""){
		 alert("검색어를 입력하세요!");
		 $("#find_name").val("").focus();
		 return false;
	 }
 }
</script>
</head>
<body>
<form action="bbs_list.do" onsubmit="return find_check();">
<%-- method속성을 생략하면 기본으로 get방식 --%>
  <div id="bList_wrap">   
    <h2 class="bList_title">자료실 목록</h2>
    <div id="bList_count">글개수:${listcount}</div>
    <table id="bList_t">
      <tr>
        <th width="6%" height="26" >번호</th> 
        <th width="50%" >제목</th> 
        <th width="14%" >작성자</th> 
        <th width="17%" >작성일</th> 
        <th width="14%" >조회수</th>
      </tr>
      
      <c:if test="${!empty blist}">
        <c:forEach var="list" items="${blist}">
          <tr>
            <td align="center">
              <c:if test="${list.bbs_step == 0}">
              <%--bbs_step이 0이면 원본글일때 실행 ,
              관리자 답변글일때는 실행이 안됨.--%>
                ${list.bbs_ref}
                <%--원본글일때만 글그룹번호를 출력.
                글그룹번호 bbs_ref는 원본글과 답변글을 묶어주
                는 기능을 한다. --%>
              </c:if>
              &nbsp;
            </td>
            
            <td class="left_box" align="left">
              <c:if test="${list.bbs_step !=0 }">
              <%--답변글 위치번호값이 0이 아닐때는
              답변글일때 실행. --%>
                <c:forEach begin="1" end="${list.bbs_step}" step="1">
                <%--1부터 시작해서 1씩증가하는 반복문 --%>
                  &nbsp;
                </c:forEach>
                <img src="./images/AnswerLine.gif"/>
                <%--답변글 이미지 출력 --%>
              </c:if>
 <a href="bbs_cont.do?bbs_no=${list.bbs_no}&page=${page}&state=cont" 
              onfocus="this.blur();">${list.bbs_title}</a>        
 <%-- *.do?no=번호값&page=쪽번호&state=cont get방식으로 3개의
 피라미터값을 웹주소창에 노출해서 넘김. 
 get방식으로 넘기때 주의할점.
  1.한글자료를 넘기면 안된다.
  2.중복되지 않는 자료를 넘김.
  onfocus="this.blur();" 는 하이퍼링크 된곳 클릭시 사각점선이 
  생긴다. 이 사각점선을 사라지게 하는 부분.
 --%>
            </td>
            
            <td align="center">${list.bbs_name}</td>
	  		<td align="center">${list.bbs_date}</td>	
	  		<td align="center">${list.bbs_hit}</td>
          </tr>
        </c:forEach>
      </c:if>
      
      <c:if test="${empty blist}">
        <tr>
          <th colspan="5">목록이 없습니다!!</th>
        </tr>
      </c:if>
    </table>
    
    <!-- 페이징 추가 -->
	<div id="list_paging">
	  <!-- 검색후 페이징 -->
	  <c:if test="${(!empty find_field) && (!empty find_name)}">
			<c:if test = "${page <= 1 }">
			[이전]&nbsp;
			</c:if>
			<c:if test = "${page > 1 }">
			<a href="bbs_list.do?page=${page-1}&find_field=${find_field}&find_name=${find_name}">[이전]</a>&nbsp;
			</c:if>	
			
			
			<c:forEach  var="a" begin = "${startpage}" end = "${endpage}" step = "1">
			 	<c:if test = "${a == page}">
					<${a}>
				</c:if>
				
                <c:if test = "${a != page}">
				 <a href="bbs_list.do?page=${a}&find_field=${find_field}&find_name=${find_name}">[${a}]</a>&nbsp;
				</c:if>
			</c:forEach>	
			
			
			<c:if test = "${page >= maxpage}">
			 [다음]
			</c:if>
			<c:if test = "${page < maxpage }">
			 <a href="bbs_list.do?page=${page+1}&find_field=${find_field}&find_name=${find_name}">[다음]</a>
			</c:if>
	      </c:if>
	  <!-- 검색후 페이징 끝 -->
	  	      
	      <!-- 검색전 페이징 -->
    <c:if test="${(empty find_field) && (empty find_name)}">
	      	<c:if test = "${page <= 1 }">
			[이전]&nbsp;
			</c:if>
			<c:if test = "${page > 1 }">
			<a href="bbs_list.do?page=${page-1}">[이전]</a>&nbsp;
			</c:if>	
			
			
			<c:forEach  var="a" begin = "${startpage}" end = "${endpage}" step = "1">
			 	<c:if test = "${a == page}">
			 	<%--해당 쪽번호가 선택된 경우 실행 --%>
					<${a}>
				</c:if>
				
                <c:if test = "${a != page}">
                <%--해당 쪽번호가 선택 안된 경우 실행 --%>
				 <a href="bbs_list.do?page=${a}">[${a}]</a>&nbsp;
				</c:if>
			</c:forEach>	
			
			
			<c:if test = "${page >= maxpage}">
			 [다음]
			</c:if>
			<c:if test = "${page < maxpage }">
			 <a href="bbs_list.do?page=${page+1}">[다음]</a>
			</c:if>
	    </c:if>
	    <!-- 검색전 페이징(쪽나누기) 끝 -->
	</div>
	
	
	<div id="list_menu">
	   	<input type="button" value="글쓰기" class="input_b"
		onclick="location='bbs_write.do?page=${page}'" />	
		<%-- 검색전 전체 레코드를 검색해서 전체 레코드를 페이징
		할 목록으로 이동할 버튼을 만든다.이럴경우는 get방식으로
		find_name과 find_field를 넘기면 안된다. --%>
     <c:if test="${(!empty find_field) && (!empty find_name)}">		
		<input type="button" value="전체목록"
		onclick="location='bbs_list.do?page=${page}'"
		class="input_b" />	
	</c:if>
   </div>    
 
   <!-- 검색폼 추가 -->
   <div id="list_find">
    <table id="list_f">
     <tr>
      <th>
      <select name="find_field">
       <option value="bbs_title"
       <c:if test="${find_field=='bbs_title'}">
       ${'selected'}</c:if>>제목</option>
       
       <option value="bbs_cont"
       <c:if test="${find_field=='bbs_cont'}">
       ${'selected'}</c:if>>내용</option>
       <%-- bbs_title,bbs_cont는 테이블 필드명. --%>
      </select>
      <input type="text" name="find_name" id="find_name"
      size="16"  value="${find_name}" class="box"/>
      <input type="submit" value="검색" class="input_b"/>
      </th>
     </tr>
    </table>
   </div>
   <!-- 검색폼 추가 끝 -->
   
  </div>
  </form>
</body>
</html>




