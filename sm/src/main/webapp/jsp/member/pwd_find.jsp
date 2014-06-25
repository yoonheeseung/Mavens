<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비번찾기</title>
<link rel="stylesheet" type="text/css" href="./cs/member.css" />
<script src="./js/jquery.js"></script>
<script>
	function pwd_check() {
		if ($.trim($("#pwd_id").val()) == "") {
			alert("아이디를 입력하세요!");
			$("#pwd_id").val("").focus();
			return false;
		}
		if ($.trim($("#pwd_name").val()) == "") {
			alert("이름을 입력하세요!");
			$("#pwd_name").val("").focus();
			return false;
		}
	}
</script>
</head>
<body>
<c:if test="${empty dm }">
	<div id="pwd_wrap">
		<h2 class="pwd_title">비번찾기</h2>
		<form method="post" action="pwd_find_ok.do" onsubmit="return pwd_check();">
			<table id="pwd_t">
				<tr>
					<th>아이디</th>
					<td><input name="pwd_id" id="pwd_id" size="14" class="box" />
					</td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input name="pwd_name" id="pwd_name"
						size="14" class="box" /></td>
				</tr>
			</table>
  <div id="pwd_name">
  <input type="submit" value="찾기" class="input_b" />
  <input type="reset" value="취소" class="input_b" onclick="$('#pwd_id').focus();" />
  </div>
  </form>
</div>
</c:if>
	<c:if test="${!empty dm }">
		<div id="pwd_result">
			<h2 class="result_title">임시비번 결과</h2>
			<table id="result_t">
				<tr>
					<th>임시비번</th>
					<td>${random}</td>
				</tr>
				<tr>
					<th colspan="2"><input type="button" value="닫기"
						class="input_b" onclick="self.close();" /> 
				<%-- self.close()는 자바스크립트에서 내 자신 공지창을 닫아준다. --%>
					</th>
				</tr>
			</table>
		</div>
	</c:if>
</body>
</html>