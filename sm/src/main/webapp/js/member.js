/**
 * member.js
 */

function join_check(){
	if($.trim($("#join_id").val())==""){
		alert("아이디를 입력하세요!");
		$("#join_id").val("").focus();
		return false;
	}
	$join_pwd=$.trim($("#join_pwd").val());
	$join_pwd2=$.trim($("#join_pwd2").val());
	if($join_pwd == ""){
		alert("비밀번호를 입력하세요!");
		$("#join_pwd").val("").focus();
		return false;
	}
    if($join_pwd2 == ""){
    	alert("비밀번호확인을 입력하세요!");
    	$("#join_pwd2").val("").focus();
    	return false;
    }
    if($join_pwd != $join_pwd2){
    	alert("비번이 다릅니다!");
    	$("#join_pwd").val("");//비번 입력창 초기화
    	$("#join_pwd2").val("");//비번 확인창 초기화
    	$("#join_pwd").focus();//비번 입력창으로 포커스 이동
    	return false;
    }
    if($.trim($("#join_name").val())==""){
       alert("회원이름을 입력하세요!");
       $("#join_name").val("").focus();
       return false;
    }
    if($.trim($("#join_zip").val())==""){
    	alert("우편번호를 입력하세요!");
    	return false;
    }
    if($.trim($("#join_zip2").val())==""){
    	alert("우편번호를 입력하세요!");
       	return false;
    }
    if($.trim($("#join_addr").val())==""){
    	alert("주소를 입력하세요!");
    	return false;
    }
    if($.trim($("#join_addr2").val())==""){
    	alert("나머지 주소를 입력하세요!");
    	$("#join_addr2").val("").focus();
    	return false;
    }
    if($.trim($("#join_phone02").val())==""){
    	alert("폰번호를 입력하세요!");
    	$("#join_phone02").val("").focus();
    	return false;
    }
    if($.trim($("#join_phone03").val())==""){
    	alert("폰번호를 입력하세요!");
    	$("#join_phone03").val("").focus();
    	return false;
    }
    if($.trim($("#mail_id").val())==""){
    	alert("이메일을 입력하세요!");
    	$("#mail_id").val("").focus();
    	return false;
    }
    if($.trim($("#mail_domain").val())==""){
    	alert("이메일을 입력하세요!");
    	$("#mail_domain").val("").focus();
    	return false;
    }
 }
  //복사 시작
    /* 아이디 중복 체크*/
    function id_check(){
    	$("#idcheck").hide();
    	//아이디 영역을 숨김
    	$join_id=$.trim($("#join_id").val());
    	//1.입력글자 길이 체크
    	if($join_id.length < 4){
    		$newtext='<font color="red" size="3"><b>아이디는 4자 이상이어야 합니다.</b></font>';
    		$("#idcheck").text('');
    		//idcheck 아이디 영역 문자열을 초기화
    		$("#idcheck").show();
    		//idcheck 아이디 영역을 보이게 함.
    		$("#idcheck").append($newtext);
    		//idcheck영역에 문자열을 추가
    		$("#join_id").val('').focus();
    		return false;
    	};
    	if($join_id.length > 12){
    		$newtext='<font color="red" size="3"><b>아이디는12자 이하이어야 합니다.</b></font>';
    		$("#idcheck").text('');
    		//idcheck 아이디 영역 문자열을 초기화
    		$("#idcheck").show();
    		//idcheck 아이디 영역을 보이게 함.
    		$("#idcheck").append($newtext);
    		//idcheck영역에 문자열을 추가
    		$("#join_id").val('').focus();
    		return false;
    	};
    	//2.입력글자 확인
    	if(!(validate_userid($join_id))){
    		$newtext='<font color="red" size="3"><b>아이디는 영문소문자,숫자,_조합만 가능합니다.</b></font>';
    		$("#idcheck").text('');
    		$("#idcheck").show();
    		$("#idcheck").append($newtext);
    		$("#join_id").val('').focus();
    		  return false;
    	};
    	//아이디 중복확인
        $.ajax({//$는 jQuery란 뜻. $.ajax 뜻은 jQuery 내의 아작스 실행
            type:"POST",//데이터를 서버로 보내는 방법
           //url:"./member/member_idcheck.jsp",    
            url:"join_idcheck.do", //아작스 서버 주소 파일명
            data: {"join_id":$join_id},  //좌측 join_id 피라미터 이름에 우측 $join_id변수값을 저장
            datatype:"int",//서버의 실행된 결과값을 사용자로 받아오는 자료형
            success: function (data) {//success는 아작스로 받아오는것이 성공했을경우
            	//서버 데이터를 data변수에 저장
          	  if(data==1){//중복 아이디가 있다면
          		$newtext='<font color="red" size="3"><b>중복 아이디입니다.</b></font>';
          		$("#idcheck").text('');
            	$("#idcheck").show();
            	$("#idcheck").append($newtext);          		
              	$("#join_id").val('').focus();
              	return false;
    	     
          	  }else{//중복 아이디가 아니면
          		$newtext='<font color="blue" size="3"><b>사용가능한 아이디입니다.</b></font>';
          		$("#idcheck").text('');
          		$("#idcheck").show();
          		$("#idcheck").append($newtext);
          		$("#join_pwd").focus();
          	  }  	    	  
            },
        	  error:function(){
        		  alert("data error");
        	  }
          });//$.ajax	
    };
    /*아이디 중복 체크 끝*/

    function validate_userid($joinid)
    {
      var pattern= new RegExp(/^[a-z0-9_]+$/);
      return pattern.test($join_id);
    };
    //복사 끝
    
function post_search(){
	alert("우편검색을 클릭하세요!");
}

//우편 검색
function post_check(){
	$url="zip_find.do";
	window.open($url,"우편검색","width=450,height=150,scrollbars=yes");
}

//직접입력,멜주소 선택
function domain_list(){
	var num=m.mail_list.selectedIndex;
	/*selectedIndex속성은 select객체 하위
	의 속성으로 해당 리스트 목록번호를 반
	환*/
	if(num==-1){
		//num==-1은 리스트목록이 없다
		return true;
	}
	if(m.mail_list.value=="직접입력"){
		m.mail_domain.value="";
		m.mail_domain.readOnly=false;
		//쓰기 가능
		m.mail_domain.focus();
	}
	//메일도메인주소를 직접입력하는부분
	else {
		//리스트에서 해당도메인주소를 선택
		m.mail_domain.value=m.mail_list.options[num].value;
		/*num변수에는 해당리스트목록번호저
		장.해당번호의 value값이 도메인주소창
		에 선택.options속성은 select객체하위
		의 속성으로서 해당리스트번호의 
		value값을 가져온다.*/
		m.mail_domain.readOnly=true;
		//쓰기가 불가능하고,읽기만 가능
	}
}

/* member_edit.jsp 경고창 */
function edit_check(){
	
	$join_pwd=$.trim($("#join_pwd").val());
	$join_pwd2=$.trim($("#join_pwd2").val());
	if($join_pwd == ""){
		alert("비밀번호를 입력하세요!");
		$("#join_pwd").val("").focus();
		return false;
	}
    if($join_pwd2 == ""){
    	alert("비밀번호확인을 입력하세요!");
    	$("#join_pwd2").val("").focus();
    	return false;
    }
    if($join_pwd != $join_pwd2){
    	alert("비번이 다릅니다!");
    	$("#join_pwd").val("");//비번 입력창 초기화
    	$("#join_pwd2").val("");//비번 확인창 초기화
    	$("#join_pwd").focus();//비번 입력창으로 포커스 이동
    	return false;
    }
    if($.trim($("#join_name").val())==""){
       alert("회원이름을 입력하세요!");
       $("#join_name").val("").focus();
       return false;
    }
    if($.trim($("#join_zip").val())==""){
    	alert("우편번호를 입력하세요!");
    	return false;
    }
    if($.trim($("#join_zip2").val())==""){
    	alert("우편번호를 입력하세요!");
       	return false;
    }
    if($.trim($("#join_addr").val())==""){
    	alert("주소를 입력하세요!");
    	return false;
    }
    if($.trim($("#join_addr2").val())==""){
    	alert("나머지 주소를 입력하세요!");
    	$("#join_addr2").val("").focus();
    	return false;
    }
    if($.trim($("#join_phone02").val())==""){
    	alert("폰번호를 입력하세요!");
    	$("#join_phone02").val("").focus();
    	return false;
    }
    if($.trim($("#join_phone03").val())==""){
    	alert("폰번호를 입력하세요!");
    	$("#join_phone03").val("").focus();
    	return false;
    }
    if($.trim($("#mail_id").val())==""){
    	alert("이메일을 입력하세요!");
    	$("#mail_id").val("").focus();
    	return false;
    }
    if($.trim($("#mail_domain").val())==""){
    	alert("이메일을 입력하세요!");
    	$("#mail_domain").val("").focus();
    	return false;
    }

}
