<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/admin_header.jsp" %>

   <!-- 관리자 메인내용 -->
  <div id="aMain_cont">
    <div id="aMem_wrap">
     <h2 class="aMem_title">관리자 회원정보</h2>
     <table id="aMem_t">
      <tr>
       <th>회원아이디</th>
       <td>${m.join_id}</td>
      </tr>
      <tr>
       <th>회원이름</th>
       <td>${m.join_name}</td>
      </tr>
      <tr>
       <th>주소</th>
       <td>
        ${m.join_zip}-${m.join_zip2}<br/>
        ${m.join_addr}&nbsp;${m.join_addr2}
       </td>
      </tr>
      <tr>
       <th>폰번호</th>
       <td>
       ${m.join_phone01}-${m.join_phone02}-${m.join_phone03}
       </td>
      </tr>
      <tr>
       <th>이메일</th>
       <td>${m.mail_id}@${m.mail_domain}</td>
      </tr>      
      <tr>
       <th>가입날짜</th>
       <td>${m.join_date}</td>
      </tr>
      <tr>
       <th>가입/탈퇴회원</th>
       <td>
       <c:if test="${m.join_state ==1}">
        가입회원
       </c:if>
       <c:if test="${m.join_state ==2}">
       탈퇴회원
       </c:if>
       </td>
      </tr>
      <c:if test="${!empty join_delcont}">
       <tr>
         <th>탈퇴사유</th>
         <td>${join_delcont}</td>
       </tr>
      </c:if>
      <c:if test="${!empty m.join_deldate}">
       <tr>
        <th>탈퇴날짜</th>
        <td>${m.join_deldate}</td>
       </tr>
      </c:if>
     </table>
     <div id="aMem_menu">
     <input type="button" value="회원목록" class="input_b"
     onclick="location='admin_member_list.do?page=${page}'" />
     </div>
    </div>
  </div>
  
<jsp:include page="../../include/admin_footer.jsp" />