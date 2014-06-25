package com.naver.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.naver.dao.GongjiDAO;
import com.naver.model.GongjiBean;

public class IndexGongjiAction {

	private GongjiDAO gongjiService;

	public void setGongjiService(GongjiDAO gongjiService) {
		this.gongjiService = gongjiService;
	}//스프링 setter DI 설정
	
	/* 메인화면에 최신공지 목록 5개 출력 */
	@RequestMapping(value="/index_gongji.do")
	public ModelAndView index_gongji(){
		List<GongjiBean> ig_list=
				gongjiService.getIndex_gongjiList();
		//최신공지 목록 5개 가져오기
		
		ModelAndView ig_m=new ModelAndView();
		ig_m.addObject("ig_list",ig_list);
		ig_m.setViewName("index_gongji");
		//jsp폴더의 index_gongji.jsp 부페이지가 시랳ㅇ
		
		return ig_m;
	}
	
	/*공지내용+목록*/
	@RequestMapping(value="/gongji_cont")
	public String gongji_cont(Model listM,
//			@RequestParam("gongji_no") int gongji_no,//페이징부분에서 에러남
			HttpServletRequest request,
			GongjiBean g
			)throws Exception{
		
		if(request.getParameter("state") !=null){ //아랫부분은 페이지 번호가 넘어감
			int page=1;
			if(request.getParameter("page") !=null){
				page=Integer.parseInt(request.getParameter("page"));
			}
		}
		
int gongji_no=Integer.parseInt(request.getParameter("gongji_no"));
     String state=request.getParameter("state");
     if(state.equals("cont")){
		this.gongjiService.updateHit(gongji_no);//조회수 증가
     }
		GongjiBean g2=gongjiService.getGongjiCont(gongji_no);
		
		String gongji_cont=g2.getGongji_cont().replace("\n", "<br/>");
		
		listM.addAttribute("g",g2);
		listM.addAttribute("gongji_cont",gongji_cont);
		listM.addAttribute("gongji_no",gongji_no);
	
	 /*목록*/
		
    int meter = Integer.parseInt(request.getParameter("meter"));		
    int page=1; /* 페이징 인경우만 사용함 */
	int limit=meter;			
	
	if(request.getParameter("page") != null){
		page=Integer.parseInt(request.getParameter("page"));
		//get방식으로 넘어온 쪽번호를 정수형 숫자로 바꿔서 좌측변수에 저장한다.
	}
	
	int listcount=this.gongjiService.getListCount();		
    //전체 공지 개수
	
	g.setStartrow((page-1)*limit+1);
	g.setEndrow(g.getStartrow()+limit-1);
	
	List<GongjiBean> blist=
			this.gongjiService.getGongjiList(g);
    //공지 목록 
	
	//총 페이지 수.
	int maxpage=(int)((double)listcount/limit+0.95); //0.95를 더해서 올림 처리.
	//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
	int startpage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
	//현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등...)
	int endpage = maxpage;

	if (endpage>startpage+10-1) endpage=startpage+10-1;
	
	listM.addAttribute("blist", blist);
	listM.addAttribute("page", page);
	listM.addAttribute("startpage", startpage);
	listM.addAttribute("endpage", endpage);
	listM.addAttribute("maxpage", maxpage);		
	/*목록끝*/
	
 return "gongji/gongji_cont";
}


}
