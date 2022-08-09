package action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.ExuserLoginService;
import vo.ActionForward;
import vo.ExuserBean;

public class ExuserLoginAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ExuserBean exuser = new ExuserBean();
		
		exuser.setEXUSER_ID(request.getParameter("EXUSER_ID"));
		exuser.setEXUSER_PASS(request.getParameter("EXUSER_PASS"));
		
		ExuserLoginService exuserLoginService = new ExuserLoginService();
		boolean loginResult = exuserLoginService.login(exuser);
		ActionForward forward = null;
		/*if(loginResult) {
			forward = new ActionForward();
			session.setAttribute("id", exuser.getEXUSER_ID());
			forward.setRedirect(true);
			forward.setPath("./exuserListAction.ex");
		}*/
		if(loginResult) {
			forward = new ActionForward();
			session.setAttribute("id", exuser.getEXUSER_ID());
			forward.setRedirect(true);
			forward.setPath("./exuserListAction.ex");
		}
		
		else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 실패');");
			out.println("location.href='loginForm.html';");
			out.println("</script>");
		}
		return forward;
	}
}
