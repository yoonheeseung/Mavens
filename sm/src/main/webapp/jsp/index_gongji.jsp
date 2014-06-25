<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- function JSTL 태그라이브러리 추가 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>최신 공지 목록 5개 보기</title>
<link rel="stylesheet" type="text/css" href="./cs/gongji.css" />

</head>
<body>
<div id="igongji_wrap">
<h2 class="igongji_title">최신공지목록</h2>
<table id="igongji_t">
<tr>
<th>공지제목</th> <th>공지날짜</th>
</tr>
<c:if test="${!empty ig_list}">
<c:forEach var="g" items="${ig_list}">
  <tr>
  <th>
  <a href="gongji_cont.do?gongji_no=${g.gongji_no}&state=cont"
    onfocus="this.blur();">
    <c:if test="${fn:length(g.gongji_title) >16 }">
    <%--공지제목 문자열 길이가 16자를 초과하는 경우 실행 --%>
    ${fn:substring(g.gongji_title,0,16)}...
    <%--공지제목을 0이상 16미만 사이의 제목만 출력하고 나머지는...으로 표현 --%>
    </c:if>
    <c:if test="${fn:length(g.gongji_title) < 16}" >
    ${g.gongji_title}
  </c:if>
  </a>
<%-- onfocus="this.blur();" 는 하이퍼링크된곳 클릭시 생기는 사각점선 없애준다.--%>
</th>
<th>${g.gongji_date}</th>
</tr>
</c:forEach>
</c:if>
<c:if test="${empty ig_list}">
<tr>
<th colspan="2">공지목록이 없습니다!</th>
</tr>
</c:if>
</table>
</div>
</body>
</html>