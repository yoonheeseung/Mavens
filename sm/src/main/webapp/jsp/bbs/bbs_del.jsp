<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- html5 문서정의 --%>
<html>
<head>
<meta charset="UTF-8">
<%-- html5 언어코딩 타입 --%>
<title>자료실 삭제</title>
<link rel="stylesheet" type="text/css" href="./cs/bbs.css" />
<script type="text/javascript" src="./js/jquery.js"></script>
<script>
function del_check(){
	if($.trim($("#del_pwd").val())==""){
		alert("삭제 비번을 입력하세요!");
		$("#del_pwd").val("").focus();
		return false;
	}
}

</script>
</head>
<body>
	<div id="bDel_wrap">
		<h2 class="bDel_title">자료실 삭제</h2>
		<form method="post" action="bbs_del_ok.do" 
		    onsubmit="return del_check();">
			<input type="hidden" name="bbs_no" value="${b.bbs_no}" />
			<input type="hidden" name="page" value="${page}" />
			<table id="bDel_t">
				<tr>
					<th>비밀번호</th>
				<td>
					<input type="password" name="del_pwd" id="del_pwd"
						size="14" class="box" />						
				</td>   
				</tr>
				<tr>
				<th colspan="2">
				<input type="submit" value="삭제" class="input_b" /> 
				<input type="reset" value="취소" class="input_b"
					 onclick="$('#bbs_pwd').focus();" />
			     <input type="button" value="목록" class="input_b" 
			         onclick="location='bbs_list.do?page=${page}'" />
			    </th>
			    </tr>
			</table>
		</form>
	</div>
</body>
</html>