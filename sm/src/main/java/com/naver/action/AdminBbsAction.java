package com.naver.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.naver.dao.AdminBbsDAO;
import com.naver.model.BbsBean;
import com.oreilly.servlet.MultipartRequest;

@Controller
public class AdminBbsAction {

	private AdminBbsDAO adminbbsService;// DB연동객체 생성,JoinPointer

	public void setAdminbbsService(AdminBbsDAO adminbbsService) {
		this.adminbbsService = adminbbsService;
	}// setter DI 설정

	/* 관리자 자료실 목록 */
	@RequestMapping(value = "/admin_bbs_list.do")
	public String admin_bbs_list(Model listM, HttpServletRequest request,
			@ModelAttribute BbsBean b, HttpServletResponse response,
			HttpSession session) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		session = request.getSession();

		String admin_id = (String) session.getAttribute("admin_id");
		if (admin_id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			int page = 1;
			int limit = 5;

			String find_field = null;
			String find_name = null;

			if (request.getParameter("find_name") != null) {
				find_name = request.getParameter("find_name").trim();
				find_name = new String(find_name.getBytes("ISO-8859-1"),
						"utf-8");
			}
			find_field = request.getParameter("find_field");
			b.setFind_field(find_field);
			b.setFind_name("%" + find_name + "%");

			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}

			// 총 레코드 개수를 반환
			int listcount = this.adminbbsService.getListCount(b);

			b.setStartrow((page - 1) * 5 + 1);
			b.setEndrow(b.getStartrow() + limit - 1);

			List<BbsBean> blist = this.adminbbsService.getBbsList(b);
			// 방명록 목록

			// 총 페이지 수.
			int maxpage = (int) ((double) listcount / limit + 0.95); // 0.95를
																		// 더해서
																		// 올림
																		// 처리.
			// 현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
			int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
			// 현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등...)
			int endpage = maxpage;

			if (endpage > startpage + 10 - 1)
				endpage = startpage + 10 - 1;

			listM.addAttribute("blist", blist);
			listM.addAttribute("page", page);
			listM.addAttribute("startpage", startpage);
			listM.addAttribute("endpage", endpage);
			listM.addAttribute("maxpage", maxpage);
			listM.addAttribute("listcount", listcount);
			/* paging 끝 */
			listM.addAttribute("find_field", find_field);
			// find_field 키값에 board_title,board_cont저장
			listM.addAttribute("find_name", find_name);
			// find_name 키값에 검색어를 저장

			return "admin/admin_bbs_list";
			// admin폴더의 admin_bbs_list.jsp 뷰페이지로 이동
		}
		return null;
	}

	/* 관리자 자료실 글쓰기 */
	@RequestMapping(value = "/admin_bbs_write.do")
	public String admin_bbs_write(HttpServletRequest request,
			HttpSession session, HttpServletResponse response, Model wm)
			throws Exception {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		session = request.getSession();

		String admin_id = (String) session.getAttribute("admin_id");
		if (admin_id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!')");
			out.println("history.back()");
			out.println("</script>");
		} else {

			int page = 1;
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			wm.addAttribute("page", page);
			return "admin/admin_bbs_write";
		}
		return null;
	}

	/* 관리자 자료실 저장 */
	@RequestMapping(value = "/admin_bbs_write_ok.do")
	public String admin_bbs_write_ok(@ModelAttribute BbsBean b,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		session = request.getSession();

		String admin_id = (String) session.getAttribute("admin_id");
		if (admin_id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!')");
			out.println("history.back()");
			out.println("</script>");
		} else {

			String saveFolder = "C:/Users/unisung/git/Mavens/sm/src/main/webapp/upload";// 이진파일
																						// 업로드
																						// 경로
			int fileSize = 5 * 1025 * 1024;// 이진파일 업로드 최대크기,5mb
			MultipartRequest multi = null;// 이진파일 업로드 변수선언
			multi = new MultipartRequest(request, saveFolder, fileSize, "utf-8");
			// 이진파일 업로드 multi객체 생성
			// 생성자 첫번째 전달인자 request는 사용자폼에서 입력한 것을 서버로 전달
			// 두번째 전달인자는 이진파일 업로드 서버 경로
			// 세번째 전달인자는 이진파일 최대크기
			// 네번째 전달인자는 언어코딩 타입

			String bbs_name = multi.getParameter("bbs_name").trim();
			String bbs_title = multi.getParameter("bbs_title").trim();
			String bbs_pwd = multi.getParameter("bbs_pwd").trim();
			String bbs_cont = multi.getParameter("bbs_cont").trim();

			File UpFile = multi.getFile("bbs_file");// 첨부한 이진파일을 가져옴.

			if (UpFile != null) {
				String fileName = UpFile.getName();// 첨부한 이진파일명을 구함
				Calendar c = Calendar.getInstance();
				String year = String.format("%04d", c.get(Calendar.YEAR));// 년도
				String month = String.format("%02d", c.get(Calendar.MONTH) + 1);// 월
				// 0으로 반환되기 때문이다.
				String date = String.format("%02d", c.get(Calendar.DATE));// 일

				String homedir = saveFolder + "/" + year + "-" + month + "-"
						+ date;
				// 새로운 이진파일 폴더 경로를 저장
				File path1 = new File(homedir);
				if (!(path1.exists())) {// 폴더경로가 없으면
					path1.mkdir();// 폴더생성
				}
				Random r = new Random();// 난수를 발생시키는 클래스
				int random = r.nextInt(100000000);
				// 1억사이의 정수형 난수를 발생시킴
				/********** 확장자 구하기 *******************/
				int index = fileName.lastIndexOf(".");
				// File 클래스의 getName()메서드는 이진파일명을 받아온다.
				// lastIndexOf("문자")는 String클래스의 메서드로 해당문자
				// 를 문자열 끝 즉 우츠에서 헤아려 문자의 위치번로를 반환한다.
				String fileExtension = fileName.substring(index + 1);
				// 파일의 확장자를 구한다.
				/*********** 확장자 구하기 끝 *********************/
				String refileName = "bbs" + year + month + date + random + "."
						+ fileExtension;// 새로운 파일명을 저장
				System.out.println("새로운파일명:" + refileName);
				String fileDBName = "/" + year + "-" + month + "-" + date + "/"
						+ refileName;// 데이터베이스에 저장될 레코드 값
				System.out.println("데이터베이스파일명:" + fileDBName);

				System.out.println(UpFile.renameTo(new File(homedir + "/"
						+ refileName)));

				// 새롭게 생성된 폴더에 바뀐 이진파일명으로 업로드
				b.setBbs_file(fileDBName);
			} else {// 이진파일을 첨부하지 않았을 때
				String fileDBName = "";// 첨부하지 않은 경우는 빈공백을 저장시킴
				b.setBbs_file(fileDBName);
			}

			b.setBbs_name(bbs_name);
			b.setBbs_title(bbs_title);
			b.setBbs_pwd(bbs_pwd);
			b.setBbs_cont(bbs_cont);

			this.adminbbsService.insertBbs(b);// 저장메서드 호출

			return "redirect:/admin_bbs_list.do";
		}
		return null;
	}

	/* 관리자 내용보기 + 수정폼+답변글폼 */
	@RequestMapping(value = "/admin_bbs_cont.do")
	public ModelAndView admin_bbs_cont(@RequestParam("bbs_no") int bbs_no,
			@RequestParam("state") String state, HttpServletRequest request)
			throws Exception {
		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		BbsBean b = this.adminbbsService.getBbsCont(bbs_no);
		// 글번호에 대당하는 디비 레코드 값을 가져온다.
		String bbs_cont = b.getBbs_cont().replace("\n", "<br/>");

		System.out.println("파일명:"+b.getBbs_file());
		ModelAndView cm = new ModelAndView();
		cm.addObject("b", b);
		cm.addObject("bbs_cont", bbs_cont);
		cm.addObject("page", page);

		if (state.equals("cont")) {// 내용보기일때
			cm.setViewName("admin/admin_bbs_cont");
		} else if (state.equals("edit")) {// 수정
			cm.setViewName("admin/admin_bbs_edit");
		} else if (state.equals("del")) {// 삭제
			cm.setViewName("admin/admin_bbs_del");
		}
		return cm;
	}

	/* 관리자 자료실 수정 */
	@RequestMapping(value = "/admin_bbs_edit_ok.do")
	public String admin_bbs_edit_ok(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@ModelAttribute BbsBean b) throws Exception {
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
			String saveFolder = "C:/Users/unisung/git/Mavens/sm/src/main/webapp/upload";
			int filesize = 5 * 1024 * 1024;
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, saveFolder, filesize, "utf-8");
			// 이진파일 업로드 객체 생성

			int bbs_no = Integer.parseInt(multi.getParameter("bbs_no"));
			int page = Integer.parseInt(multi.getParameter("page"));
			String bbs_name = multi.getParameter("bbs_name").trim();
			String bbs_title = multi.getParameter("bbs_title").trim();
			String bbs_pwd = multi.getParameter("bbs_pwd").trim();
			String bbs_cont = multi.getParameter("bbs_cont").trim();

			BbsBean db_pwd = this.adminbbsService.getBbsCont(bbs_no);

			if (!db_pwd.getBbs_pwd().equals(bbs_pwd)) {
				out.println("<script>");
				out.println("alert('비번이 다릅니다.!')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				File UpFile = multi.getFile("bbs_file");
				if (UpFile != null) {
					String fileName = UpFile.getName();
					File DelFile = new File(saveFolder + db_pwd.getBbs_file());
					if (DelFile.exists()) {
						DelFile.delete();
					}

					Calendar c = Calendar.getInstance();
					String year = String.format("%04d", c.get(Calendar.YEAR));
					String month = String.format("%02d",
							c.get(Calendar.MONTH) + 1);
					String date = String.format("%02d", c.get(Calendar.DATE));
					String hour = String.format("%02d",
							c.get(Calendar.HOUR_OF_DAY));
					String minute = String.format("%02d",
							c.get(Calendar.MINUTE));
					String second = String.format("%02d",
							c.get(Calendar.SECOND));

					String homedir = saveFolder + "/" + year + "-" + month
							+ "-" + date;

					File path1 = new File(homedir);
					if (!(path1.exists())) {
						path1.mkdir();
					}
					Random r = new Random();
					int random = r.nextInt(100000000);// 1억 사이의 난수 발생

					/********** 확장자 구하기 *******************/
					int index = fileName.lastIndexOf(".");
					// File 클래스의 getName()메서드는 이진파일명을 받아온다.
					// lastIndexOf("문자")는 String클래스의 메서드로 해당문자
					// 를 문자열 끝 즉 우츠에서 헤아려 문자의 위치번로를 반환한다.
					String fileExtension = fileName.substring(index + 1);
					// 파일의 확장자를 구한다.
					/*********** 확장자 구하기 끝 *********************/
					String refileName = "bbs" + year + month + date + hour
							+ minute + second + random + "." + fileExtension;
					System.out.println("새로운파일명:" + refileName);
					String fileDBName = "/" + year + "-" + month + "-" + date
							+ "/" + refileName;// 데이터베이스에 저장될 레코드 값
					System.out.println("데이터베이스파일명:" + fileDBName);

					UpFile.renameTo(new File(homedir + "/" + refileName));

					b.setBbs_file(fileDBName);
					
					System.out.println("fileName:" + fileName);
				} else {//첨부하지 않은 경우
					if(db_pwd.getBbs_file() !=null){
					b.setBbs_file(db_pwd.getBbs_file());//기존 이진파일을 가져와 저장
					System.out.println(db_pwd.getBbs_file());
					}else{
						String bbs_file="";
						b.setBbs_file(bbs_file);
					}
				}
				b.setBbs_no(bbs_no);
				b.setBbs_name(bbs_name);
				b.setBbs_title(bbs_title);
				b.setBbs_cont(bbs_cont);

				this.adminbbsService.editBbs(b);
				return "redirect:/admin_bbs_list.do?page=" + page;
			}

		}
		return null;
	}

	/* 관리자 자료실 삭제 */
	@RequestMapping(value = "/admin_bbs_del_ok.do")
	public String admin_bbs_del_ok(@RequestParam("bbs_no") int bbs_no,
			@RequestParam("page") int page,
			@RequestParam("del_pwd") String del_pwd,
			@ModelAttribute BbsBean db_pwd, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

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
			db_pwd = this.adminbbsService.getBbsCont(bbs_no);
			if (!db_pwd.getBbs_pwd().equals(del_pwd)) {
				out.println("<script>");
				out.println("alert('비번이 다릅니다!')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				String up = "C:/Users/unisung/git/Mavens/sm/src/main/webapp/upload";
				String fname = db_pwd.getBbs_file();
				this.adminbbsService.deleteBbs(bbs_no);
				
				if(fname !=null){
					File file=new File(up+fname);//삭제할 파일 객체를 생성
					file.delete();//기존 이진파일을 삭제
				}
				out.println("<script>");
				out.println("alert('삭제되었습니다.!')");
				out.println("location='admin_bbs_list.do?page="+page+"'");
				out.println("</script>");
			}
		}
		return null;

	}
}
