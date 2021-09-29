<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 목록</title>
</head>
<body>

	<table>
	 <thead>
	  <tr>
	   <th>번호</th>
	   <th>제목</th>
	   <th>작성일</th>
	   <th>작성자</th>
	   <th>조회수</th>
	  </tr>
	 </thead>

 <!-- 반복문을 사용하려면  root-context.xml 에서 -->
 <!-- context:component-scan을 통해 vo, dao, service의 패키지 경로를 잡아줘야한다 -->

	 
	 <tbody>

 
 <c:forEach items="${list}" var="list">
	 <tr>
	  <td>${list.bno}</td>
	  <td>
	   <a href="/board/view?bno=${list.bno}">${list.title}</a>
	  </td>
	  <!-- 주소의 파라미터값을 컨트롤러에 전달  -->
	  <td>${list.regDate}</td>
	  <td>${list.writer}</td>
	  <td>${list.viewCnt}</td>
	 </tr>
</c:forEach>
	  
	 </tbody>
	
	</table>

</body>
</html>