package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vo.ActionForward;

public class ExuserLogoutAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		HttpSession session =  request.getSession();
	    session.invalidate(); 
	    //세션 영역에 있는 모든 속성값들을 제거하는 메소드
	    //response.sendRedirect("index.jsp");
		forward = new ActionForward();
		forward.setPath("index2.jsp");
		forward.setRedirect(true);
		
		return forward;
	}
}