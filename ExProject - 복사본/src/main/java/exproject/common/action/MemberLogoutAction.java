package exproject.common.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import exproject.common.vo.ActionForward;

public class MemberLogoutAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		HttpSession session =  request.getSession();
	    session.invalidate();
	    //�ش� ���� ����
	    
	    //response.sendRedirect("index.jsp");
		forward = new ActionForward();
		forward.setUrl("index2.jsp");
		forward.setRedirect(true);
		
		return forward;
	}
}
