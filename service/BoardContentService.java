package bo.service;

import bo.vo.BoardVO;
import static bo.db.JdbcUtil.*;

import java.sql.Connection;

import bo.dao.BoardDAO;
public class BoardContentService {

	public BoardVO getArticle(int num) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		BoardVO article = boardDAO.selectArticle(num);
		close(con);
		return article;
	}

}
