package action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.ExuserDeleteService;
import vo.ActionForward;

public class ExuserDeleteAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id =(String)session.getAttribute("id");
		ActionForward forward = null;
		
		if(id==null) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./exuserLogin.ex");
		}
		else if(!id.equals("admin")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자가 아닙니다.');");
			out.println("location.href='./exuserLogin.ex';");
			out.println("</script>");
		}
		else {
			String deleteId = request.getParameter("id");
			ExuserDeleteService exuserDeleteService = new ExuserDeleteService();
			boolean deleteResult = exuserDeleteService.deleteExuser(deleteId);
			
			if(deleteResult) {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("./exuserListAction.ex");
			}
			else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('회원정보삭제 실패.');");
				out.println("location.href='./exuserListAction.ex';");
				out.println("</script>");
			}
		}
		return forward;
	}
}
