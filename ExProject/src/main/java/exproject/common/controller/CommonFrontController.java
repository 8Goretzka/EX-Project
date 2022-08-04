package exproject.common.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exproject.common.action.Action;
import exproject.common.action.MemberIdCheckAction;
import exproject.common.action.MemberLoginAction;
import exproject.common.action.MemberLogoutAction;
import exproject.common.action.MemberRegistAction;
import exproject.common.action.MemberzipSearchAction;
import exproject.common.vo.ActionForward;

/**
 * Servlet implementation class MemberFrontController
 */
@WebServlet("*.comm")
public class CommonFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommonFrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String requestURI = request.getRequestURI();
		// 요청 URL : http://localhost:8088/LoginProject/login.me
		// requestURI : /LoginProject/login.me

		String contextPath = request.getContextPath();
		// 요청 URL : http://localhost:8088/LoginProject/login.me
		// contextPath : /LoginProject

		String command = requestURI.substring(contextPath.length());
		//String substring(int startIndex)
		//원본문자열에서 startIndex 문자부터 마지막 문자까지 반환하는 메소드
		// /login.me
		// 2. 요청별 비지니스로직 실행
		
		
		
		ActionForward forward = null;
		//각 비지니스 로직을 수행하고 마지막으로 뷰페이지로 포워딩할때 포워딩 정보를 저장하는 클래스
		
		Action action = null;

		if (command.equals("/login.comm")) {
			action = new MemberLoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} 
		else if (command.equals("/logout.comm")) {
			action = new MemberLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} 
		

		// 3.뷰페이지로 포워딩
		if (forward != null) {
			// 요청처리가 제대로되었다면..
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getUrl());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getUrl());
				dispatcher.forward(request, response);
			}
		}

	}// doget에 요청처리.

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}// Post방식으로 넘어왔을 때 한글처리를 해야하는 경우.. request.setCharacterEncoding("UTF-8");

}
