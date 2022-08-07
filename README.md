# pp.board 

### 스프링게시판 만들기
### MariaDB -게시판 테이블

## 테이블 생성
  create table tbl_board(
  bno int not null auto_increment,
  title varchar(50) not null,
  content text not null,
  writer varchar(30) not null,
  regDate timestamp not null default now(),
  viewCnt int default 0,
  primary key(bno)
  );

  -- bno : 각 게시물을 구분할 고유 번호
  -- title : 게시물의 제목
  -- content : 게시물 내용
  -- writer : 작성자
  -- regDate : 작성일
  -- viewCnt : 조회수

## 데이터 임시 삽입
  insert into tbl_board(title, content, writer)
  values('테스트 제목1', '테스트 내용', '작성자');

  insert into tbl_board(title, content, writer)
  values('테스트 제목2', '테스트 내용', '작성자');

  insert into tbl_board(title, content, writer)
  values('테스트 제목3', '테스트 내용', '작성자');

  insert into tbl_board(title, content, writer)
  values('테스트 제목4', '테스트 내용', '작성자');

  insert into tbl_board(title, content, writer) 
  values('테스트 제목5', '테스트 내용', '작성자');

## 페이징

## 인서트 셀렉트 문(현재 테이블의 모든 데이터를 재입력:복사) 페이징 테스트

  insert into tbl_board(title, content, writer)
  select title, content, writer from tbl_board
  
## 번호 순차정렬

  select 
  bno, title, writer, regDate, viewCnt
  from tbl_board
  order by bno DESC;

## limit 10을 추가하여 작성한 쿼리의 결과에서 10개만 출력할수 있게함

  select 
  bno, title, writer, regDate, viewCnt
  from tbl_board
  order by bno desc
  limit 10;
 
## limit 0,10 으로 수정시 결과중 0번째를 시작으로 10개가 출력 

  select 
  bno, title, writer, regDate, viewCnt
  from tbl_board
  order by bno desc
  limit 0, 10;

  -- 첫페이지는 0번째부터 10개이므로 limit 0,10
  -- 두페이지는 11번째부터 10개이므로 limit 10,10
  -- 세페이지는 21번째부터 10개이므로 limit 20,10

## 게시물의 총 갯수

  select count(bno) from tbl_board;

## 검색기능 쿼리 
  -- 제목+내용 검색

  SELECT 
  bno, title, content, writer, regDate, viewCnt
  FROM tbl_board
  WHERE title LIKE '%테스트%'
  OR content LIKE '%test%' ;

## 댓글기능 테이블 쿼리
  CREATE TABLE tbl_reply (
  rno         int             not null auto_increment,
  bno         int             not null,
  writer     varchar(30) not null,
  content     text             not null,
  regDate     timestamp     not null default now(),
  PRIMARY KEY(rno, bno),
  FOREIGN KEY(bno) REFERENCES tbl_board(bno)
  );

  -- rno : 댓글 고유번호
  -- bno : 댓글이 작성된 게시물의 번호
  -- 게시물에 여러개의 댓글이 달릴 수 있으므로 rno,bno 2개의 컬럼에 pk설정
 
## 댓글 테이블 데이터 테스트 (현재 게시물의 번호가 실존해야함)
  INSERT INTO tbl_reply(bno,writer,content,regDate)
  VALUES(823, '댓글작성자','댓글내용',SYSDATE());

  UPDATE tbl_reply SET
  writer = '댓글 작성자_수정',
  content = '댓글 내용_수정'
  WHERE rno = 1 
  AND bno = 823;

  DELETE FROM tbl_reply 
  WHERE rno = 1
  AND bno = 823;

## 고유번호(시퀀스) 초기화
  ALTER TABLE tbl_board AUTO_INCREMENT=1;
