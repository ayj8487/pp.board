package com.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.domain.BoardVO;
import com.board.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Inject
	private BoardService service;
	
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
	
	// 게시물 작성
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String posttWirte(BoardVO vo) throws Exception {
	  service.write(vo);
	  
	  return "redirect:/board/list";
	}
}
