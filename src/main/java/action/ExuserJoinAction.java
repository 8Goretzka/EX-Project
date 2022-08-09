package action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ExuserJoinService;
import vo.ActionForward;
import vo.ExuserBean;

public class ExuserJoinAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ExuserBean exuser = new ExuserBean();
		boolean joinResult = false;
		
		exuser.setEXUSER_ID(request.getParameter("EXUSER_ID"));
		exuser.setEXUSER_PASS(request.getParameter("EXUSER_PASS"));
		exuser.setEXUSER_NAME(request.getParameter("EXUSER_NAME"));
		exuser.setEXUSER_AGE(Integer.parseInt(request.getParameter("EXUSER_AGE")));
		exuser.setEXUSER_GENDER(request.getParameter("EXUSER_GENDER"));
		exuser.setEXUSER_EMAIL(request.getParameter("EXUSER_EMAIL"));
		
		ExuserJoinService exuserJoinService = new ExuserJoinService();
		joinResult = exuserJoinService.joinExuser(exuser);
		
		ActionForward forward = null;
		if(joinResult == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원등록실패');");
			out.println("history.back()");
			out.println("</script>");
		}
		else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("index2.jsp");
		}
		return forward;
	}
}
