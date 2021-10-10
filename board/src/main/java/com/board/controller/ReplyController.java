package com.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.domain.ReplyVO;
import com.board.service.ReplyService;

@Controller
@RequestMapping("/reply/*")
public class ReplyController {

	@Inject
	private ReplyService replyService;
	// 1. 게시물 조회 페이지에서 댓글 작성후 등록
	// 2. /reply/write 에 해당되는 컨트롤러오 데이터 전달
	// 3. service => dao => mapper를 통해 db에 전달
	// 4. redirect:/board/view?bno= 로인해 1번에서 조회중이었던 게시물로 이동
	
	
	// 댓글 조회
	
	// 댓글 작성
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String posttWirte(ReplyVO vo) throws Exception {
		
		replyService.write(vo);
		
		return "redirect:/board/view?bno=" + vo.getBno();
	}
	// 댓글 수정
	
	// 댓글 삭제
	
}
