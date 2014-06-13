<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/admin_header.jsp" %>

<!-- 관리자 메인내용 -->
<div id="aBbs_cont">
	<h2 class="aBbscont_title">관리자 자료실 내용</h2>
	<table id="aBbscont_t">
	<tr>
	<th>제목</th>
	<td>${b.bbs_title}</td>
	</tr>
	<tr>
	<th>내용</th>
	<td>${b.bbs_cont}</td>
	</tr>
	<tr>
	<th>조회수</th>
	<td>${b.bbs_hit}</td>
	</tr>
	<tr>
	<c:if test="${!empty b.bbs_file}">
	<tr>
	<th>첨부파일</th>
	<td>
	<img src="./upload${b.bbs_file}" width="100" height="100" />
	<br/>
	${b.bbs_file}
	</td>
	</tr>
	</c:if>
	<tr>
	<th colspan="2">
	<input type="button" value="목록" class="input_b" onclick="location='admin_bbs_list.do?page=${page}'"/>
	</th>
	</tr>
	</table>
</div>

<jsp:include page="../../include/admin_footer.jsp" />