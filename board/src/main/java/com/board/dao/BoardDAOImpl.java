package com.board.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	// BoardDAO를 상속받는 BoardDAOImple

	@Inject
	private SqlSession sql;
	
	//  BoardDAOImpl에 있는 namespace는 매퍼의 namespace와 일치해야함 .
	private static String namespce = "com.board.mappers.board";
	
	// 게시물 목록 

	// 게시물, 즉 tbl_board 1행의 데이터의 형태는 BoardVO와 같다. 
	// 게시물 목록은 tbl_board가 1행 이상 존재하는것이므로, 
	// BoardVO를 리스트(List) 형태로 만들면 게시물 목록을 받아올 수 있다.
	@Override
	public List<BoardVO> list() throws Exception {

		return sql.selectList(namespce + ".list");
	}
	
	//게시물 작성
	@Override
	public void write(BoardVO vo) throws Exception {

		sql.insert(namespce + ".write", vo);
	}

	//게시물 조회
	@Override
	public BoardVO view(int bno) throws Exception {

		return sql.selectOne(namespce + ".view", bno);
	}

	//게시물 수정
	@Override
	public void modify(BoardVO vo) throws Exception {

		sql.update(namespce + ".modify", vo);
	}

	//게시물 삭제
	@Override
	public void delete(int bno) throws Exception {

		sql.delete(namespce + ".delete", bno);
	}

	//게시물 총 갯수
	@Override
	public int count() throws Exception {

		return sql.selectOne(namespce + ".count");
	}

	// 게시물 목록 + 페이징
	@Override
	public List<BoardVO> listPage(int displayPost, int postNum) throws Exception {
		
		 // 매개변수인 displayPost,postNum 를 
		 // 해시맵을 이용하여 하나로 그룹지어준뒤 매퍼에 전송 
		 
		 // ** DAO와 매퍼에서는 데이터를 하나만 전송할 수 있기 때문에 
		 // 2개 이상의 데이터를 다룰 때는 VO(Value Object)를 사용하거나 해시맵을 이용.
		 HashMap data = new HashMap();
		 
		 data.put("displayPost", displayPost);
		 data.put("postNum", postNum);
		  
		 return sql.selectList(namespce + ".listPage", data);
	}
	
	

}
