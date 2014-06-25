<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인화면</title>
<link rel="stylesheet" type="text/css" href="./cs/member.css" />
<link rel="stylesheet" type="text/css" href="./cs/bbs.css" />
</head>
<body>
	<div id="index_wrap">
		<form method="post" action="member_logout.do">
			<table id="index_t">
				<tr>
					<th><input type="button" value="정보수정" class="input_b"
						onclick="location='member_edit.do'" /> <input type="button"
						value="회원탈퇴" class="input_b" onclick="location='member_del.do'" />
						<input type="submit" value="로그아웃" class="input_b" /></th>
					</tr>
					<tr>
					<th>${id}님 로그인을 환영합니다!</th>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>