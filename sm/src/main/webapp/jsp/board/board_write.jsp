<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>사용자 게시판 글입력</title>
<link rel="stylesheet" type="text/css" href="./cs/board.css" />
<script src="./js/jquery.js"></script>
<script src="./js/board.js"></script>
</head>
<body>
	<div id="BWrite_wrap">
		<h2 class="BWrite_title">게시판 글입력</h2>
		<form method="post" action="board_write_ok.do"
			onsubmit="return w_check();">
			<table id="BWrite_t">
				<tr>
					<th>이름</th>
					<td><input name="board_name" id="board_name" size="14" class="box" /></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input name="board_title" id="board_title" size="38" class="box" /></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="board_pwd" id="board_pwd" size="14" class="box" /></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea cols="37" rows="9" name="board_cont" id="board_cont" class="box"></textarea></td>
				</tr>
			</table>
			<div id="BWrite_menu">
				<input type="submit" value="저장" class="input_b" /> 
				<input type="reset" value="취소" class="input_b" onclick="$('#board_name').focus();" />
			</div>
		</form>
	</div>
</body>
</html>