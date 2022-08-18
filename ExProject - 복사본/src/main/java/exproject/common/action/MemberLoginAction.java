package exproject.common.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exproject.common.service.MemberLoginService;
import exproject.common.vo.ActionForward;

//Action 클래스 까지는 컨트롤러에 속함

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String id = request.getParameter("memberId");
		String passwd = request.getParameter("memberPassword");
		
		
		
		MemberLoginService memberLoginService = new MemberLoginService();
		
		boolean loginSuccess = memberLoginService.login(id,passwd);
		
		ActionForward forward = null;
		
		if(loginSuccess) {
			HttpSession session = request.getSession();
			session.setAttribute("loginId",id);
			forward = new ActionForward();
			forward.setUrl("index2.jsp");
			forward.setRedirect(true);
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
