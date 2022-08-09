<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import = "bo.dao.DogDAO" %>
<%@ page import = "java.sql.Timestamp" %>

<%
	request.setCharacterEncoding("UTF-8");
%>

<%


  if(check==1){
%>
	  <meta http-equiv="Refresh" content="0;url=list.jsp?pageNum=<%=pageNum%>" >
<% }else{%>
       <script language="JavaScript">      
       <!--      
         alert("비밀번호가 맞지 않습니다");
         history.go(-1);
       -->
      </script>
<%
    }
 %>