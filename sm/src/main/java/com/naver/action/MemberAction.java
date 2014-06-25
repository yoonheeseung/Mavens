package com.naver.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pwdconv.PwdChange;

import com.naver.dao.MemberDAO;
import com.naver.model.MemberBean;
import com.naver.model.ZipcodeBean;
import com.naver.model.ZipcodeBean2;

public class MemberAction {

	private MemberDAO memberService;

	public void setMemberService(MemberDAO memberService) {
		this.memberService = memberService;
	}// setter DI 설정

	/* 로그인 페이지 */
	@RequestMapping(value = "/member_login.do")
	public String member_login() {
		return "member/member_login";
	}

	/* 회원가입폼 */
	@RequestMapping(value = "/member_join")
	public String member_join(Model j) {
		String[] phone = { "010", "011", "016", "018", "019" };
		String[] email = { "naver.com", "daum.net", "gmail.com", "nate.com",
				"korea.com", "직접입력" };

		/*
		 * List<PhoneBean> phone=this.memberService.getphoneList();
		 * List<EmailBean> email=this.memberService.getemailList();
		 */
		/*
		 * MemPhoneMailBean mb=new MemPhoneMailBean();
		 * 
		 * mb=this.memberService.getList();
		 * 
		 * List phone=new ArrayList(); List email=new ArrayList();
		 */

		/*
		 * phone.add(this.memberService.getphoneList());
		 * email.add(this.memberService.getemailList());
		 */
		j.addAttribute("phone", phone);
		j.addAttribute("email", email);

		return "member/member_join";
	}

	/* 아이디 중복 체크 */
	@RequestMapping(value = "/join_idcheck.do")
	public String join_idcheck(@RequestParam("join_id") String join_id,
			HttpServletResponse response, @ModelAttribute MemberBean db_id)
			throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		db_id = this.memberService.idcheck(join_id);
		// 아이디 중복 체크
		int re = -1;
		if (db_id != null) {
			re = 1;
		}
		out.println(re);// 값이 반환
		return null;
	}

	/* 우편 검색 폼 */
	@RequestMapping(value = "/zip_find.do")
	public String zip_find() {
		return "member/zip_find";
	}

	/* 우편번호 검색 결과 */
	@RequestMapping(value = "/zip_find_ok.do")
	public ModelAndView zip_find_ok(@RequestParam("dong") String dong)
			throws Exception {
		List<ZipcodeBean> zlist = this.memberService.zip("%" + dong + "%");

		List<ZipcodeBean2> zlist2 = new ArrayList<ZipcodeBean2>();

		for (ZipcodeBean z : zlist) {// 확정 for
			String addr = z.getSido() + " " + z.getGugun() + " " + z.getDong();// 시
																				// 도
																				// 구군
																				// 동을
																				// 저장
			ZipcodeBean2 z2 = new ZipcodeBean2();
			z2.setZipcode(z.getZipcode());// 우편번호 저장
			z2.setAdd(addr);// 시도 구군 동을 저장

			zlist2.add(z2);// 컬렉션에 추가
		}
		ModelAndView zm = new ModelAndView("member/zip_find");
		zm.addObject("zipcodelist", zlist2);
		zm.addObject("dong", dong);
		System.out.println("zip_find_ok.do");
		return zm;
	}

	/* 회원저장 */
	@RequestMapping(value = "/member_join_ok.do")
	public String member_join_ok(@ModelAttribute MemberBean m,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		MemberBean db_id = this.memberService.idcheck(m.getJoin_id());
		// 중복 아이디 검색
		if (db_id != null) {
			out.println("<script>");
			out.println("alert('중복아이디 입니다!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			String join_pwd = PwdChange.getPassWordToXEMD5String(m
					.getJoin_pwd());
			// 비번을 md5로 암호화 함.
			m.setJoin_pwd(join_pwd);
			this.memberService.insertMember(m);// 회원저장
			return "redirect:/member_login.do";
		}
		return null;
	}

	/* 비번찾기 */
	@RequestMapping(value = "/pwd_find.do")
	public String pwd_find() {
		return "member/pwd_find";
	}

	/* 비번찾기 결과 */
	@RequestMapping(value = "/pwd_find_ok.do")
	public String pwd_find_ok(@RequestParam("pwd_id") String pwd_id,
			@RequestParam("pwd_name") String pwd_name,
			HttpServletResponse response, @ModelAttribute MemberBean pm, Model m)
			throws Exception {

		System.out.println("pwd_find_ok.do");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		pm.setJoin_id(pwd_id);
		pm.setJoin_name(pwd_name);

		MemberBean dm = this.memberService.pwd_find(pm);
		// 아이디와 이름을 기준으로 비번 발번
		if (dm != null) {
			Random r = new Random();
			int random = r.nextInt(100000);// 임시비번 난수 발생
			MemberBean rm = new MemberBean();
			rm.setJoin_id(pwd_id);
			rm.setJoin_pwd(PwdChange.getPassWordToXEMD5String(Integer
					.toString(random)));// md5로 암호화 해서
			// 임시 비번을 저장,toString()메서드가 정수형 숫자를 문자열로 바꿈.

			this.memberService.edit_pm(rm);// 임시비번 수정

			m.addAttribute("dm", dm);
			m.addAttribute("random", random);// 임시 비번 저장
			return "member/pwd_find";
		} else {
			out.println("<script>");
			out.println("alert('회원으로 검색되지 않습니다!')");
			out.println("history.back()");
			out.println("</script>");
		}
		return null;
	}

	/* 로그인 인증 */
	@RequestMapping(value = "/member_login_ok.do")
	public String member_login_ok(@RequestParam("login_id") String login_id,
			@RequestParam("login_pwd") String login_pwd,
			HttpServletResponse response, @ModelAttribute MemberBean db_id,
			HttpSession session, HttpServletRequest request) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		session = request.getSession();

		db_id = this.memberService.loginCheck(login_id);
		// 로그인 인증
		if (db_id == null) {
			out.println("<script>");
			out.println("alert('가입 안된 회원입니다!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			String pwd = PwdChange.getPassWordToXEMD5String(login_pwd);
			// 입력비번을 MD5로 암호화
			if (!db_id.getJoin_pwd().equals(pwd)) {
				out.println("<script>");
				out.println("alert('비번이 다릅니다!')");
				out.println("history.go(-1)");
				out.println("</script>");
			} else {
				session.setAttribute("id", login_id);
				System.out.println("go to index.do!");
				return "redirect:/index.do";
			}
		}
		return null;
	}

	/* 메인 화면 */
	@RequestMapping(value = "/index.do")
	public String index(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		System.out.println("indexo.do!");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		session = request.getSession();

		String id = (String) session.getAttribute("id");
		if (id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 해주세요!')");
			out.println("location='member_login.do'");
			out.println("</script>");
		} else {
			return "index";
		}
		return null;
	}

	/* 로그아웃 */
	@RequestMapping(value = "/member_logout.do")
	public String member_logout(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		System.out.println("indexo.do!");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		session = request.getSession();

		session.invalidate();// 세션 종료

		out.println("<script>");
		out.println("alert('로그아웃 되었습니다!')");
		out.println("location='member_login.do'");
		out.println("</script>");

		return null;
	}

	/* 정보수정 폼 */
	@RequestMapping(value = "/member_edit.do")
	public String member_eidt(HttpServletResponse response,
			HttpSession session, HttpServletRequest request, Model j)
			throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		session = request.getSession();

		String id = (String) session.getAttribute("id");
		if (id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 해주세요!')");
			out.println("lcation='member_login.do'");
			out.println("</script>");
		} else {
			String[] phone = { "010", "011", "016", "018", "019" };
			String[] email = { "naver.com", "daum.net", "gmail.com",
					"nate.com", "korea.com", "직접입력" };

			j.addAttribute("phone", phone);
			j.addAttribute("email", email);

			MemberBean dm = this.memberService.idcheck(id);
			j.addAttribute("em", dm);
			return "member/member_edit";
		}
		return null;
	}

	/* 정보수정 완료 */
	@RequestMapping(value = "/member_edit_ok.do")
	public String member_edit_ok(@ModelAttribute MemberBean eb,
			HttpServletResponse response, HttpServletRequest request,
			HttpSession session) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		session = request.getSession();

		String id = (String) session.getAttribute("id");
		if (id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 해주세요!')");
			out.println("lcation='member_login.do'");
			out.println("</script>");
		} else {
			eb.setJoin_id(id);
			String pwd = PwdChange.getPassWordToXEMD5String(eb.getJoin_pwd());
			// md5로 암호화
			eb.setJoin_pwd(pwd);// 암호화 된 패스워드 저장
			this.memberService.editMember(eb);// 수정메서드 호출

			out.println("<script>");
			out.println("alert('수정 되었습니다!')");
			out.println("location='member_edit.do'");
			out.println("</script>");
		}
		return null;
	}

	/* 회원 탈퇴 폼 */
	@RequestMapping(value = "/member_del.do")
	public String member_del(HttpServletResponse response,
			HttpServletRequest request, HttpSession session, Model dm)
			throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = (String) session.getAttribute("id");
		if (id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!')");
			out.println("location='member_login.do'");
			out.println("</script>");
		} else {
			MemberBean name = this.memberService.idcheck(id);
			dm.addAttribute("name", name.getJoin_name());
			return "member/member_del";

		}
		return null;
	}

	/* 회원탈퇴 */
	@RequestMapping(value = "/member_del_ok.do")
	public String member_del_ok(@RequestParam("del_pwd") String del_pwd,
			@RequestParam("del_cont") String del_cont,
			@ModelAttribute MemberBean db_pwd, HttpServletResponse response,
			HttpServletRequest request, HttpSession session) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		session = request.getSession();

		String id = (String) session.getAttribute("id");
		if (id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!')");
			out.println("location='member_login.do'");
			out.println("</script>");
		} else {
			db_pwd = this.memberService.idcheck(id);
			String pwd = PwdChange.getPassWordToXEMD5String(del_pwd);
			if (!db_pwd.getJoin_pwd().equals(pwd)) {
				out.println("<script>");
				out.println("alert('비번이 다릅니다!')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				MemberBean dm = new MemberBean();
				dm.setJoin_id(id);
				dm.setJoin_delcont(del_cont);

				this.memberService.delMember(dm);// 회원탈퇴

				session.invalidate();// 세션만료

				out.println("<script>");
				out.println("alert('회원 탈퇴 했습니다!')");
				out.println("location='member_login.do'");
				out.println("</script>");
			}
		}
		return null;
	}

}
