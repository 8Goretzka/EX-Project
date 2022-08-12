package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.ExuserViewService;
import vo.ActionForward;
import vo.ExuserBean;

public class ExuserViewAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		ActionForward forward = null;
		
		/*if(id==null) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./exuserLogin.ex");
		}
		
		  else if(!id.equals("admin")) {
		  response.setContentType("text/html;charset=UTF-8"); PrintWriter out =
		  response.getWriter(); out.println("<script>");
		  out.println("alert('관리자가 아닙니다.');");
		  out.println("location.href='./exuserLogin.ex';"); out.println("</script>"); }
		 */
		if(id.equals("admin")) {
			forward = new ActionForward(); String viewId =
			request.getParameter("id"); ExuserViewService exuserViewService = new
			ExuserViewService(); ExuserBean exuser = exuserViewService.getExuser(viewId);
			request.setAttribute("exuser", exuser); forward.setPath("./exuser_info.jsp");
		}
		/*
		 * else { forward = new ActionForward(); String viewId =
		 * request.getParameter("id"); ExuserViewService exuserViewService = new
		 * ExuserViewService(); ExuserBean exuser = exuserViewService.getExuser(viewId);
		 * request.setAttribute("exuser", exuser); forward.setPath("./exuser_info.jsp");
		 * }
		 */
		else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("index.html");
		}
		return forward;
	}
}
