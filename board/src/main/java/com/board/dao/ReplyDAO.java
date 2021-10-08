package com.board.dao;

import java.util.List;

import com.board.domain.ReplyVO;

// 하위의 dao들을 묶어놓기 위해 패키지명은 
// com.reply.dao 가 아닌 com.board.dao 가 맞다
public interface ReplyDAO {

	// 댓글 조회
	public List<ReplyVO> list(int bno) throws Exception;

	// 댓글 조회
	public void write(ReplyVO vo) throws Exception;

	// 댓글 수정
	public void modify(ReplyVO vo) throws Exception;

	// 댓글 삭제
	public void delete(ReplyVO vo) throws Exception;
}
