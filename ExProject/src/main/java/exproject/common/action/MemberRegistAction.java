package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberLoginService;
import service.MemberRegistService;
import vo.ActionForward;
import vo.MemberVO;

public class MemberRegistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("memberId");
		String name = request.getParameter("memberName");
		int age = Integer.parseInt(request.getParameter("memberAge"));
		String email = request.getParameter("memberEmail");
		String addr1 = request.getParameter("memberAddr1");
		String addr2 = request.getParameter("memberAddr2");
		String zipCode = request.getParameter("memberZipCode");
		String tel = request.getParameter("memberTel");
		String gender = request.getParameter("memberGender");
		String country = request.getParameter("memberCountry");
		String passwd = request.getParameter("memberPasswd2");
		
		MemberVO newMemberVO = new MemberVO();
		newMemberVO.setMemberId(id);
		newMemberVO.setMemberName(name);
		newMemberVO.setMemberAge(age);
		newMemberVO.setMemberEmail(email);
		newMemberVO.setMemberAddr1(addr1);
		newMemberVO.setMemberAddr2(addr2);
		newMemberVO.setZipCode(zipCode);
		newMemberVO.setMemberTel(tel);
		newMemberVO.setMemberGender(gender);
		newMemberVO.setMemberCountry(country);
		newMemberVO.setMemberPassword(passwd);
		
		MemberRegistService memberRegistService = new MemberRegistService();
		boolean registSuccess = memberRegistService.registMember(newMemberVO);

		ActionForward forward = null;
		if (registSuccess) {
			HttpSession session = request.getSession();
			session.setAttribute("loginId", id);
			forward = new ActionForward();
			forward.setUrl("index.jsp");
			forward.setRedirect(true);
		}
		else {
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
