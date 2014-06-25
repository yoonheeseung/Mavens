<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/admin_header.jsp" %>

  <!-- 관리자 메인내용 -->
  <div id="aMain_cont">
  <form action="admin_member_list.do" onsubmit="return find_check();">
<%-- method속성을 생략하면 기본으로 get방식 --%>
  <div id="abList_wrap">   
    <h2 class="abList_title">관리자 회원 목록</h2>
    <div id="bList_count">회원수:${listcount}</div>
    <table id="bList_t">
      <tr>
        <th width="20%" height="26" >아이디</th> 
        <th width="16%" >회원이름</th> 
        <th width="20%" >폰번호</th> 
        <th width="21%" >가입날짜</th> 
        <th width="24%" >수정/삭제</th>
      </tr>
      
      <c:if test="${!empty blist}">
        <c:forEach var="list" items="${blist}">
          <tr>
            <th>
              ${list.join_id}
            </th>            
            <th>
<a href="admin_member_info.do?id=${list.join_id}&page=${page}&state=info" 
              onfocus="this.blur();">${list.join_name}</a>  
            </th>            
            <th>${list.join_phone01}-${list.join_phone02}-
            ${list.join_phone03}</th>
	  		<th>${list.join_date}</th>	
	  		<th>
<input type="button" value="수정" class="input_b"
onclick="location=
'admin_member_info.do?id=${list.join_id}&page=${page}&state=edit'"/>
<input type="button" value="삭제" class="input_b"
onclick="if(confirm('정말로 삭제할까요?')==true){
location=
'admin_member_del.do?id=${list.join_id}&page=${page}';	
}else{ return;}" />
<%-- 자바스크립트에서 confirm()메서드는 확인/취소 버튼을 가진
경고창을 만든다. 확인을 클릭하면 true를 반환,취소를 클릭하면
false를 반환한다. --%>	  		
	  		</th>
          </tr>
        </c:forEach>
      </c:if>
      
      <c:if test="${empty blist}">
        <tr>
          <th colspan="5">목록이 없습니다!!</th>
        </tr>
      </c:if>
    </table>
    
    <!-- 페이징 추가 -->
	<div id="list_paging">
	  <!-- 검색후 페이징 -->
	  <c:if test="${(!empty find_field) && (!empty find_name)}">
			<c:if test = "${page <= 1 }">
			[이전]&nbsp;
			</c:if>
			<c:if test = "${page > 1 }">
			<a href="admin_member_list.do?page=${page-1}&find_field=${find_field}&find_name=${find_name}">[이전]</a>&nbsp;
			</c:if>	
			
			
			<c:forEach  var="a" begin = "${startpage}" end = "${endpage}" step = "1">
			 	<c:if test = "${a == page}">
					<${a}>
				</c:if>
				
                <c:if test = "${a != page}">
				 <a href="admin_member_list.do?page=${a}&find_field=${find_field}&find_name=${find_name}">[${a}]</a>&nbsp;
				</c:if>
			</c:forEach>	
			
			
			<c:if test = "${page >= maxpage}">
			 [다음]
			</c:if>
			<c:if test = "${page < maxpage }">
			 <a href="admin_member_list.do?page=${page+1}&find_field=${find_field}&find_name=${find_name}">[다음]</a>
			</c:if>
	      </c:if>
	  <!-- 검색후 페이징 끝 -->
	  	      
	      <!-- 검색전 페이징 -->
    <c:if test="${(empty find_field) && (empty find_name)}">
	      	<c:if test = "${page <= 1 }">
			[이전]&nbsp;
			</c:if>
			<c:if test = "${page > 1 }">
			<a href="admin_member_list.do?page=${page-1}">[이전]</a>&nbsp;
			</c:if>	
			
			
			<c:forEach  var="a" begin = "${startpage}" end = "${endpage}" step = "1">
			 	<c:if test = "${a == page}">
			 	<%--해당 쪽번호가 선택된 경우 실행 --%>
					<${a}>
				</c:if>
				
                <c:if test = "${a != page}">
                <%--해당 쪽번호가 선택 안된 경우 실행 --%>
				 <a href="admin_member_list.do?page=${a}">[${a}]</a>&nbsp;
				</c:if>
			</c:forEach>	
			
			
			<c:if test = "${page >= maxpage}">
			 [다음]
			</c:if>
			<c:if test = "${page < maxpage }">
			 <a href="admin_member_list.do?page=${page+1}">[다음]</a>
			</c:if>
	    </c:if>
	    <!-- 검색전 페이징(쪽나누기) 끝 -->
	</div>
	
	
	<div id="list_menu">
     <c:if test="${(!empty find_field) && (!empty find_name)}">		
		<input type="button" value="전체목록"
		onclick="location='admin_member_list.do?page=${page}'"
		class="input_b" />	
	</c:if>
   </div>    
 
   <!-- 검색폼 추가 -->
   <div id="list_find">
    <table id="list_f">
     <tr>
      <th>
      <select name="find_field">
       <option value="join_id"
       <c:if test="${find_field=='join_id'}">
       ${'selected'}</c:if>>아이디</option>
       
       <option value="join_name"
       <c:if test="${find_field=='join_name'}">
       ${'selected'}</c:if>>회원이름</option>       
      </select>
      <input type="text" name="find_name" id="find_name"
      size="16"  value="${find_name}" class="box" />
      <input type="submit" value="검색" class="input_b"/>
      </th>
     </tr>
    </table>
   </div>
   <!-- 검색폼 추가 끝 -->
   
  </div>
  </form>
  </div>

<jsp:include page="../../include/admin_footer.jsp" />