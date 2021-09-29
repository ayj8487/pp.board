<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성</title>
</head>
<body>

<!-- 입력 엘리먼트인 <input>과 <textarea>의 
이름(name)속성의 값이 BoardVO와 동일해야함. -->

<!-- 프론트단에서 전송하는 데이터 타입을 VO화 시키고, 
백단인 컨트롤러에서도 같은 VO타입으로 받도록 해주면 알아서 데이터가 들어감. -->

<form method="post">

<label>제목</label>
<input type="text" name="title" /><br />

<label>작성자</label>
<input type="text" name="writer" /><br />

<label>내용</label>
<textarea cols="50" rows="5" name="content"></textarea><br />

<button type="submit">작성</button>
<button type="reset">취소</button>

</form>

</body>
</html>