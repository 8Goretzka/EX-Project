<%@page import="java.sql.Timestamp"%>
<%@page import="bo.vo.PageVO"%>
<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ page import = "bo.vo.BoardVO" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ include file="/view/color.jsp"%>
<%@ taglib  prefix = "c" 
uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix = "fmt" 
uri = "http://java.sun.com/jsp/jstl/fmt"%>

<%!
    SimpleDateFormat sdf = 
        new SimpleDateFormat("yyyy-MM-dd HH:mm");
    //2021-01-12 09:17%>

<%
	/* List<BoardVO> articleList = 
	(List<BoardVO>)request.getAttribute("articleList");
	PageVO pageVO = (PageVO)request.getAttribute("pageVO");
	int count = pageVO.getCount();
	int currentPage = pageVO.getCurrentPage();
	int startPage = pageVO.getStartPage();
	int number = pageVO.getNumber();
	int endPage = pageVO.getEndPage();
	int pageCount = pageVO.getPageCount(); 
	*/
%>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body bgcolor="<%=bodyback_c%>">
<center><b>글목록(전체 글:${pageVO.count})</b>
<table width="700">
<tr>
    <td align="right" bgcolor="<%=value_c%>">
    <a href="boardWriteForm.bo">글쓰기</a>
    </td>
</table>

<%-- <%
	if (count == 0) {
%> --%>
<c:if test="${pageVO.count == 0 }">
<table width="700" border="1" cellpadding="0" cellspacing="0">
<tr>
    <td align="center">
    게시판에 저장된 글이 없습니다.
    </td>
</table>
</c:if>
<%-- <%
	} else {
%> --%>
<c:if test="${pageVO.count != 0 }">
<table border="1" width="700" cellpadding="0" cellspacing="0" align="center"> 
    <tr height="30" bgcolor="<%=value_c%>"> 
      <td align="center"  width="50"  >번 호</td> 
      <td align="center"  width="250" >제   목</td> 
      <td align="center"  width="100" >작성자</td>
      <td align="center"  width="150" >작성일</td> 
      <td align="center"  width="50" >조 회</td>    
    </tr>
<%-- <%
	for (int i = 0 ; i < articleList.size() ; i++) {
          BoardVO article = (BoardVO)articleList.get(i);
%> --%>
	<c:set var = "number" value = "${pageVO.number }"></c:set>
	<c:forEach var = "article" items = "${articleList }">
   <tr height="30">
    <td align="center"  width="50" > ${number }
    <c:set var = "number" value = "${number - 1 }"></c:set>
    </td>
    
    <td  width="250" >
	<%-- <%
	      int wid=0; //공백 이미지의 픽셀값
	      if(article.getRe_level()>0){
	        wid=5*(article.getRe_level());
	%> --%>
	<c:set var = "wid" value = "${0}"></c:set>
	<c:if test="${article.re_level > 0 }">
	<c:set var = "wid" value = "${article.re_level * 5}"></c:set>
	  <img src="board/images/level.gif" width="${wid }" height="16">
	  <img src="board/images/re.gif">
	</c:if>
	<%-- <%}else{%> --%>
	<c:if test="${article.re_level == 0 }">
	  <img src="board/images/level.gif" width="${wid }" height="16">
	<%-- <%}%> --%>
	</c:if>
           
      <a href="boardContent.bo?num=${article.num }&pageNum=${pageVO.currentPage}">
           ${article.subject }</a> 
          <%-- <% if(article.getReadcount()>=20){%> --%>
          <c:if test = "${article.readcount >= 20 }">
         <img src="images/hot.gif" border="0"  height="16">
         </c:if>
         <%-- <%}%>  --%>
         </td>
    <td align="center"  width="100"> 
       <a href="mailto:${article.email }">
       ${article.writer }</a></td>
    <td align="center"  width="150">
    
    <fmt:formatDate var="formDate" value = "${article.reg_date }" pattern="yyyy.MM.dd" />
    <c:out value="${formDate }"></c:out>
    </td>
    <td align="center"  width="50">${article.readcount }
    </td>
  </tr>
  </c:forEach>
     <%-- <%}%> --%>
</table>
<%-- <%}%> --%>
</c:if>

<%
    //if (count > 0) {
        
        //if (startPage > 10) { //첫번째 페이지 그룹이 아니면...  이전그룹의 startPage로 이동
%>
      	<c:if test="${pageVO.count > 0 }">
      	<c:if test="${pageVO.startPage > 10 }">
        <a href="boardList.bo?pageNum=${pageVO.startPage - 10 }">[이전]</a>
        </c:if>
        
        <c:forEach begin="${pageVO.startPage }" end="${pageVO.endPage }" var = "i">
<%      //}
        //for (int i = startPage ; i <= endPage ; i++) { 
        %>
        <a href="boardList.bo?pageNum=${i }">[${i }]</a>
        
        </c:forEach>
<%
        //}
        //if (endPage < pageCount) { //현재 페이지그룹이 마지막 페이지 그룹이 아닐때 다음 페이지그룹의 startPage로 이동 
        %>
        
        <c:if test="${pageVO.endPage < pageVO.pageCount }">
        <a href="boardList.bo?pageNum=${startPage + 10 }">[다음]</a>
        
        </c:if>
<%
        //}
    //}
%>
</c:if>
</center>
</body>
</html>