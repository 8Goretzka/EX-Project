package exproject.common.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exproject.common.service.MemberZipSearchService;
import exproject.common.vo.ActionForward;
import exproject.common.vo.ZipCodeVO;

public class MemberzipSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String ro = request.getParameter("ro");
		MemberZipSearchService memberZipSearchService = new MemberZipSearchService();
		
		ArrayList<ZipCodeVO> zipCodeList = memberZipSearchService.searchZipcodeList(ro);
		request.setAttribute("zipCodeList", zipCodeList);
		ActionForward forward = new ActionForward();
		forward.setUrl("zipSearch.jsp");
		
		
		return forward;
	}

}
