<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정</title>
</head>
<body>
	<h1>글쓰기</h1>
	<div>
		<form action="/board/mod" method="post">
			<!-- /board/write -->
			<div>
			글번호 : <input type ="hidden" name ="iboard" value= "${data.iboard}" readonly> <!-- readonly쓰면 수정안됨, 어차피 숨겨서 여기선 있으나마나 -->
				<input type="text" name="title" placeholder="제목" value = "${data.title}">
			</div>
			<div>
				<textarea name="ctnt" placeholder="내용" >${data.ctnt}</textarea>
			</div>
			<!--  <input type ="hidden" name ="iuser" value="${loginUser.iuser}">
			iuser값을 html에서 받아오면 안됨 -->
			<div>
				<input type="submit" value="수정"> <input type="reset"
					value="초기화">
			</div>
		</form>
	</div>
</body>
</html>