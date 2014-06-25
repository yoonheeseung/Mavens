<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<link rel="stylesheet" type="text/css" href="./cs/member.css" />
<link rel="stylesheet" type="text/css" href="./cs/bbs.css" />
<script src="./js/jquery.js"></script>
<script src="./js/member.js"></script>
</head>
<body>
	<div id="join_wrap">
		<h2 class="join_title">회원가입</h2>
		<form name="m" method="post" action="member_edit_ok.do"
			onsubmit="return edit_check();">
			<table id="join_t">
				<tr>
					<th>아이디</th>
					<td>${id}</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="join_pwd" id="join_pwd"
						size="14" class="box" /></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" name="join_pwd2" id="join_pwd2"
						size="14" class="box"  /></td>
				</tr>
				<tr>
					<th>회원이름</th>
					<td><input name="join_name" id="join_name" size="14"
						class="box"  value="${em.join_name}"/></td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td>
					<input name="join_zip" id="join_zip" size="3" maxlength="3" onclick="post_search();" value="${em.join_zip}"/>-
					<input name="join_zip2"id="join_zip2" size="3" maxlength="3" class="box" onclick="post_search();" value="${em.join_zip2}" /> 
					<input type="button" value="우편검색"class="input_b" onclick="post_check();" value="${em.join_addr}"/></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input name="join_addr" id="join_addr" size="40"
						class="box" /></td>
				</tr>
				<tr>
					<th>나머지 주소</th>
					<td><input name="join_addr2" id="join_addr2" size="36"
						class="box" /></td>
				</tr>
				<tr>
					<th>휴대폰</th>
					<td><select name="join_phone01">
							<c:forEach var="p" items="${phone}">
								<option value="${p}"
								 <c:if test="${em.join_phone01 ==p}">
								 ${'selected'}</c:if>>${p}</option>
							</c:forEach>
							
					</select>-<input name="join_phone02" id="join_phone02" siz4e="4"
						maxlength="4" class="box" value="${em.join_phone02}"/>- <input name="join_phone03"
						id="join_phone03" size="4" maxlength="4" class="box" value="${em.join_phone03}" /></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input name="mail_id" id="mail_id" size="14" class="box" value="${em.mail_id}"/>@
					    <input name="mail_domain" id="mail_domain" size="12" class="box" value="${em.mail_domain}" readonly/>
						<%-- 입력박스에 readonly 속성을 지정하면 읽기만 가능 --%>
						 <select name="mail_list" onchange="domain_list();">
							<c:forEach var="mail" items="${email}">
	    <option value="${mail}"<c:if test="${em.mail_domain == mail}">${'selected'}</c:if>>${mail}</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>
			<div id="join_menu">
				<input type="submit" value="수정" class="input_b" /> <input
					type="reset" value="취소" class="input_b"
					onclick="$('#join_pwd').focus();" />
			</div>
		</form>
	</div>
</body>
</html>