package bo.service;
import static bo.db.JdbcUtil.*;

import java.sql.Connection;

import bo.dao.BoardDAO;
import bo.vo.BoardVO;
public class BoardWriteProService {

	public boolean registArticle(BoardVO article)  throws Exception{
		// TODO Auto-generated method stub
		boolean registSuccess = false;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int insertCount = boardDAO.insertArticle(article);
		if(insertCount > 0) {
			registSuccess = true;
			commit(con);
		}
		else {
			rollback(con);
		}
		return registSuccess;
	}

}
