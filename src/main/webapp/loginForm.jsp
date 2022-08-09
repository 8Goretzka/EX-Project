<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<style>
	#loginformArea {
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
	<section id = "loginformArea">
		<form name = "loginform" action = "./exuserLoginAction.ex" method = "post">
		<table>
			<tr>
				<td colspan="2">
					<h1>로그인 페이지</h1>
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
				<td colspan="2">
					<a href = "javascript:loginform.submit()">로그인</a>&nbsp;&nbsp;
					<a href = "exuserJoin.ex">회원 가입</a>
				</td>
			</tr>
		</table>
		</form>
	</section>
</body>
</html>