<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 시스템 관리자 모드(회원정보보기)</title>
<style>
	#exuserInfoArea {
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
	<section id = "exuserInfoArea">
	<table>
		<tr>
			<td>아이디 : </td>
			<td>${exuser.EXUSER_ID}</td>
		</tr>
		<tr>
			<td>비밀번호 : </td>
			<td>${exuser.EXUSER_PASS}</td>
		</tr>
		<tr>
			<td>이름 : </td>
			<td>${exuser.EXUSER_NAME}</td>
		</tr>
		<tr>
			<td>나이 : </td>
			<td>${exuser.EXUSER_AGE}</td>
		</tr>
		<tr>
			<td>성별 : </td>
			<td>${exuser.EXUSER_GENDER}</td>
		</tr>
		<tr>
			<td>이메일 주소 : </td>
			<td>${exuser.EXUSER_EMAIL}</td>
		</tr>
		<tr>
			<td colspan=2>
				<a href ="exuserListAction.ex">리스트로 돌아가기</a>
			</td>
		</tr>
	</table>
	</section>
</body>
</html>