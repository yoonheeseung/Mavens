<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/admin_header.jsp" %>

  <!-- 관리자 메인내용 -->
  <div id="aMain_cont">    
    <div id="aBbs_cont">
     <h2 class="aBbscont_title">관리자 게시판 내용</h2>
     <table id="aBbscont_t">
      <tr>
       <th>제목</th>
       <td>${b.board_title}</td>
      </tr>
      <tr>
       <th>내용</th>
       <td>${board_cont}</td>
      </tr>
      <tr>
       <th>조회수</th>
       <td>
        ${b.board_hit}
       </td>
      </tr>
     
      <tr>
       <th colspan="2">
       <input type="button" value="목록" class="input_b"
onclick="location='admin_board_list.do?page=${page}'" />      
       </th>
      </tr>
     </table>
    </div>
  </div>

  <%@ include file="../../include/admin_footer.jsp" %>