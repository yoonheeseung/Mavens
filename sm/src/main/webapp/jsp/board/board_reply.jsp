<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 게시판 답변</title>
<link rel="stylesheet" type="text/css" href="./css/board.css" />
<link rel="stylesheet" type="text/css" href="./css/bbs.css" />
<script src="./js/jquery.js"></script>
<script src="./js/board.js"></script>
</head>
<body>
 <div id="BWrite_wrap">
  <h2 class="BWrite_title">게시판 답변</h2>
  <form method="post" action="board_reply_ok.do"
  onsubmit="return w_check();">
  <%--관리자 답변글 히든값 --%>
  <input type="hidden" name="board_ref" value="${b.board_ref}" />
  <input type="hidden" name="board_step" 
  value="${b.board_step}" />
  <input type="hidden" name="board_level"
  value="${b.board_level}" />
  
  <%--페이징 쪽번호 히든값 --%>
  <input type="hidden" name="page" value="${page}" />
  
  <table id="BWrite_t">
   <tr>
    <th>이름</th>
    <td>
    <input name="board_name" id="board_name" size="14"
    class="box" />
    </td>
   </tr>
   <tr>
    <th>제목</th>
    <td>
    <input name="board_title" id="board_title" size="38"
    class="box" value="Re: ${b.board_title}" />
    </td>
   </tr>
   <tr>
    <th>비밀번호</th>
    <td>
    <input type="password" name="board_pwd" id="board_pwd"
    size="14" class="box"/>
    </td>
   </tr>
   <tr>
    <th>내용</th>
    <td>
    <textarea name="board_cont" id="board_cont" rows="9"
    cols="37" class="box"></textarea>
    </td>
   </tr>
  </table>
  <div id="BWrite_menu">
   <input type="submit" value="답변" class="input_b" />
   <input type="reset" value="취소" class="input_b"
   onclick="$('#board_name').focus();" />
   <input type="button" value="목록" class="input_b"
   onclick="location='board_list.do?page=${page}'" />
  </div>
  </form>
 </div>
</body>
</html>