package bo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.action.Action;
import bo.action.BoardContentAction;
import bo.action.BoardDeleteFormAction;
import bo.action.BoardDeleteProAction;
import bo.action.BoardListAction;
import bo.action.BoardUpdateFormAction;
import bo.action.BoardUpdateProAction;
import bo.action.BoardWriteFormAction;
import bo.action.BoardWriteProAction;
import bo.vo.ActionForward;

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1. 요청 파악
		String requestURI = request.getRequestURI();
		//http://localhost:8088/project/boardWriteForm.bo
		// /project/boardWriteForm.bo
		//length : 1
		//index : 0
		String contextPath = request.getContextPath();
		// /project
		
		String command = requestURI.substring(contextPath.length());
		// /boardWriteForm.bo
		
		//2. 각 요청처리
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/boardWriteForm.bo")) {
			action = new BoardWriteFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/boardWritePro.bo")) {
			action = new BoardWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/boardList.bo")) {
			action = new BoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/boardContent.bo")) {
			action = new BoardContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/boardUpdateForm.bo")) {
			action = new BoardUpdateFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/boardUpdatePro.bo")) {
			action = new BoardUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/boardDeleteForm.bo")) {
			action = new BoardDeleteFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/boardDeletePro.bo")) {
			action = new BoardDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//3. 포워딩
		if(forward != null) {
			//요청처리가 제대로 되었으면...
			
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getUrl());
			}
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getUrl());
				dispatcher.forward(request, response);
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}