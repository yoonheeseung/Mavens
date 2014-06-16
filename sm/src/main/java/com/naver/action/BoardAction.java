package com.naver.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.naver.dao.BoardDAO;
import com.naver.model.BoardBean;

@Controller
public class BoardAction {

	private BoardDAO boardService;

	public void setBoardService(BoardDAO boardService) {
		this.boardService = boardService;
	}//스프링 setter DI 의존관계 설정
	
	/* 사용자 게시판 글쓰기 폼 */
	@RequestMapping(value="/board_write.do")
	public String board_write(Model wm,
			HttpServletRequest request){
		int page=1;
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		wm.addAttribute("page",page);
		
		return "board/board_write";
	}
	
	/* 게시판 저장 */
	@RequestMapping(value="/board_write_ok.do")
	public String board_write_ok(@ModelAttribute BoardBean b){
		this.boardService.insertBoard(b);//저장메서드
		return "redirect:/board_list.do";
	}
	
	/* 게시판 목록 */
	@RequestMapping(value="/board_list.do")
	public String board_list(Model listM,
			HttpServletRequest request,
			@ModelAttribute BoardBean b) throws Exception{
	
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
				int listcount = this.boardService.getListCount(b);
				
				b.setStartrow((page - 1) * 10 + 1);
				b.setEndrow(b.getStartrow() + limit - 1);

				List<BoardBean> blist = this.boardService.getBoardList(b);
				//검색 전 목록, 검색후 목록

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
		
		return "board/board_list";
	}
	
	/* 내용보기+수정폼+삭제폼+답변글폼 */
	@RequestMapping(value="/board_cont")
	public ModelAndView board_cont(
			@RequestParam("board_no") int board_no,
			@RequestParam("state") String state,
			HttpServletRequest request,
			@ModelAttribute BoardBean b)
		throws Exception{
		int page=1;
		if(request.getParameter("page") !=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		if(state.equals("cont")){
			this.boardService.updateHit(board_no);
		}
		b=this.boardService.getBoardCont(board_no);
		
		String board_cont=b.getBoard_cont().replace("\n", "<br/>");
				ModelAndView bm=new ModelAndView();
				bm.addObject("b",b);
				bm.addObject("board_cont",board_cont);
				bm.addObject("page",page);
		
				if(state.equals("cont")){
					bm.setViewName("./board/board_cont");
				}else if(state.equals("reply")){
					bm.setViewName("./board/board_reply");
				}else if(state.equals("edit")){
					bm.setViewName("./board/board_edit");
				}else if(state.equals("del")){
					bm.setViewName("./board/board_del");
				}
				return bm;
	}
	
	/*관리자 답변 저장 */
	@RequestMapping(value="/board_reply_ok.do")
	public String board_reply_ok(@ModelAttribute BoardBean rb, 
			HttpServletRequest request)
	  throws Exception{
		int page=1;
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		this.boardService.reply(rb);//답변저장
		
		return "redirect:/board_list_do?page="+page;	
	}
	
	 /*게시판 수정*/
    @RequestMapping(value="/board_edit_ok.do")
    public String board_edit_ok(@ModelAttribute BoardBean b,
    		HttpServletRequest request,
    		HttpServletResponse response)
    throws Exception{
    	response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out=response.getWriter();
    	
    	int page=1;
    	if(request.getParameter("page") != null){
         page=Integer.parseInt(request.getParameter("page"));    		
    	}
BoardBean db_pwd=
   this.boardService.getBoardCont(b.getBoard_no());
//디비로 부터 비번을 가져옴
        if(db_pwd.getBoard_pwd().equals(b.getBoard_pwd())){
    	this.boardService.editBoard(b);//수정
    	return "redirect:/board_cont.do?board_no="+
    	b.getBoard_no()+"&page="+page+"&state=cont";
        }else{
        	out.println("<script>");
        	out.println("alert('비번이 다릅니다!')");
        	out.println("history.back()");
        	out.println("</script>");
        }
        return null;
    }
    
    /*게시판 삭제*/
    @RequestMapping(value="/board_del_ok.do")
    public String board_del_ok(
    		@RequestParam("page") int page,
    		@RequestParam("del_pwd") String del_pwd,
    		@ModelAttribute BoardBean b,
    		HttpServletResponse response) throws Exception{
    	response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out=response.getWriter();
    	
    	BoardBean db_pwd=
  			this.boardService.getBoardCont(b.getBoard_no());
    	//디비로 부터 비번을 가져옴.
    	if(!db_pwd.getBoard_pwd().equals(del_pwd)){
    		out.println("<script>");
    		out.println("alert('비번이 다릅니다!')");
    		out.println("history.back()");
    		out.println("</script>");
    	}else{
    		this.boardService.deleteBoard(b.getBoard_no());
    		//삭제
    		return "redirect:/board_list.do?page="+page;
    	}
    	return null;
    }
    
    
}
