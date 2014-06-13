package com.naver.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pwdconv.PwdChange;

import com.naver.dao.AdminDAO;
import com.naver.model.AdminBean;

@Controller
public class AdminAction {

	 private AdminDAO adminService;

	public void setAdminService(AdminDAO adminService) {
		this.adminService = adminService;
	}//setter DI 설정
	
	/* 관리자 로그인 페이지 */
	@RequestMapping(value="/admin_index.do")
	public String admin_index(){
		return "admin/admin_index";
	}
	
	/*관리자 로그인 OK */
	@RequestMapping(value="/admin_ok.do")
	public String admin_ok(@ModelAttribute AdminBean ab,
			               HttpSession session,
			               HttpServletResponse response,
			               HttpServletRequest request)
			throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		session=request.getSession();//세션객체 생성
		AdminBean db_id=this.adminService.find(ab.getAdmin_id());

		ab.setAdmin_pwd(PwdChange.getPassWordToXEMD5String(ab.getAdmin_pwd()));
		ab.setAdmin_name("관리자");

			if(db_id==null){
				out.println("<script>");
				out.println("alert('관리자가 아닙니다!')");
				out.println("history.back()");
				out.println("</script>");
			}else{
				if(!db_id.getAdmin_pwd().equals(ab.getAdmin_pwd())){
					out.println("<script>");
					out.println("alert('비번이 다릅니다!')");
					out.println("history.go(-1)");
					out.println("</script>");
				}else{
			session.setAttribute("admin_id",ab.getAdmin_id());
			//admin_id관리자 아이디 키에 관리자 아이디를 저장
			session.setAttribute("admin_name",db_id.getAdmin_name());
			return "redirect:/admin_main.do";
				}
			}
			return null;
		}
	
	/*관리자 메인화면 */
	@RequestMapping(value="/admin_main.do")
	public String admin_main(HttpServletRequest request,
							 HttpServletResponse response,
							 HttpSession session) 
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
			return "admin/admin_main";
		} 
		
		return null;
	}
			               	
	 /*관리자 로그아웃 */
	@RequestMapping(value="/admin_logout.do")
	public String admin_logout(HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		session=request.getSession();//session객체 생성
		
		session.invalidate();//세션을 만료
		
		out.println("<script>");
		out.println("alert('로그아웃되었습니다!')");
		out.println("location='admin_index.do'");
		out.println("</script>");
		return null;
	}
			
}
