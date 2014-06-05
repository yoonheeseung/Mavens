<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료실 내용보기</title>
<link rel="stylesheet" type="text/css" href="./cs/bbs.css" />
</head>
<body>
	<div id="bCont_wrap">
		<h2 class="bCont_title">자료실 내용</h2>
		<table id="bCont_t">
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
			<c:if test="${!empty b.bbs_file}">
				<tr>
					<th>첨부파일</th>
					<td>${b.bbs_file}</td>
				</tr>
				<tr>
				<th>첨부한 이미지 보기</th>
				<td>
				<img src="./upload${b.bbs_file}" width="100" height="100" />
				</td>
				</tr>
			</c:if>
			<c:if test="${empty b.bbs_file}">
			<tr>
			<th>첨부파일</th>
			<td>첨부파일 없음</td>
			</tr>
			</c:if>
			<tr>
				<th colspan="2"><input type="button" value="답변" class="input_b"
					onclick="location='bbs_cont.do?bbs_no=${b.bbs_no}&page=${page}&state=reply'" />
					<input type="button" value="수정" class="input_b"
					onclick="location='bbs_cont.do?bbs_no=${b.bbs_no}&page=${page}&state=edit'" />
					<input type="button" value="삭제" class="input_b"
					onclick="location='bbs_cont.do?bbs_no=${b.bbs_no}&page=${page}&state=del'" />
					<input type="button" value="목록" class="input_b"
					onclick="location='bbs_list.do?page=${page}'" /></th>
			</tr>
		</table>
	</div>


</body>
</html>