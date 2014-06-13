<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/admin_header.jsp" %>

<!-- 관리자 메인내용 -->
<div id="aMain_cont">
<div id="abWrite_wrap">
		<h2 class="abWrite_title">관리자 자료실 글쓰기</h2>
		<form method="post" action="admin_bbs_write_ok.do"
			onsubmit="return write_check();" enctype="multipart/form-data">
			<%--
     그림,동영상 같은 이진파일을 서버에 첨부해서 올리는 자료실
      만들 때 주의사항
      1.반드시 form테그내에 enctype="multipart/fomr-data"속성을 지정해야함
      2.이진파일(바이너리파일)을 서버로 보낼려면 반드시 method="post"방식으로
         넘겨야한다.
      3.자바 웹개발에서 이진파일을 첨부해서 서버로 보낼려면 외부라이브러리
        파일이 필요하다. jsp등 웹개발에서 광범위하게 사용되는 대표적인
         라이브러리 파일 cos.jar를 사용해 본다. cos.jar는 바로 이진파일을 
        서버에서 받을 수 있는 라이브러리.
        cos.jar말고 다른 이진 업로드 라이브러리파일이 다수 존재함. 
   --%>
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
					<input name="bbs_title" id="bbs_title" size="36" class="box" />
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
				<tr>
					<th>파일첨부</th>
					<td>
					<input type="file" name="bbs_file" />
					</td>
				</tr>
			</table>
			<div id="bWrite_menu">
				<input type="submit" value="저장" class="input_b" /> 
				<input type="reset" value="취소" class="input_b"
					 onclick="$('#bbs_name').focus();" />
			  <input type="button" value="목록" class="input_b" 
			         onclick="location='admin_bbs_list.do'" />
			</div>
		</form>
	</div>

</div>