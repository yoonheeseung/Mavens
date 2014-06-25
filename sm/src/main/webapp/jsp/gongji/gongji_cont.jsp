<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 내용&목록</title>
<link rel="stylesheet" type="text/css" 
 href="./cs/gongji.css" />
 <link rel="stylesheet" type="text/css" 
 href="./cs/bbs.css" />
</head>
<body>
 <div id="gCont_wrap">
  <h2 class="gCont_title">공지내용</h2>
  <table id="gCont_t">
   <tr>
    <th>공지제목</th>
    <td>${g.gongji_title}</td>
   </tr>
   <tr>
    <th>공지내용</th>
    <td>
    ${gongji_cont}
    </td>
   </tr>
   <tr>
    <th>조회수</th> <td>${g.gongji_hit}</td>
   </tr>
   <tr>
   <th colspan="2">
   <input type="button" value="메인공지" class="input_b" 
    onclick="location='index_gongji.do'" />
    </th>
    </tr>
  </table>
 </div>
 
 <hr/> 
 <%--공지 목록 --%>
 <div id="bList_wrap">   
    <h2 class="bList_title">공지목록</h2>
    <table id="bList_t">
      <tr>
        <th width="6%" height="26" >번호</th> 
        <th width="50%" >제목</th> 
        <th width="14%" >작성자</th> 
        <th width="17%" >작성일</th> 
        <th width="14%" >조회수</th>
      </tr>
      
      <c:if test="${!empty blist}">
        <c:forEach var="list" items="${blist}">
          <tr>
            <td align="center">              
                ${list.gongji_no}               
            </td>            
            <td class="left_box" align="left">              
<a href=
"gongji_cont.do?gongji_no=${list.gongji_no}&page=${page}&state=cont"
onfocus="this.blur();">${list.gongji_title}</a>         
            </td>            
            <td align="center">${list.gongji_name}</td>
	  		<td align="center">${list.gongji_date}</td>	
	  		<td align="center">${list.gongji_hit}</td>
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
	      <!-- 페이징 시작--> 
	      	<c:if test = "${page <= 1 }">
			[이전]&nbsp;
			</c:if>
			<c:if test = "${page > 1 }">
			<a href="gongji_cont.do?page=${page-1}&state=list&gongji_no=${gongji_no}">[이전]</a>&nbsp;
			</c:if>				
			
			<c:forEach  var="a" begin = "${startpage}" end = "${endpage}" step = "1">
			 	<c:if test = "${a == page}">
			 	<%--해당 쪽번호가 선택된 경우 실행 --%>
					<${a}>
				</c:if>
				
                <c:if test = "${a != page}">
                <%--해당 쪽번호가 선택 안된 경우 실행 --%>
				 <a href="gongji_cont.do?page=${a}&state=list&gongji_no=${gongji_no}">[${a}]</a>&nbsp;
				</c:if>
			</c:forEach>				
			
			<c:if test = "${page >= maxpage}">
			 [다음]
			</c:if>
			<c:if test = "${page < maxpage }">
			 <a href="gongji_cont.do?page=${page+1}&state=list&gongji_no=${gongji_no}">[다음]</a>
			</c:if>	  
	    <!--  페이징(쪽나누기) 끝 -->
	</div>
  </div>
</body>
</html>













