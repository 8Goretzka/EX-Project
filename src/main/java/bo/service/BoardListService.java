package bo.service;
import static bo.db.JdbcUtil.*;

import java.sql.Connection;
import java.util.List;

import bo.dao.BoardDAO;
import bo.vo.BoardVO;
public class BoardListService {

	public int getArticleCount() throws Exception {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int articleCount = boardDAO.selectArticleCount();
		close(con);
		return articleCount;
	}

	public List<BoardVO> getArticles(int startRow, int pageSize)
	throws Exception{
		// TODO Auto-generated method stub
		Connection con = getConnection();
		bo.dao.BoardDAO boardDAO = bo.dao.BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		List<BoardVO> articleList = boardDAO.selectArticleList(startRow, pageSize);
		close(con);
		return articleList;
	}

}
