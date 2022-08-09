<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import = "dao.DogDAO" %>
<%@ page import = "java.sql.Timestamp" %>

<%
	request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="article" class="vo.BoardVO">
   <jsp:setProperty name="article" property="*"/>
</jsp:useBean>
 
<%
 	article.setReg_date(new Timestamp(System.currentTimeMillis()) );
  //System.currentTimeMillis() : 1970년 1월 1일 자정부터 현재까지의 시간을 밀리세컨드 단위로 반환
  	

      DogDAO dbPro = DogDAO.getInstance();
      dbPro.insertArticle(article);

      response.sendRedirect("list.jsp");
 %>
