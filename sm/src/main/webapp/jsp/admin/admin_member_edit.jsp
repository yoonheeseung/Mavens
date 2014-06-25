<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/admin_header.jsp"%>

<!-- 관리자 메인내용 -->
<div id="aMain_cont">
	<div id="join_wrap">
		<h2 class="join_title">정보수정</h2>
		<form name="m" method="post" action="admin_member_edit_ok.do"
			onsubmit="return edit_check();">
		<input type="hidden" name="join_id" value="${m.join_id}" />
		<input type="hidden" name="page" value="${page}" />
			
			<table id="join_t">
				<tr>
					<th>아이디</th>
					<td>${m.join_id}</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="join_pwd" id="join_pwd"
						size="14" class="box" /></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" name="join_pwd2" id="join_pwd2"
						size="14" class="box" /></td>
				</tr>
				<tr>
					<th>회원이름</th>
					<td><input name="join_name" id="join_name" size="14"
						class="box" value="${m.join_name}" /></td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td><input name="join_zip" id="join_zip" size="3"
						maxlength="3" class="box" onclick="post_search();"
						value="${m.join_zip}" />- <input name="join_zip2" id="join_zip2"
						size="3" maxlength="3" class="box" onclick="post_search();"
						value="${m.join_zip2}" /> <input type="button" value="우편검색"
						class="input_b" onclick="post_check();"  />
					</td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input name="join_addr" id="join_addr" size="40"
						class="box" onclick="post_search();" value="${m.join_addr}" /></td>
				</tr>
				<tr>
					<th>나머지 주소</th>
					<td><input name="join_addr2" id="join_addr2" size="36"
						class="box" value="${m.join_addr2}" /></td>
				</tr>
				<tr>
					<th>휴대폰</th>
					<td><select name="join_phone01">
							<c:forEach var="p" items="${phone}">
								<option value="${p}"
									<c:if test="${m.join_phone01 == p}">
       ${'selected'}</c:if>>${p}</option>
							</c:forEach>
					</select>-<input name="join_phone02" id="join_phone02" size="4"
						maxlength="4" class="box" value="${m.join_phone02}" />-<input
						name="join_phone03" id="join_phone03" size="4" maxlength="4"
						class="box" value="${m.join_phone03}" /></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input name="mail_id" id="mail_id" size="14" class="box"
						value="${m.mail_id}" />@<input name="mail_domain"
						id="mail_domain" size="12" class="box" readonly
						value="${m.mail_domain}" /> <%--입력박스에 readonly 속성을 지정하면 읽기만 가능 --%>
						<select name="mail_list" onchange="domain_list();">
							<c:forEach var="mail" items="${email}">
								<option value="${mail}"
									<c:if test="${m.mail_domain == mail}">
      ${'selected'}</c:if>>${mail}</option>
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
</div>

<jsp:include page="../../include/admin_footer.jsp" />
