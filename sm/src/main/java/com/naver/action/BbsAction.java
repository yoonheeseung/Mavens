package com.naver.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@Controller 어노테이션은 implements Controller를 하지 않아도
//쉽게 스프링 컨트롤 클래스를 만들수 있게 한다.
public class BbsAction {

	/* 자료실 글쓰기 */
	@RequestMapping(value="/bbs_write.do",method=RequestMethod.GET)
	//get방식으로 접근할때 호출되는 어노테이션
	public String bbs_write(){
		return "bbs/bbs_write";
		//jsp/bbs/bbs_write.jsp 뷰페이지가 실행
	}
	
}
