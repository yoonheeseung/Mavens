package com.naver.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.naver.dao.AdminGongjiDAO;
import com.naver.model.BoardBean;
import com.naver.model.GongjiBean;

public class AdminGongjiAction {

	private AdminGongjiDAO admingongjiService;

	public void setAdmingongjiService(AdminGongjiDAO admingongjiService) {
		this.admingongjiService = admingongjiService;
	}//스프링 setter DI 설정
	
	/*관리자 공지목록 */
	@RequestMapping(value="/admin_gongji_list")
	public String admin_gongji_list(Model listM,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,
			@ModelAttribute GongjiBean g)
	throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String admin_id=(String)session.getAttribute("admin_id");
		if(admin_id==null){
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!')");
			out.println("location='admin_index.do'");
			out.println("</script>");
		}else{
			int page=1;
			int limit=5;			

			String find_name=null;
			String find_field=null;

			if(request.getParameter("find_name") != null){
				find_name=request.getParameter("find_name").trim();	
				find_name=new String(find_name.getBytes("ISO-8859-1"),"UTF-8");
				//get방식으로 넘어온 한글을 안깨지게 할려면 String클래스의 
				//getBytes()메서드를 사용해야 한다.
			}
			find_field=request.getParameter("find_field");
			//검색필드를 저장함.gongji_title,gongji_cont를 저장
			g.setFind_field(find_field);
			g.setFind_name("%"+find_name+"%");
			//%는 오라클 쿼리문 검색연산자로서 하나이상의임의의 문자와
			//매핑을 한다.?는 오라클 쿼리문 검색연산자로서 하나의 문자
			//와 매핑을 한다.(오라클 월말평가 문제 예상)

			//System.out.println(find_name);
			//이클립스 콘솔모드에 검색어가 출력

			if(request.getParameter("page") != null){
				page=Integer.parseInt(request.getParameter("page"));
				//get방식으로 넘어온 쪽번호를 정수형 숫자로 바꿔서 좌측변수에 저장한다.
			}

			//검색전 총레코드개수,제목 검색후 레코드 개수,
			//내용 검색후 레코드 개수
			int listcount=this.admingongjiService.getListCount(g);		
			//System.out.println(listcount);

			g.setStartrow((page-1)*5+1);
			g.setEndrow(g.getStartrow()+limit-1);

			List<BoardBean> blist=
					this.admingongjiService.getGongjiList(g);
			//검색 전후 목록 

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
			listM.addAttribute("listcount",listcount);	
			listM.addAttribute("find_field",find_field);
			//find_field 키값에 board_title,board_cont저장
			listM.addAttribute("find_name", find_name);
			//find_name 키값에 검색어를 저장	
			return "admin/admin_gongji_list";
		}
		return null;
	}
	
	/*관리자 공지 작성 */
	@RequestMapping(value="/admin_gongji_write")
	public String admin_gongji_write(
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,
			Model wm
			) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		session=request.getSession();
		
		String admin_id=(String)session.getAttribute("admin_id");
		if(admin_id==null){
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!')");
			out.println("location='admin_index.do'");
			out.println("</script>");
		}else{	
			int page=1;
			if(request.getParameter("page") !=null){
				page=Integer.parseInt(request.getParameter("page"));
			}
			wm.addAttribute("page",page);
			return "admin/admin_gongji_write";
	}
	return null;
	}
	
	/* 관리자 공지 저장 */
	@RequestMapping(value="/admin_gongji_write_ok.do")
	public String admin_gongji_write_ok(
			@ModelAttribute GongjiBean g,
			HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session)
			throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		session=request.getSession();
		
		String admin_id=(String)session.getAttribute("admin_id");
		if(admin_id ==null){
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!')");
			out.println("location='admin_index.do'");
			out.println("</script>");
		}else{
			this.admingongjiService.insertG(g);//저장
			return "redirect:/admin_gongji_list.do";
		}
		return null;
	}

	/* 관리자 공지내용+수정삭제 */
	@RequestMapping(value="/admin_gongji_cont.do")
	public ModelAndView admin_gongji_cont(
			@RequestParam("gongji_no") int gongji_no,
			@RequestParam("page") int page,
			@RequestParam("state") String state,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session )
		throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		session=request.getSession();
		
		String admin_id=(String)session.getAttribute("admin_id");
		if(admin_id==null){
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!')");
			out.println("location='admin_index.do'");
			out.println("</script>");
		}else{
			GongjiBean g=this.admingongjiService.getGongjiCont(gongji_no);
			String gongji_cont=g.getGongji_cont().replace("\n", "<br/>");
			
			ModelAndView cm=new ModelAndView();
			
			cm.addObject("g",g);
			cm.addObject("gongji_cont",gongji_cont);
			cm.addObject("page",page);
			
			if(state.equals("cont")){
				cm.setViewName("admin/admin_gongji_cont");
			}else if(state.equals("edit")){
				cm.setViewName("admin/admin_gongji_edit");
			}else if(state.equals("del")){
				cm.setViewName("admin/admin_gongji_del");
			}
			return cm;
		}
		return null;
	}
	
	/*관리자 공지수정 */
	@RequestMapping(value="/admin_gongji_edit_ok.do")
	public String admin_gongji_edit_ok(
			@ModelAttribute GongjiBean eg,
			@RequestParam("page") int page,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session
			)throws Exception{
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		session=request.getSession();
		
//		String admin_id=(String)session.getAttribute("admin_id");
		isSession(request,response, session);
		
//		if(admin_id==null){
//			out.println("<script>");
//			out.println("alert('다시 로그인 하세요')");
//			out.println("location='admin_index.do'");
//			out.println("</script>");
//		}else{
			GongjiBean db_pwd =
					this.admingongjiService.getGongjiCont(eg.getGongji_no());
			//디비로 부터 비번을 가져옴.
			if(!db_pwd.getGongji_pwd().equals(eg.getGongji_pwd())){
				out.println("<script>");
				out.println("alert('비번이 다릅니다.')");
				out.println("history.back()");
				out.println("</script>");
			}else{
				this.admingongjiService.editG(eg);//수정
				return "redirect:/admin_gongji_list.do?page="+page;
			}
//		}
		return null;
	}
	

/** id check **/
	public void isSession(
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session )throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		session=request.getSession();

		String admin_id=(String)session.getAttribute("admin_id");
		if(admin_id==null){
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!')");
			out.println("location='admin_index.do'");
			out.println("</script>");
		}
	}

   /* 관리자 공지삭제 */
	@RequestMapping(value="/admin_gongji_del_ok.do")
	public String admin_gongji_del_ok(
			@RequestParam("gongji_no") int gongji_no,
			@RequestParam("page") int page,
			@RequestParam("del_pwd") String del_pwd,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session
			) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		session=request.getSession();
		
		isSession(request, response, session);
		
		GongjiBean db_pwd=
				this.admingongjiService.getGongjiCont(gongji_no);
		if(!(db_pwd.getGongji_pwd().equals(del_pwd))){
			out.println("<script>");
			out.println("alert('비번이 다릅니다')");
			out.println("history.back()");
			out.println("</script>");
		}else{
			this.admingongjiService.delG(gongji_no);
			//글번호를 기준으로 삭제
			out.println("<script>");
			out.println("alert('삭제되었습니다.')");
			out.println("location='admin_gongji_list.do?page="+page+"'");
			out.println("</script>");
			
			//return "redirect:/admin_gongji_list.do?page="+page;
		}
		return null;
	}
	
}