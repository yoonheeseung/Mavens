package com.naver.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.naver.dao.BbsDAO;
import com.naver.model.BbsBean;
import com.oreilly.servlet.MultipartRequest;

@Controller
//@Controller 어노테이션은 implements Controller를 하지 않아도
//쉽게 스프링 컨트롤 클래스를 만들수 있게 한다.
public class BbsAction {

	private BbsDAO bbsService;
	
	public void setBbsService(BbsDAO bbsService) {
		this.bbsService = bbsService;
	}//setter  DI 의존관계 설정
	
	/* 자료실 글쓰기 */
	@RequestMapping(value="/bbs_write.do",method=RequestMethod.GET)
	//get방식으로 접근할때 호출되는 어노테이션
	public String bbs_write(HttpServletRequest request, Model wm){
		int page=1;
		if(request.getParameter("page") !=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		wm.addAttribute("page", page);
		
		return "bbs/bbs_write";
		//jsp/bbs/bbs_write.jsp 뷰페이지가 실행
	}

   /* 자료실 저장 */
	@RequestMapping(value="/bbs_write_ok.do",method=RequestMethod.POST)
	//post방식일때 호출되는 어노테이션
	public String bbs_write_ok(HttpServletRequest request,
			     @ModelAttribute BbsBean b)
	 throws Exception{
		String saveFolder="C:/Users/unisung/git/Mavens/sm/src/main/webapp/upload";//이진파일 업로드 경로
		int fileSize=5*1025*1024;//이진파일 업로드 최대크기,5mb
		MultipartRequest multi=null;//이진파일 업로드 변수선언
		multi=new MultipartRequest(request, saveFolder, fileSize, "utf-8");
		//이진파일 업로드 multi객체 생성
		//생성자 첫번째 전달인자 request는 사용자폼에서 입력한 것을 서버로 전달
		//두번째 전달인자는 이진파일 업로드 서버 경로
		//세번째 전달인자는 이진파일 최대크기
		//네번째 전달인자는 언어코딩 타입
		
		String bbs_name=multi.getParameter("bbs_name").trim();
		String bbs_title=multi.getParameter("bbs_title").trim();
		String bbs_pwd=multi.getParameter("bbs_pwd").trim();
		String bbs_cont=multi.getParameter("bbs_cont").trim();
		
		File UpFile=multi.getFile("bbs_file");//첨부한 이진파일을 가져옴.
		
		if(UpFile !=null){
			String fileName=UpFile.getName();//첨부한 이진파일명을 구함
			Calendar c=Calendar.getInstance();
			String year=String.format("%04d", c.get(Calendar.YEAR));//년도
			String month=String.format("%02d",c.get(Calendar.MONTH)+1);//월
			//0으로 반환되기 때문이다.
			String date=String.format("%02d",c.get(Calendar.DATE));//일
			
		String homedir=saveFolder+"/"+year+"-"+month+"-"+date;
		//새로운 이진파일 폴더 경로를 저장
		File path1=new File(homedir);
		if(!(path1.exists())){//폴더경로가 없으면
			path1.mkdir();//폴더생성
		}
		Random r=new Random();//난수를 발생시키는 클래스
		int random=r.nextInt(100000000);
		//1억사이의 정수형 난수를 발생시킴
		/********** 확장자 구하기 *******************/
		int index=fileName.lastIndexOf(".");
		//File 클래스의 getName()메서드는 이진파일명을 받아온다.
		//lastIndexOf("문자")는 String클래스의 메서드로 해당문자
		//를 문자열 끝 즉 우츠에서 헤아려 문자의 위치번로를 반환한다.
		String fileExtension=fileName.substring(index +1);
		//파일의 확장자를 구한다.
		/*********** 확장자 구하기 끝 *********************/
		String refileName="bbs"+year+month+date+random+"."+fileExtension;//새로운 파일명을 저장
		System.out.println("새로운파일명:"+refileName);
		String fileDBName="/"+year+"-"+month+"-"+date+"/"+refileName;//데이터베이스에 저장될 레코드 값
		System.out.println("데이터베이스파일명:"+fileDBName);
		
		System.out.println(UpFile.renameTo(new File(homedir+"/"+refileName)));
		
		//새롭게 생성된 폴더에 바뀐 이진파일명으로 업로드
		b.setBbs_file(fileDBName);
		}else{//이진파일을 첨부하지 않았을 때
			String fileDBName="";//첨부하지 않은 경우는 빈공백을 저장시킴
			b.setBbs_file(fileDBName);
		}
			
		b.setBbs_name(bbs_name);
		b.setBbs_title(bbs_title);
		b.setBbs_pwd(bbs_pwd);
		b.setBbs_cont(bbs_cont);
		
		this.bbsService.insertBbs(b);//저장메서드 호출
		
		return "redirect:/bbs_list.do";
	}
	
	/* 자료실 목록 */
	@RequestMapping(value="/bbs_list.do")
	public String bbs_list(Model listM,	@ModelAttribute BbsBean b, HttpServletRequest request)
	 throws Exception{
		
		int page=1;
		int limit=10;
		
		String find_field=null;
		String find_name=null;
		
		if(request.getParameter("find_name")!=null){
			find_name=request.getParameter("find_name").trim();
            find_name= new String(find_name.getBytes("ISO-8859-1"),"utf-8");
		}
		find_field=request.getParameter("find_field");
		b.setFind_field(find_field);
		b.setFind_name("%"+find_name+"%");
		
		
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		
		// 총 레코드 개수를 반환
				int listcount = this.bbsService.getListCount(b);
				
				b.setStartrow((page - 1) * 10 + 1);
				b.setEndrow(b.getStartrow() + limit - 1);

				List<BbsBean> blist = this.bbsService.getBbsList(b);
				// 방명록 목록

				// 총 페이지 수.
				int maxpage = (int) ((double) listcount / limit + 0.95); // 0.95를 더해서 올림
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
				listM.addAttribute("find_field",find_field);
				//find_field 키값에 board_title,board_cont저장
				listM.addAttribute("find_name", find_name);
				//find_name 키값에 검색어를 저장
		
		return "bbs/bbs_list";
		//bbs폴더의 bbs_list.jsp 뷰페이지로 이동
	}
	
	/*내용보기+수정폼+삭제폼+답변글폼*/
	@RequestMapping(value="/bbs_cont.do")
	public ModelAndView bbs_cont(HttpServletRequest request,
			@RequestParam("bbs_no") int bbs_no,
			@RequestParam("state") String state)
		throws Exception{
		int page=1;
		if(request.getParameter("page") !=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		if(state.equals("cont")){//내용보기일때 조회수 증가
			this.bbsService.updateHit(bbs_no);
		}
		
		BbsBean b=this.bbsService.getCont(bbs_no);
		//글번호를 기준으로 디비로 부터 내용을 가져옴.
		String bbs_cont=b.getBbs_cont().replace("\n", "<br/>");
		//textarea 박스에서 엔터키 친 부분을 다음 줄로 개행
		
		ModelAndView bm=new ModelAndView();
		bm.addObject("b",b);
		bm.addObject("bbs_cont",bbs_cont);
		bm.addObject("page",page);
		
		if(state.equals("cont")){
			bm.setViewName("./bbs/bbs_cont");
		}else if(state.equals("reply")){//답변글 폼일때
			bm.setViewName("./bbs/bbs_reply");
		}else if(state.equals("edit")){//수정폼
			bm.setViewName("./bbs/bbs_edit");
		}else if(state.equals("del")){//삭제폼
			bm.setViewName("./bbs/bbs_del");
		}
		return bm;
	}

	/* 답변 저장 */
	@RequestMapping(value="bbs_reply_ok.do")
	public String bbs_reply_ok(HttpServletRequest request,
			@ModelAttribute BbsBean b)
			throws Exception{
		
		int page=1;
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		this.bbsService.reply(b);//답변 저장 메서드
		
		return "redirect://bbs_list.do?page="+page;
	}
			
	/* 수정완료 */
	@RequestMapping(value="/bbs_edit_ok.do")
	public String bbs_edit_ok(
			                  HttpServletRequest request, 
			                  @ModelAttribute BbsBean b,
			                  HttpServletResponse response)
	 throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String saveFolder="C:/Users/unisung/git/Mavens/sm/src/main/webapp/upload";//서버 업로드경로 저장
		int fileSize = 5*1024*1024;//이진파일 업로드 최대 크기
		MultipartRequest multi=null;//이진파일 받아올 변수
	    multi=new MultipartRequest(request, saveFolder, fileSize, "utf-8");
	//이진파일 업로드 객체 생성
	//생성자 첫번째 전달인자 사용자폼에서 입력한 정보를 서버로 가져오는 역할
	//두번째 전달인자는 이진파일 업로드 서버 경로
	//세번째 전달인자는 이진파일 업로드 최대크기
	//네번째 전달인자는 언어코딩 타입을 설정
	int bbs_no=Integer.parseInt(multi.getParameter("bbs_no"));
	//히든으로 넘어온 글번호를 정수형 숫자롤 바꿔서 저장
	int page=Integer.parseInt(multi.getParameter("page"));
	
	String bbs_name=multi.getParameter("bbs_name").trim();
	String bbs_title=multi.getParameter("bbs_title").trim();
	String bbs_pwd=multi.getParameter("bbs_pwd").trim();
	String bbs_cont=multi.getParameter("bbs_cont").trim();
 
	BbsBean db_pwd=this.bbsService.getCont(bbs_no);
	//디비로 부터 비번을 가져옴.
	if(!db_pwd.getBbs_pwd().equals(bbs_pwd)){
		out.println("<script>");
		out.println("alert('비번이 다릅니다!)");
		out.println("history.back()");
		out.println("</script>");
	}else{
		File UpFile=multi.getFile("bbs_file");
		//첨부한 이진파일을 가져옴.
		if(UpFile !=null){
			//첨부한 이진파일이 있다면
			String fileName=UpFile.getName();//이진파일명을 저장
			File DelFile=
					 new File(saveFolder+db_pwd.getBbs_file());
			//디비로 부터 가져온 이진파일명 삭제 객체를 생성
			if(DelFile.exists()){
				DelFile.delete();
			}
			Calendar c=Calendar.getInstance();
			String year=String.format("%04d", c.get(Calendar.YEAR));//년도값
			String month=String.format("%02d", c.get(Calendar.MONTH)+1);//월값
			String date=String.format("%02d", c.get(Calendar.DATE));//일값
			
			
			String homedir=saveFolder+"/"+year+"-"+month+"-"+date;
			System.out.println("homedir:"+homedir);
			//새로운 이진파일을 폴더명을 저장
			File path1=new File(homedir);
			if(!(path1.exists())){
				path1.mkdir();//폴더를 생성
			}
			Random r=new Random();
			int random=r.nextInt(100000000);
			//1억사이의 정수형 난수를 발생
			/********** 확장자 구하기 *******************/
			int index=fileName.lastIndexOf(".");
			//lastIndexOf("문자")는 String클래스의 메서드로 해당문자
			//를 문자열 끝 즉 우측에서 헤아려 문자의 위치번로를 반환한다.
			String fileExtension=fileName.substring(index +1);
			//파일의 확장자를 구한다.
			/*********** 확장자 구하기 끝 *********************/
			String refileName="bbs"+year+month+date+random+"."+fileExtension;//새로운 파일명을 저장
			System.out.println("새로운파일명:"+refileName);
			
			String fileDBName="/"+year+"-"+month+"-"+date+"/"+refileName;
			//데이터베이스에 저장될 레코드 값
			UpFile.renameTo(new File(homedir+"/"+refileName));
			//새롭게 생성된 폴더로 바뀐 이진파일명으로 업로드
			b.setBbs_file(fileDBName);
		}else{
			if(db_pwd.getBbs_file() !=null){
				b.setBbs_file(db_pwd.getBbs_file());
				//첨부하지 않은 경우는 기존이진파일명으로 저장.
			}else{
				String bbs_file="";
				b.setBbs_file(bbs_file);
			}
			/*
			 * null을 자바 jdbc에서는 수정 저장 되지만,
			 * ibatis와 mybatis xml에서는 기존 null값을 저장 수정하면
			 * sql예외 오류를 발생 시킨다.
			 * null대신 빈 공백을 저장시키면 null로 저장된다.
			 * 
			 */
		}
		b.setBbs_name(bbs_name);
		b.setBbs_title(bbs_title);
		b.setBbs_cont(bbs_cont);
		b.setBbs_no(bbs_no);
		
		this.bbsService.updateBbs(b);//수정메서드
		System.out.println("수정!");
		return "redirect:/bbs_cont.do?bbs_no="+bbs_no+"&page="+page+"&state=cont";
	     //*.do?bbs_no=번호&page=페이지;   	
	
	}
		return null;
	}
	
	/* 자료실 삭제 */
	@RequestMapping(value="/bbs_del_ok.do")
	public String bbs_del_ok(
			@RequestParam("bbs_no") int bbs_no,
			@RequestParam("del_pwd") String del_pwd,
			HttpServletRequest request,
			HttpServletResponse response)
	   throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		BbsBean db_pwd=this.bbsService.getCont(bbs_no);
		
		int page=1;	
		if(request.getParameter("page")!=null){
		   page=Integer.parseInt(request.getParameter("page"));
		}
		//디비로부터 비번을 가져옴.
		String up="C:/Users/unisung/git/Mavens/sm/src/main/webapp/upload";//이진파일 업로드 서버 경로를 저장
		String fname=db_pwd.getBbs_file();
		if(!db_pwd.getBbs_pwd().equals(del_pwd)){
			out.println("<script>");
			out.println("비번일 다릅니다.");
			out.println("history.go(-1)");
			out.println("</script>");
		}else{
			this.bbsService.del(bbs_no);
			//글번호를 기준으로 삭제
			if(fname!=null){
				File file=new File(up+fname);
				//삭제할 파일객체를 생성
				file.delete();//파일을 삭제
			return "redirect:/bbs_list.do?page="+page;//목록으로 이동
			}
		}
		return null;
	}
	
}


