package com.board.dao;

import java.util.List;

import com.board.domain.BoardVO;

public interface BoardDAO {
 
//게시물 목록
 public List<BoardVO> list() throws Exception; 

//게시물 작성
 public void write(BoardVO vo) throws Exception; 

 // 인터페이스DAO나 Service에 메서드를 추가하면 구현체인 DAOImpl에서 에러가 발생,
 // 구현체인 BoardDAOImpl에는 인터페이스인 BoardDAO와 동일한 메서드가 존재해야하기 때문.
}