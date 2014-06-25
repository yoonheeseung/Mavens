<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/admin_header.jsp" %>

  <!-- 관리자 메인내용 -->
  <div id="aMain_cont">        
    <div id="BWrite_wrap">
  <h2 class="BWrite_title">관리자공지수정</h2>
  <form method="post" action="admin_gongji_edit_ok.do"
  onsubmit="return gongji_check();">
  <input type="hidden" name="gongji_no" value="${g.gongji_no}" />
  <input type="hidden" name="page" value="${page}" />
  <table id="BWrite_t">
   <tr>
    <th>이름</th>
    <td>
    <input name="gongji_name" id="gongji_name" size="14"
    class="box" value="${g.gongji_name}" />
    </td>
   </tr>
   <tr>
    <th>제목</th>
    <td>
    <input name="gongji_title" id="gongji_title" size="38"
    class="box" value="${g.gongji_title}" />
    </td>
   </tr>
   <tr>
    <th>비밀번호</th>
    <td>
    <input type="password" name="gongji_pwd" id="gongji_pwd"
    size="14" class="box"/>
    </td>
   </tr>
   <tr>
    <th>내용</th>
    <td>
    <textarea name="gongji_cont" id="gongji_cont" rows="9"
    cols="37" class="box">${g.gongji_cont}</textarea>
    </td>
   </tr>
  </table>
  <div id="BWrite_menu">
   <input type="submit" value="수정" class="input_b" />
   <input type="reset" value="취소" class="input_b"
   onclick="$('#gongji_name').focus();" />
   <input type="button" value="목록" class="input_b"
   onclick="location='admin_gongji_list.do?page=${page}'" />
  </div>
  </form>
 </div>
  </div>
  
 <%@ include file="../../include/admin_footer.jsp" %>