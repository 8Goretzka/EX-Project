<%@ page import = "vo.ExuserBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 시스템 관리자 모드(회원목록보기)</title>
<style>
	#exuserListArea {
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
	<section id = "exuserListArea">
	<table>
		<tr>
			<td colspan=2><h1>회원 목록</h1></td>
		</tr>
		<c:forEach var = "exuser" items = "${exuserList}">
		<tr>
			<td>
				<a href="exuserViewAction.ex?id=${exuser.EXUSER_ID}">
					${exuser.EXUSER_ID}
				</a>
			</td>
			<td>
				<a href = "exuserDeleteAction.ex?id=${exuser.EXUSER_ID}">삭제</a>
			</td>
		</tr>
		</c:forEach>
	</table>
	</section>
</body>
</html>