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
	    //���� ������ �ִ� ��� �Ӽ������� �����ϴ� �޼ҵ�
	    //response.sendRedirect("index.jsp");
		forward = new ActionForward();
		forward.setPath("index2.jsp");
		forward.setRedirect(true);
		
		return forward;
	}
}