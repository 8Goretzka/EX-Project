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
		// ��û URL : http://localhost:8088/LoginProject/login.me
		// requestURI : /LoginProject/login.me

		String contextPath = request.getContextPath();
		// ��û URL : http://localhost:8088/LoginProject/login.me
		// contextPath : /LoginProject

		String command = requestURI.substring(contextPath.length());
		//String substring(int startIndex)
		//�������ڿ����� startIndex ���ں��� ������ ���ڱ��� ��ȯ�ϴ� �޼ҵ�
		// /login.me
		// 2. ��û�� �����Ͻ����� ����
		
		
		
		ActionForward forward = null;
		//�� �����Ͻ� ������ �����ϰ� ���������� ���������� �������Ҷ� ������ ������ �����ϴ� Ŭ����
		
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
		

		// 3.���������� ������
		if (forward != null) {
			// ��ûó���� ����εǾ��ٸ�..
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getUrl());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getUrl());
				dispatcher.forward(request, response);
			}
		}

	}// doget�� ��ûó��.

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}// Post������� �Ѿ���� �� �ѱ�ó���� �ؾ��ϴ� ���.. request.setCharacterEncoding("UTF-8");

}
