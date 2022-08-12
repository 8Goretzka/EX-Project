package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import action.ExuserDeleteAction;
import action.ExuserJoinAction;
import action.ExuserListAction;
import action.ExuserLoginAction;
import action.ExuserLogoutAction;
import action.ExuserViewAction;
import vo.ActionForward;


/**
 * Servlet implementation class ExuserFrontController
 */
@WebServlet("*.ex")
public class ExuserFrontController extends javax.servlet.http.HttpServlet 
{
	static final long serialVersionUID = 1L;
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String RequestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String command = RequestURI.substring(contextPath.length());
    	ActionForward forward = null;
    	Action action = null;
    	
    	if(command.equals("/exuserLogin.ex")) {
    		forward = new ActionForward();
    		forward.setRedirect(true);
    		forward.setPath("./loginForm.jsp");
    	}
    	
    	else if(command.equals("/exuserJoin.ex")) {
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("./joinForm.jsp");
    	}
    	
    	else if(command.equals("/exuserLoginAction.ex")) {
    		action = new ExuserLoginAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
    	}
    	else if(command.equals("/exuserLogoutAction.ex")) {
    		action = new ExuserLogoutAction();
    		try {
    			forward = action.execute(request, response);
    		} catch (Exception e) {
    			// TODO: handle exception
    			e.printStackTrace();
    		}
    	}
    	
    	else if(command.equals("/exuserJoinAction.ex")) {
    		action = new ExuserJoinAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
    	}
    	
    	else if(command.equals("/exuserListAction.ex")) {
    		action = new ExuserListAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
    	}
    	
    	else if(command.equals("/exuserViewAction.ex")) {
    		action = new ExuserViewAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
    	}
    	
    	else if(command.equals("/exuserDeleteAction.ex")) {
    		action = new ExuserDeleteAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
    	}
    	
    	if(forward != null) {
    		if(forward.isRedirect()) {
    			response.sendRedirect(forward.getPath());
    		}
    		else {
    			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request, response);
    		}
    	}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}

}
