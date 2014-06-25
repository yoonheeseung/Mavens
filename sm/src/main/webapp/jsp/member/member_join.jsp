<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/mjoin.js"></script>
<link rel="stylesheet" style="text/css" href="./css/mjoin.css">

<title>쇼핑몰</title>
</head>
<body>
<table id="join_t">
	<tr>
	<td colspan=2>
	<!-- 회원가입 -->
	<form id="joinform" name="joinform" action="./memberJoinOk.me" method="post" onsubmit="return check()">		
	<p align="center">	
	<table id="form_t">
	<tr>
		<td width="17%" bgcolor="#f5f5f5">
			<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;이름</font>
		</td>
		<td>
			&nbsp;&nbsp;&nbsp;
			<input type="text" name="MEMBER_NAME" size="20"/>
		</td>
	</tr>
	<tr>
		<td bgcolor="#f5f5f5">
			<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;아이디</font>
		</td>
		<td>
			&nbsp;&nbsp;&nbsp;
			<input type="text" name="MEMBER_ID" size="10" maxlength=15/>
			<input type="button" name="confirm_id" value="중복확인" 
				onclick="openConfirmId(this.form)" />
		</td>
	</tr>
	<tr>
		<td bgcolor="#f5f5f5">
			<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;비밀번호</font>
		</td>
		<td>
			&nbsp;&nbsp;&nbsp;
			<input type="password" name="MEMBER_PW" size="15"/>
		</td>
	</tr>
	<tr>
		<td bgcolor="#f5f5f5">
			<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;비밀번호 확인</font>
		</td>
		<td>
			&nbsp;&nbsp;&nbsp;
			<input type="password" name="MEMBER_PW2" size="15" />
		</td>
	</tr>
	<tr>
		<td bgcolor="#f5f5f5">&nbsp;</td>
		<td>
		<font size="2">&nbsp;&nbsp;&nbsp;
		(아이디와 비밀번호는 문자와 숫자를 조합하여 2~12자리로 만들어 주세요)</font>
		</td>
	</tr>
	<tr>
		<td bgcolor="#f5f5f5">
			<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;주민등록번호</font>
		</td>
		<td>
			&nbsp;&nbsp;&nbsp;
			<input type="text" name="MEMBER_JUMIN1" size="12" 
				onkeypress="gNumCheck()" maxlength="6"/>-	
			<input type="text" name="MEMBER_JUMIN2" size="12" 
				onkeypress="gNumCheck()" maxlength="7"/>
		</td>
	</tr>
	<tr>
		<td bgcolor="#f5f5f5">
			<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;이메일 주소</font>
		</td>
		<td>
			&nbsp;&nbsp;&nbsp;
			<input type="text" name="MEMBER_EMAIL1" size="13"/>@
			<input type="text" name="MEMBER_EMAIL2" size="15"/> 
		</td>
		</tr>
		<tr>
		<td bgcolor="#f5f5f5">
		<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;메일 수신 여부</font>
		</td>
		<td>
		&nbsp;&nbsp;&nbsp;
		<input type="radio" name="MEMBER_EMAIL_GET" value="YES" checked/>
		<font size="2">수신</font>
		&nbsp;&nbsp;<input type="radio" name="MEMBER_EMAIL_GET" value="NO"/>
		<font size="2">수신 안함</font>
		</td>
		</tr>
		<tr>
			<td bgcolor="#f5f5f5">
				<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;집전화</font>
			</td>
			<td>
				&nbsp;&nbsp;&nbsp;
				<input type="text" name="MEMBER_PHONE" size="24"/>
			</td>
		</tr>
		<tr>
			<td bgcolor="#f5f5f5">
				<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;우편번호</font>
			</td>
			<td>
			&nbsp;&nbsp;&nbsp;
			<input type="text" name="MEMBER_ZIPCODE1" size="6" 
				onkeypress="gNumCheck()" maxlength="3"/>- 
			<input type="text" name="MEMBER_ZIPCODE2" size="6" 
				onkeypress="gNumCheck()" maxlength="3" />&nbsp;&nbsp;
			<input type="button" name="zipcode" value="우편번호 검색" 
				onclick="openZipcode(this.form)" />
			</td>
		</tr>
		<tr>
			<td bgcolor="#f5f5f5">
				<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;집주소</font>
			</td>
			<td>
				&nbsp;&nbsp;&nbsp;
				<input type="text" name="MEMBER_ADDR1" size="50" />
			</td>
		</tr>
		<tr>
			<td bgcolor="#f5f5f5">
				<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;상세주소</font>
			</td>
			<td>
				&nbsp;&nbsp;&nbsp;
				<input type="text" name="MEMBER_ADDR2" size="50" />
			</td>
		</tr>
		<tr>
			<td bgcolor="#f5f5f5">
				<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;휴대폰</font>
			</td>
			<td>
				&nbsp;&nbsp;&nbsp;
				<input type="text" name="MEMBER_MOBILE" size="24" />
				</td>
			</tr>
		</table>
		<table width="80%">
			<tr>
				<td align="center">
					<br/><input type="submit" value="확 인" />
					     <input type="button" value="뒤로" onclick="history.go(-1)">
				</td>
			</tr>
		</table>
		</form>
		<!-- 회원가입 -->	
		</td>
	</tr>
</table>
</body>
</html>