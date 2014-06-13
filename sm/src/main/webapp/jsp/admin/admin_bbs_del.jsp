<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../../include/admin_header.jsp" />

  <!-- 관리자 메인내용 -->
  <div id="aMain_cont">
  <div id="bDel_wrap">
  <h2 class="bDel_title">자료실 삭제</h2>
  <form method="post" action="admin_bbs_del_ok.do"
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
    onclick="$('#del_pwd').focus();" />
    </th>
   </tr>
  </table>
  </form>
 </div>
  </div>
  
<jsp:include page="../../include/admin_footer.jsp" />