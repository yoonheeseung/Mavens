package com.naver.action;

import java.io.IOException;
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

import com.naver.dao.AdminBoardDAO;
import com.naver.model.BoardBean;

public class AdminBoardAction {

	private AdminBoardDAO adminboardService;

	public void setAdminboardService(AdminBoardDAO adminboardService) {
		this.adminboardService = adminboardService;
	}// 스프링 setter DI 설정

	/*관리자 게시판 목록 */
	@RequestMapping(value="/admin_board_list.do")
	public String admin_board_list(HttpServletRequest request,
			@ModelAttribute BoardBean b,
			Model listM,
			HttpServletResponse response,
			HttpSession session) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		session=request.getSession();

		String admin_id=(String)session.getAttribute("admin_id");

		if(admin_id == null){
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
			//검색필드를 저장함.board_title,board_cont를 저장
			b.setFind_field(find_field);
			b.setFind_name("%"+find_name+"%");
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
			int listcount=this.adminboardService.getListCount(b);		
			//System.out.println(listcount);

			b.setStartrow((page-1)*5+1);
			b.setEndrow(b.getStartrow()+limit-1);

			List<BoardBean> blist=
					this.adminboardService.getBoardList(b);
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
			return "admin/admin_board_list";
		}
		return null;
	}

	/* 관리자 게시판 글쓰기 */
	@RequestMapping(value = "/admin_board_write.do")
	public String admin_board_write(Model wm, 
			                        HttpServletRequest request,
			                        HttpServletResponse response, 
			                        HttpSession session)
			throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		session = request.getSession();
		String admin_id = (String) session.getAttribute("admin_id");

		if (admin_id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!')");
			out.println("location='admin_index.do'");
			out.println("</script>");
		} else {

			int page = 1;
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			wm.addAttribute("page", page);
			return "admin/admin_board_write";
		}
		return null;
	}

	/* 관리자 게시판 저장 */
	@RequestMapping(value = "/admin_board_write_ok.do")
	public String admin_board_write_ok(@ModelAttribute BoardBean b,
			HttpServletResponse response, HttpSession session,
			HttpServletRequest request) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		session = request.getSession();
		String admin_id = (String) session.getAttribute("admin_id");
		if (admin_id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!')");
			out.println("location='admin_index.do'");
			out.println("</script>");
		} else {
			this.adminboardService.insertBoard(b);// 저장
			return "redirect:/admin_board_list.do";
		}

		return null;
	}

	/* 내용보기+수정폼+삭제폼 */
	@RequestMapping(value = "/admin_board_cont.do")
	public ModelAndView admin_board_cont(
			@RequestParam("board_no") int board_no,
			@RequestParam("state") String state, HttpSession session,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		session = request.getSession();
		String admin_id = (String) session.getAttribute("admin_id");
		if (admin_id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!')");
			out.println("location='admin_index.do'");
			out.println("</script>");
		} else {
			int page = 1;
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}

			BoardBean b = this.adminboardService.getCont(board_no);
			String board_cont = b.getBoard_cont().replace("\n", "<br/>");
			// textarea박스에서 엔터키 친부분을 다음줄로 개행

			ModelAndView cm = new ModelAndView();
			cm.addObject("b", b);
			cm.addObject("board_cont", board_cont);
			cm.addObject("page", page);

			if (state.equals("cont")) {
				cm.setViewName("admin/admin_board_cont");
			}
			if (state.equals("edit")) {
				cm.setViewName("admin/admin_board_edit");
			}
			if (state.equals("del")) {
				cm.setViewName("admin/admin_board_del");
			}
			return cm;
		}
		return null;
	}

	//수정
	@RequestMapping(value = "/admin_board_edit_ok.do")
	public String admin_board_edit_ok(HttpServletResponse response,
			HttpServletRequest request, HttpSession session,
			@ModelAttribute BoardBean b) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		session = request.getSession();
		String admin_id = (String) session.getAttribute("admin_id");
		if (admin_id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 해주세요!')");
			out.println("location='admin_index.do'");
			out.println("</script>");
		} else {
			int page = 1;
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			BoardBean db_pwd = this.adminboardService.getCont(b.getBoard_no());
			// 디비로 부터 비번을 가져옴
			if (!db_pwd.getBoard_pwd().equals(b.getBoard_pwd())) {
				out.println("<script>");
				out.println("alert('비번이 다릅니다!')");
				out.println("history.back();");
				out.println("</script>");
			} else {
				this.adminboardService.editBoard(b);
				return "redirect:/admin_board_list.do?page=" + page;
			}
		}
		return null;
	}
	
	/* 관리자 게시판 삭제 */
	@RequestMapping(value="/admin_board_del_ok.do")
	public String admin_board_del_ok(
			HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			@RequestParam("board_no") int board_no,
			@RequestParam("page") int page,
			@RequestParam("del_pwd") String del_pwd,
			@ModelAttribute BoardBean db_pwd
			) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		session=request.getSession();
		
		String admin_id=(String) session.getAttribute("admin_id");
		if(admin_id==null){
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!')");
			out.println("location='admin_indedx.do'");
			out.println("</script>");
		}else{
			db_pwd=this.adminboardService.getCont(board_no);
			//디비로 부터 비번을 가져옴.
			if(db_pwd.getBoard_pwd().equals(del_pwd)){
				out.println("<script>");
				out.println("alert('비번이 다릅니다!')");
				out.println("history.back()");
				out.println("</script>");
			}else{
				this.adminboardService.delBoard(board_no);
				return "redirect:/admin_board_list.do?page="+page;
			}
		}
		return null;
	}
}
