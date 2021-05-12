<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글제목</title>
</head>
<body>
	<h1>디테일 페이지</h1>
	<!-- 글의 pk (iboard) 값 찍히도록 하기 -->
	<div>${param.iboard}</div>

	<div>글번호 : ${data.iboard}</div>
	<div>제목 : ${data.title}</div>
	<div>글쓴이 : ${data.iuser}</div>
	<div>작성일시 : ${data.regdt}</div>
	<div>${data.ctnt}</div>
	<c:if test="${loginSuccess.iuser == data.iuser}">
	<div>
		<a href="del?iboard=${param.iboard}">삭제</a>  <!-- ${data.iboard} -->
		<a href="mod?iboard=${param.iboard}">수정</a>
	</div>
	</c:if>

</body>
</html>