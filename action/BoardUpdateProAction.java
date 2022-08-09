package bo.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.service.BoardUpdateProService;
import bo.service.BoardWriteProService;
import bo.vo.ActionForward;
import bo.vo.BoardVO;

public class BoardUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		BoardVO article = new BoardVO();
		article.setContent(request.getParameter("content"));
		article.setEmail(request.getParameter("email"));
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setPasswd(request.getParameter("passwd"));
		article.setReadcount(0);
		article.setReg_date(new Timestamp(System.currentTimeMillis()));
		article.setSubject(request.getParameter("subject"));
		article.setWriter(request.getParameter("writer"));
		
		BoardUpdateProService boardUpdateProService
		= new BoardUpdateProService();
		
		boolean modifySuccess = boardUpdateProService.modifyArticle(article);
		
		ActionForward forward = null;
		
		if(modifySuccess) {
			forward = new ActionForward();
			forward.setUrl("boardList.bo?pageNum=" + request.getParameter("pageNum"));
			forward.setRedirect(true);
		}
		else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정실패')");
			out.println("history.back();");
			out.println("</script>");
		}
		return forward;
	}

}
