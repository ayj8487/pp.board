package com.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardVO;
import com.board.domain.Page;
import com.board.domain.ReplyVO;
import com.board.service.BoardService;
import com.board.service.ReplyService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Inject
	private BoardService service;
	
	@Inject
	private ReplyService replyService;
	
	// get메서드 서버 => 사용자
	// post메서드 사용자 => 서버 (게시물작성)
// 게시물작성 => controller(데이터를받음) => service => DAO => 데이터베이스(DB)
	
	//게시물 목록 출력
	@RequestMapping(value = "/list", method =RequestMethod.GET)
	public void getList(Model model) throws Exception {
		
		List<BoardVO> list = null;
		list = service.list();
		
		// Model은 (Controller)컨트롤러와 (View)뷰 를 연결해주는 역할
		model.addAttribute("list", list);
	}
	
	//게시물 작성페이지 열기
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void getWirte() throws Exception {
	   
	}
	
	//게시물 작성
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String posttWirte(BoardVO vo) throws Exception {
	  service.write(vo);
	  
	  return "redirect:/board/list";
	}
	
	//게시물 조회
	
	// RequestParam(문자열)을 이용해 주소에 있는 특정값(bno)을 찾아 int bno에 넣어줌 
	
	//BoardVO를 이용하여 서비스(service)에서 데이터를 받고, 
	//모델(model)을 이용하여 뷰(view)에 데이터를 넘겨줌. 
	//이때, 넘겨주는 모델의 이름은 view이다.
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void getView(@RequestParam("bno") int bno, Model model) throws Exception{
		
		BoardVO vo = service.view(bno);
		
		model.addAttribute("view", vo);
		
		// 댓글 조회 추가
		List<ReplyVO> reply = null;
		reply = replyService.list(bno);
		model.addAttribute("reply", reply);
	}
	//게시물 수정 - 수정페이지에 기존게시물 보여주기
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void getModify(@RequestParam("bno") int bno, Model model) throws Exception{
		
		BoardVO vo = service.view(bno);
		
		model.addAttribute("view", vo);
	}
	// 게시물 수정
	
	//service.bodify(vo)에서 뷰에서 컨트롤러로 넘어온 데이터(BoardVO)
	//를 이용해 수정을 끝내고 현재 bno에 해당되는 조회페이지로 이동
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String postModify(BoardVO vo) throws Exception {

		 service.modify(vo);
		   
		 return "redirect:/board/view?bno=" + vo.getBno();
	}
	//게시물 삭제
	@RequestMapping(value = "/delete" , method = RequestMethod.GET)
	public String getDelete(@RequestParam("bno") int bno) throws Exception {
		
		service.delete(bno);
		
		return "redirect:/board/list";
	}
	// 게시물 목록 + 페이징 추가 
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void getListPage(Model model, @RequestParam("num") int num) throws Exception {

		Page page = new Page();
		
		page.setNum(num);
		page.setCount(service.count());  

		List<BoardVO> list = null; 
		list = service.listPage(page.getDisplayPost(), page.getPostNum());

		model.addAttribute("list", list);   
		
		model.addAttribute("page", page);

		model.addAttribute("select", num);
		
/* 페이징 코드를 Page클래스로 분리해서 주석처리 
 
	 // 1. 게시물 총 갯수
	 int count = service.count();
	  
	 // 2. 한 페이지에 출력할 게시물 갯수
	 int postNum = 10;
	  
	 // 3. 하단 페이징 번호 ([ 게시물 총 갯수 ÷ 한 페이지에 출력할 갯수 ]의 올림)
	 //    하단에 표시할 페이징 번호의 갯수를 구함(소수점은 올림)
	 int pageNum = (int)Math.ceil((double)count/postNum);
	  
	 // 4. 현재 페이지를 기준으로 10개의 데이터를 출력
	 int displayPost = (num - 1) * postNum;
	 
	 // 1. 페이징 갯수 (한번에 표시할 페이징 번호의 갯수)
	 int pageNum_cnt = 10;

	 // 2. 표시되는 페이지 번호 중 마지막 번호
	 int endPageNum = (int)(Math.ceil((double)num / (double)pageNum_cnt) * pageNum_cnt);
	 
//	 마지막 페이지 번호 = 
// 	((올림)(현재 페이지 번호 / 한번에 표시할 페이지 번호의 갯수)) * 
//	 한번에 표시할 페이지 번호의 갯수 

	 // 3. 표시되는 페이지 번호 중 첫번째 번호
	 int startPageNum = endPageNum - (pageNum_cnt - 1);

//	 시작 페이지 = 마지막 페이지 번호 - 한번에 표시할 페이지 번호의 갯수 + 1	 
	 
	 // 4. 마지막 번호 재계산 (마지막 번호는 한번더 계산할 필요가 있다)
	 int endPageNum_tmp = (int)(Math.ceil((double)count / (double)pageNum_cnt));
	  
	 if(endPageNum > endPageNum_tmp) {
	  endPageNum = endPageNum_tmp;
	 }
	 boolean prev = startPageNum == 1 ? false : true;
	 boolean next = endPageNum * pageNum_cnt >= count ? false : true;
	 
	 List<BoardVO> list = null; 
	 list = service.listPage(displayPost, postNum);
	 model.addAttribute("list", list);   
	 model.addAttribute("pageNum", pageNum);
	
	 // 시작 및 끝 번호
	 model.addAttribute("startPageNum", startPageNum);
	 model.addAttribute("endPageNum", endPageNum);

	 // 이전 및 다음 
	 model.addAttribute("prev", prev);
	 model.addAttribute("next", next);
	 
	 // 현재 페이지표시
	 model.addAttribute("select", num);

까지 */	

	}	
	
	// 게시물 목록 + 페이징 추가 + 검색
	@RequestMapping(value = "/listPageSearch", method = RequestMethod.GET)
	public void getListPageSearch(Model model, @RequestParam("num") int num, 
	     @RequestParam(value = "searchType",required = false, defaultValue = "title") String searchType,
		 @RequestParam(value = "keyword",required = false, defaultValue = "") String keyword
	  ) throws Exception {
		
	// 매게변수부에 @RequestParam("searchType")~~ 를통해
	// url 을 통해 searchType과 keyword를 받아낼 수 있도록 함, value	
	 Page page = new Page();
	 
	 page.setNum(num);
	 
	 //-- 검색 -- 검색적용후 게시물갯수 추가 후 주석 
	 //page.setCount(service.count());  
	 page.setCount(service.searchCount(searchType, keyword));
	 
	 // 검색 타입과 검색어
	 // page.setSearchTypeKeyword(searchType, keyword);
	 page.setSearchType(searchType);
	 page.setKeyword(keyword);
	 //--까지-- 
	 
	 List<BoardVO> list = null; 
	 //list = service.listPage(page.getDisplayPost(), page.getPostNum());
	 list = service.listPageSearch(page.getDisplayPost(), page.getPostNum(), searchType, keyword);
	 
	 model.addAttribute("list", list);
	 model.addAttribute("page", page);
	 model.addAttribute("select", num);
	 
	 //검색후 검색조건과 검색어를 유지, page클래스로 코드 분리 후 주석
	 // model.addAttribute("searchType", searchType);
	 // model.addAttribute("keyword", keyword);
	}
}
