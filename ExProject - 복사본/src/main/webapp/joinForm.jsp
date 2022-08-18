<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#joinformArea {
	width : 400px;
	margin : auto;
	border : 1px solid gray;
	}
	table {
		width : 380px;
		margin : auto;
		text-align : center;
	}
</style>
</head>
<body>
	<section id = "joinformArea">
		<form name = "joinform" action = "./exuserJoinAction.ex" method = "post">
		<table>
			<tr>
				<td colspan="2">
					<h1>회원 가입 페이지</h1>
				</td>
			</tr>
			<tr>
				<td><label for = "EXUSER_ID">아이디 : </label></td>
				<td><input type = "text" name = "EXUSER_ID" id = "EXUSER_ID"/></td>
			</tr>
			<tr>
				<td><label for = "EXUSER_PASS">비밀번호 : </label></td>
				<td><input type = "password" name = "EXUSER_PASS" id = "EXUSER_PASS"/></td>
			</tr>
			<tr>
				<td><label for = "EXUSER_NAME">이름 : </label></td>
				<td><input type = "text" name = "EXUSER_NAME" id = "EXUSER_NAME"/></td>
			</tr>
			<tr>
				<td><label for = "EXUSER_AGE">나이 : </label></td>
				<td><input type = "text" name = "EXUSER_AGE" maxlength = "2" id = "EXUSER_AGE"/></td>
			</tr>
			<tr>
				<td><label for = "EXUSER_GENDER">성별 : </label></td>
				<td>
					<input type = "radio" name = "EXUSER_GENDER" value = "남"
					checked = "checked" id = "EXUSER_GENDER"/>남자 
					<input type = "radio" name = "EXUSER_GENDER" value = "여"/>여자
				</td>
			</tr>
			<tr>
				<td><label for = "EXUSER_EMAIL">이메일 주소 : </label></td>
				<td><input type = "text" name = "EXUSER_EMAIL" id = "EXUSER_EMAIL"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<a href = "javascript:joinform.submit()">회원 가입</a>&nbsp;&nbsp;
					<a href = "javascript:joinform.reset()">다시 작성</a>
				</td>
			</tr>
		</table>
		</form>
	</section>
</body>
</html>