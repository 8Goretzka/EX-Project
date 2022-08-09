package action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.ExuserListService;
import vo.ActionForward;
import vo.ExuserBean;


public class ExuserListAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		ActionForward forward = null;
		/*if(id==null) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./exuserLogin.ex");
		}*/
		if(id.equals("admin")) {
			forward = new ActionForward(); ExuserListService exuserListService =new ExuserListService();
			ArrayList<ExuserBean> exuserList = exuserListService.getExuserList(); 
			request.setAttribute("exuserList",exuserList); 
			forward.setPath("./exuser_list.jsp"); 
		}
		
		/*
		 * else if(!id.equals("admin")) {
		 * response.setContentType("text/html;charset=UTF-8"); PrintWriter out =
		 * response.getWriter(); out.println("<script>");
		 * out.println("alert('관리자가 아닙니다.');");
		 * out.println("location.href='./exuserLogin.ex';"); out.println("</script>"); }
		 */
		else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("index.html");
		}
		/*
		 * else { forward = new ActionForward(); ExuserListService exuserListService =
		 * new ExuserListService(); ArrayList<ExuserBean> exuserList =
		 * exuserListService.getExuserList(); request.setAttribute("exuserList",
		 * exuserList); forward.setPath("./exuser_list.jsp"); }
		 */
		 
		return forward;
	}
}
