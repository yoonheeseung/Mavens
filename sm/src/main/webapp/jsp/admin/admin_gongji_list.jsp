<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/admin_header.jsp" %>

<!-- 관리자 메인내용 -->
  <div id="aMain_cont">
  <form action="admin_gongji_list.do" onsubmit="return find_check();">
<%-- method속성을 생략하면 기본으로 get방식 --%>
  <div id="abList_wrap">   
    <h2 class="abList_title">관리자 공지 목록</h2>
    <div id="bList_count">글개수:${listcount}</div>
    <table id="bList_t">
      <tr>
        <th width="6%" height="26" >번호</th> 
        <th width="40%" >제목</th> 
        <th width="14%" >작성자</th> 
        <th width="17%" >작성일</th> 
        <th width="24%" >수정/삭제</th>
      </tr>
      
      <c:if test="${!empty blist}">
        <c:forEach var="list" items="${blist}">
          <tr>
            <td align="center">
              ${list.gongji_no}
            </td>
            <td class="left_box" align="left">           
 <a href="admin_gongji_cont.do?gongji_no=${list.gongji_no}&page=${page}&state=cont" 
              onfocus="this.blur();">${list.gongji_title}</a>        
 <%-- *.do?no=번호값&page=쪽번호&state=cont get방식으로 3개의
 피라미터값을 웹주소창에 노출해서 넘김. 
 get방식으로 넘기때 주의할점.
  1.한글자료를 넘기면 안된다.
  2.중복되지 않는 자료를 넘김.
  onfocus="this.blur();" 는 하이퍼링크 된곳 클릭시 사각점선이 
  생긴다. 이 사각점선을 사라지게 하는 부분.
 --%>
            </td>
            
            <td align="center">${list.gongji_name}</td>
	  		<td align="center">${list.gongji_date}</td>	
	  		<td align="center">
<input type="button" value="수정" class="input_b"
onclick="location=
'admin_gongji_cont.do?gongji_no=${list.gongji_no}&page=${page}&state=edit'"/>
<input type="button" value="삭제" class="input_b"
onclick="if(confirm('정말로 삭제할까요?')==true){
location=
'admin_gongji_cont.do?gongji_no=${list.gongji_no}&page=${page}&state=del';	
}else{ return;}" />
<%-- 자바스크립트에서 confirm()메서드는 확인/취소 버튼을 가진
경고창을 만든다. 확인을 클릭하면 true를 반환,취소를 클릭하면
false를 반환한다. --%>	  		
	  		</td>
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
			<a href="admin_gongji_list.do?page=${page-1}&find_field=${find_field}&find_name=${find_name}">[이전]</a>&nbsp;
			</c:if>	
			
			
			<c:forEach  var="a" begin = "${startpage}" end = "${endpage}" step = "1">
			 	<c:if test = "${a == page}">
					<${a}>
				</c:if>
				
                <c:if test = "${a != page}">
				 <a href="admin_gongji_list.do?page=${a}&find_field=${find_field}&find_name=${find_name}">[${a}]</a>&nbsp;
				</c:if>
			</c:forEach>	
			
			
			<c:if test = "${page >= maxpage}">
			 [다음]
			</c:if>
			<c:if test = "${page < maxpage }">
			 <a href="admin_gongji_list.do?page=${page+1}&find_field=${find_field}&find_name=${find_name}">[다음]</a>
			</c:if>
	      </c:if>
	  <!-- 검색후 페이징 끝 -->
	  	      
	      <!-- 검색전 페이징 -->
    <c:if test="${(empty find_field) && (empty find_name)}">
	      	<c:if test = "${page <= 1 }">
			[이전]&nbsp;
			</c:if>
			<c:if test = "${page > 1 }">
			<a href="admin_gongji_list.do?page=${page-1}">[이전]</a>&nbsp;
			</c:if>	
			
			
			<c:forEach  var="a" begin = "${startpage}" end = "${endpage}" step = "1">
			 	<c:if test = "${a == page}">
			 	<%--해당 쪽번호가 선택된 경우 실행 --%>
					<${a}>
				</c:if>
				
                <c:if test = "${a != page}">
                <%--해당 쪽번호가 선택 안된 경우 실행 --%>
				 <a href="admin_gongji_list.do?page=${a}">[${a}]</a>&nbsp;
				</c:if>
			</c:forEach>	
			
			
			<c:if test = "${page >= maxpage}">
			 [다음]
			</c:if>
			<c:if test = "${page < maxpage }">
			 <a href="admin_gongji_list.do?page=${page+1}">[다음]</a>
			</c:if>
	    </c:if>
	    <!-- 검색전 페이징(쪽나누기) 끝 -->
	</div>
	
	
	<div id="list_menu">
	   	<input type="button" value="글쓰기" class="input_b"
		onclick="location='admin_gongji_write.do?page=${page}'" />	
		<%-- 검색전 전체 레코드를 검색해서 전체 레코드를 페이징
		할 목록으로 이동할 버튼을 만든다.이럴경우는 get방식으로
		find_name과 find_field를 넘기면 안된다. --%>
     <c:if test="${(!empty find_field) && (!empty find_name)}">		
		<input type="button" value="전체목록"
		onclick="location='admin_gongji_list.do?page=${page}'"
		class="input_b" />	
	</c:if>
   </div>    
 
   <!-- 검색폼 추가 -->
   <div id="list_find">
    <table id="list_f">
     <tr>
      <th>
      <select name="find_field">
       <option value="board_title"
       <c:if test="${find_field=='gongji_title'}">
       ${'selected'}</c:if>>제목</option>
       
       <option value="board_cont"
       <c:if test="${find_field=='gongji_cont'}">
       ${'selected'}</c:if>>내용</option>
       <%-- board_title,board_cont는 테이블 필드명. --%>
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

<%@ include file="../../include/admin_footer.jsp" %>