package bo.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.service.BoardListService;
import bo.vo.ActionForward;
import bo.vo.BoardVO;
import bo.vo.PageVO;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int pageSize = 10; //한페이지 당 출력되는 글의 개수
		String pageNum = request.getParameter("pageNum");
	    if (pageNum == null) {//파라미터 값으로 pageNum 값이 전송되지 않은경우...
	        pageNum = "1"; //작업페이지를 1 페이지로 지정, 즉, 1페이지를 리스트함
	    }

	    int currentPage = Integer.parseInt(pageNum);
	    int startRow = (currentPage - 1) * pageSize + 1;
	    //해당 페이지에 출력될 첫번째 레코드 번호
	    //현재페이지 : 1
	    //startRow = (1 - 1) * 10 + 1 ===> 1
	    //현재페이지 : 2
	    //startRow = (2 - 1) * 10 + 1 ===> 11
	   
	    int count = 0; //총 글의 개수
	    int number=0; //목록보기 페이지에 출력되는 글들의 번호

	    BoardListService boardListService
	    = new BoardListService();
	    
	    
	    List<BoardVO> articleList = null; //페이지에 출력될 글목록을 저장하는 컬렉션
	    count = boardListService.getArticleCount();
	    
	    if (count > 0) {
	        articleList = boardListService.getArticles(startRow, pageSize);
	    }

		number=count-(currentPage-1)*pageSize;
		//해당 페이지에 첫번째로 출력되는 글의 글번호를 구하는 부분
		//전체 글의 개수 : 132
		//현재 페이지 : 1
		//number : 132 - (1 - 1) * 10 ===> 132
		//전체 글의 개수 : 132
		//현재 페이지 : 2
		//number : 132 - (2 - 1) * 10 ===> 122
		
		int pageCount = 0;
		//총 페이지 개수를 저장할 변수
		
		int startPage = 0;
		//동일 페이지 그룹에서 첫 번째 페이지 번호
		
		int endPage = 0;
		//현재 페이지가 속해있는 페이지 그룹의 마지막 페이지 번호
		
		if (count > 0) {
			
		    	//전체 페이지 개수
		        pageCount = count / pageSize + 
		        		( count % pageSize == 0 ? 0 : 1);
				 
		    	//동일 페이지 그룹에서 첫번째 페이지번호
		        startPage = (int)((currentPage-1)/10)*10+1;
		    	
		    	//한 페이지그룹의 크기
				int pageBlock=10;
		    	
		    	//현재페이지가 속해있는 페이지그룹의 마지막 페이지번호
		        endPage = startPage + pageBlock-1;
		    	
		    	//마지막 페이지 그룹인 경우 발생할 수 있다.
		        if (endPage > pageCount) endPage = pageCount;
		 }
		 
		 
		 request.setAttribute("articleList", articleList);
		 PageVO pageVO = new PageVO();
		 pageVO.setCount(count);
		 pageVO.setCurrentPage(currentPage);
		 pageVO.setEndPage(endPage);
		 pageVO.setNumber(number);
		 pageVO.setPageCount(pageCount);
		 pageVO.setStartPage(startPage);
		 
		 request.setAttribute("pageVO", pageVO);
		 
		 ActionForward forward = new ActionForward();
		 forward.setUrl("board/list.jsp");
		return forward;
	}

}



