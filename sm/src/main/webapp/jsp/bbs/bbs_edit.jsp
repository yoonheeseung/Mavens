<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- html5 문서정의 --%>
<html>
<head>
<meta charset="UTF-8">
<%-- html5 언어코딩 타입 --%>
<title>자료실 수정</title>
<link rel="stylesheet" type="text/css" href="./cs/bbs.css" />
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/bbs.js"></script>
</head>
<body>
	<div id="bWrite_wrap">
		<h2 class="bWrite_title">자료실 수정</h2>
		<form method="post" action="bbs_edit_ok.do"
			onsubmit="return write_check();" enctype="multipart/form-data">
			<input type="hidden" name="bbs_no" value="${b.bbs_no}" />
			<input type="hidden" name="page" value="${page}" />			
			<!-- 바이너리파일(이진파일)은 반드시 POST방식 -->
			
			<table id="bWrite_t">
				<tr>
					<th>이름</th>
					<td>
					<input name="bbs_name" id="bbs_name" size="14" class="box" value="${b.bbs_name}"/>
						<%--type="text"를 생략하면 기본값으로 한줄 입력필드를 만든다. --%>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>
					<input name="bbs_title" id="bbs_title" size="36" class="box" value="${b.bbs_title}"/>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
					<input type="password" name="bbs_pwd" id="bbs_pwd"
						size="14" class="box" />
				    </td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
					 <textarea name="bbs_cont" id="bbs_cont" rows="9" cols="34" >${b.bbs_cont}</textarea>
					</td>
				</tr>
				<tr>
					<th>파일첨부</th>
					<td>
					<input type="file" name="bbs_file" />
					</td>
				</tr>
			</table>
			<div id="bWrite_menu">
				<input type="submit" value="수정" class="input_b" /> 
				<input type="reset" value="취소" class="input_b"
					 onclick="$('#bbs_name').focus();" />
			  <input type="button" value="목록" class="input_b" 
			         onclick="location='bbs_list.do?page=${page}'" />
			</div>
		</form>
	</div>
</body>
</html>