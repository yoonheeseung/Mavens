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

import pwdconv.PwdChange;

import com.naver.dao.AdminMemberDAO;
import com.naver.model.MemberBean;

public class AdminMemberAction {

	private AdminMemberDAO adminmemberService;

	public void setAdminmemberService(AdminMemberDAO adminmemberService) {
		this.adminmemberService = adminmemberService;
	}
	
	/*관리자 회원목록*/
	@RequestMapping(value="/admin_member_list.do")
	public String admin_member_list(
			HttpServletRequest request,
			@ModelAttribute MemberBean m,
			Model listM,HttpServletResponse response,
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
			m.setFind_field(find_field);
			m.setFind_name("%"+find_name+"%");
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
			int listcount=this.adminmemberService.getListCount(m);		
			//System.out.println(listcount);

			m.setStartrow((page-1)*5+1);
			m.setEndrow(m.getStartrow()+limit-1);

			List<MemberBean> blist=
					this.adminmemberService.getBbsList(m);
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
			return "admin/admin_member_list";
	    }
		return null;
	}
	
	/* 관리자 정보+수정+삭제*/
	@RequestMapping(value="/admin_member_info.do")
	public ModelAndView admin_member_info(
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("id") String id,
			@RequestParam("state") String state)
	throws Exception{
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
	   if(request.getParameter("page") != null){
		   page=Integer.parseInt(request.getParameter("page"));
	   }
	   MemberBean m=this.adminmemberService.getMember(id);
	   //디비로 부터 회원정보를 가져옴
	   String join_delcont=null;
	   
	   if(m.getJoin_delcont() != null){
       join_delcont=m.getJoin_delcont().replace("\n","<br/>");
	   }
	   
	   ModelAndView im=new ModelAndView();
	   
	   String[] phone={"010","011","016","018","019"};
	   String[] email={"naver.com","daum.net","gmail.com",
				"nate.com","korea.com","직접입력"};
	   
	   im.addObject("m",m);
	   im.addObject("page",page);
	   im.addObject("join_delcont",join_delcont);
	   im.addObject("phone",phone);
	   im.addObject("email",email);
	   
	   if(state.equals("info")){//정보보기
		   im.setViewName("admin/admin_member_info");
	   }else if(state.equals("edit")){
		   im.setViewName("admin/admin_member_edit");
	   }
	   return im;
   }
		return null;
	}
	
	/*관리자 정보 수정*/
	@RequestMapping(value="/admin_member_edit_ok.do")
	public String admin_member_edit_ok(
			HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			@ModelAttribute MemberBean m,
			@RequestParam("page") int page)
	throws Exception{
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
String pwd=PwdChange.getPassWordToXEMD5String(m.getJoin_pwd());
//MD5로 암호화
            m.setJoin_pwd(pwd);
        	this.adminmemberService.editM(m);        	
       
        	out.println("<script>");
        	out.println("alert('수정 되었습니다!')");
 out.println("location='admin_member_list.do?page="+page+"'");
        	out.println("</script>");
    //return "redirect:/admin_member_list.do?page="+page;
    //*.do?page=쪽번호를 get방식으로 넘김.
        }
		return null;
	}

	/* 관리자 회원 삭제*/
	@RequestMapping(value="/admin_member_del.do")
	public ModelAndView admin_member_del(
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("id") String id,
			@RequestParam("page") int page)
	throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		session=request.getSession();
		
  String admin_id=(String)session.getAttribute("admin_id");
  if(admin_id==null){
	  out.println("<script>");
	  out.println("alert('다시 로그인 하세요!')");
	  out.println("location='admin_index.do'");
	  out.println("</script>");
  }else{
	  this.adminmemberService.deleteM(id);
	  //아이디를 기준으로 삭제
	  
	  ModelAndView dm=
  new ModelAndView("redirect:/admin_member_list.do?page="+page);
	  return dm;
  }
		return null;
     }
}


















