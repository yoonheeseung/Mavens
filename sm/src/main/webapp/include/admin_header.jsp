<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<metacharset="UTF-8">
<title>관리자 메인화면</title>
<link rel="stylesheet" type="text/css" href="./cs/admin.css" />
<link rel="stylesheet" type="text/css" href="./cs/bbs.css" />
<link rel="stylesheet" type="text/css" href="./cs/admin_bbs.css" />
<link rel="stylesheet" type="text/css" href="./cs/board.css" />
<script src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/bbs.js"></script>
<script src="./js/board.js"></script>
<script>
 function find_check(){
	 if($.trim($("#find_name").val())==""){
		 alert("검색어를 입력하세요!");
		 $("#find_name").val("").focus();
		 return false;
	 }
 }
</script>
</head>
<body>
<!-- 관리자 메인 ui -->
<div id="aMain_wrap">
<!-- 관리자 상단 -->
<div id="aMain_header">
<!-- 회사 로고 -->
<div id="aMain_logo">
<a href="admin_main.do" onfocus="this.blur();">
<img src="./images/admin/admin_logo.gif" />
</a>
</div>
<!-- 상단 메뉴 -->
<div id="aMain_menu">
<ul>
<li><a href="admin_bbs_list.do" onfocus="this.blur();">자료실</a>
</li>
<li><a href="admin_board_list.do" onfocus="this.blur();">게시판</a>
</li>
<li><a href="admin_member_list.do" onfocus="this.blur();">회원관리</a>
</li>
<li><a href="admin_gongji_list.do" onfocus="this.blur();">공지사항</a>
</li>
</ul>
</div>
<!-- 관리자 상단 우측메뉴 -->
<div id="aMain_right">
<form method="post" action="admin_logout.do">
<h3 class="aMain_title">
 ${admin_name}님 로그인을 환영합니다!
 <input type="submit" value="로그아웃" class="input_b" />
</h3>
</form>
</div>
</div>

<div class="clear"></div>