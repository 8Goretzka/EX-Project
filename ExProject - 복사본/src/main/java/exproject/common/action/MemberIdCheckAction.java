package exproject.common.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exproject.common.service.MemberIdCheckService;
import exproject.common.vo.ActionForward;

public class MemberIdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String memberId = request.getParameter("memberId");
		MemberIdCheckService memberIdCheckService = new MemberIdCheckService();
		
		boolean idExist = memberIdCheckService.existId(memberId);
		request.setAttribute("idExist", idExist);
		request.setAttribute("memberId", memberId);
		ActionForward forward = new ActionForward();
		forward.setUrl("idCheck.jsp");
		
		return forward;
	}

}
