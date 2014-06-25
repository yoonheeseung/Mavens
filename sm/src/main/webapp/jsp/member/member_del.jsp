<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>
<link rel="stylesheet" type="text/css" href="./cs/member.css" />
<link rel="stylesheet" type="text/css" href="./cs/bbs.css" />
<script src="./js/jquery.js"></script>
<script>
function del_check(){
	if($.trim($("#del_pwd").val())==""){
		alert("비번을 입력하세요!");
		$("#del_pwd").val("").focus();
		return false;
	}	
}
</script>
</head>
<body>
<div id="del_wrap">
	<h2 class="del_title">회원탈퇴</h2>
	<form method="post" action="member_del_ok.do" onsubmit="return del_check();">
	<table id="del_t">
	<tr>
	<th>회원아이디</th>
	<td>${id}</td>
	</tr>
	<tr>
	<th>비밀번호</th>
	<td>
	<input type="password" name="del_pwd" id="del_pwd" size="14" class="box" />
	</td>
	</tr>
	<tr>
	<th>탈퇴사유</th>
	<td>
	<textarea name="del_cont" id="del_cont" rows="8" cols="34" class="box"></textarea>
	</td>
	</tr>
	</table>
	<div id="del_menu">
	<input type="submit" value="탈퇴" class="input_b" />
	<input type="reset" value="취소" class="input_b" onclick="$('#del_pwd').focus();"/>
	</div>
	</form>
</div>
</body>
</html>