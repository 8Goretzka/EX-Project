package bo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.service.BoardContentService;
import bo.vo.ActionForward;
import bo.vo.BoardVO;

public class BoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");

		BoardContentService boardContentService
		= new BoardContentService();
		
	    BoardVO article =  boardContentService.getArticle(num);
	    request.setAttribute("article", article);
	    request.setAttribute("pageNum", pageNum);
	    
	    ActionForward forward = new ActionForward();
	    forward.setUrl("board/content.jsp");
	    
	    
		return forward;
	}

}
