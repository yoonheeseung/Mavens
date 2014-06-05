<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- html5 문서정의 --%>
<html>
<head>
<meta charset="UTF-8">
<%-- html5 언어코딩 타입 --%>
<title>자료실 답변</title>
<link rel="stylesheet" type="text/css" href="./cs/bbs.css" />
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/bbs.js"></script>
</head>
<body>
	<div id="bWrite_wrap">
		<h2 class="bWrite_title">자료실 답변</h2>
		<form method="post" action="bbs_reply_ok.do"
			onsubmit="return write_check();">
	<%--답변글 히든 값  --%>
	    <input type="hidden" name="bbs_ref" value="${b.bbs_ref}" /><%--글 그룹번호 --%>
	    <input type="hidden" name="bbs_step" value="${b.bbs_step}" />
	    <input type="hidden" name="bbs_level" value="${b.bbs_level}" />
   <%--페이징 --%>
        <input type="hidden" name="page" value="${page}" />
        
			<table id="bWrite_t">
				<tr>
					<th>이름</th>
					<td>
					<input name="bbs_name" id="bbs_name" size="14" class="box" />
						<%--type="text"를 생략하면 기본값으로 한줄 입력필드를 만든다. --%>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>
					<input name="bbs_title" id="bbs_title" size="36" class="box" value="Re: ${b.bbs_title}"/>
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
					 <textarea name="bbs_cont" id="bbs_cont" rows="9" cols="34"></textarea>
					</td>
				</tr>
			</table>
			<div id="bWrite_menu">
				<input type="submit" value="답변" class="input_b" /> 
				<input type="reset" value="취소" class="input_b"
					 onclick="$('#bbs_name').focus();" />
			  <input type="button" value="목록" class="input_b" 
			         onclick="location='bbs_list.do'" />
			</div>
		</form>
	</div>
</body>
</html>