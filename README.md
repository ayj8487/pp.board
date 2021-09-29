# pp.board

스프링게시판 만들기
MariaDB -게시판 테이블

 create table tbl_board(
  bno int not null auto_increment,
  title varchar(50) not null,
  content text not null,
  writer varchar(30) not null,
  regDate timestamp not null default now(),
  viewCnt int default 0,
  primary key(bno)
);

bno : 각 게시물을 구분할 고유 번호
title : 게시물의 제목
content : 게시물 내용
writer : 작성자
regDate : 작성일
viewCnt : 조회수
