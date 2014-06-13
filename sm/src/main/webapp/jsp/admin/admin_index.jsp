<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인</title>
<link rel="stylesheet" type="text/html" href="./cs/admin.css" />
<script src="./js/jquery.js"></script>
<script>
	function admin_check() {
		if ($.trim($("#admin_id").val()) == "") {
			alert("관리자 아이디를 입력하세요!");
			$("#admin_id").val("").focus();
			return false;
		}
		if ($.trim($("#admin_pwd").val()) == "") {
			alert("관리자 비번을 입력하세요!");
			$("#admin_pwd").val("").focus();
			return false;
		}
	}
</script>
</head>
<body>
	<div id="admin_wrap">
		<h2 class="admin_title">관리자 로그인</h2>
		<form method="post" action="admin_ok.do"
			onsubmit="return admin_check();">
			<table id="admin_t">
				<tr>
					<th>관리자 아이디</th>
					<td><input name="admin_id" id="admin_id" size="14" class="box"
						tabindex="1" />
					</td>
					<th rowspan="2">
					<input type="submit" value="로그인"
						class="input_s" />
					</th>
				</tr>
				<tr>
					<th>관리자 비밀번호</th>
					<td><input type="password" name="admin_pwd" id="admin_pwd"
						size="14" class="box" tabindex="2" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>