<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 내용</title>
<link rel="stylesheet" type="text/css" href="./cs/bbs.css" />
<link rel="stylesheet" type="text/css" href="./cs/board.css" />
</head>
<body>
	<div id="bCont_wrap">
		<h2 class="BCont_title">게시판 내용</h2>
		<table id="BCont_t">
			<tr>
				<th>제목</th>
				<td>${b.board_title}</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${b.board_hit}</td>
			</tr>
		</table>
		<div id="BCont_menu">
			<input type="button" value="답변" class="input_b"
				onclick="location='board_cont.do?board_no=${b.board_no}&page=${page}&state=reply'" />
			<input type="button" value="삭제" class="input_b"
				onclick="location='board_cont.do?board_no=${b.board_no}&page=${page}&state=del'" />
			<input type="button" value="수정" class="input_b"
				onclick="location='board_cont.do?board_no=${b.board_no}&page=${page}&state=edit'" />

			<input type="button" value="목록" class="input_b"
				onclick="location='board_list.do?page=${page}'" />
		</div>
	</div>
</body>
</html>