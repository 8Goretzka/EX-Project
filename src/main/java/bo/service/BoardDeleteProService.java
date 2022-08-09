package bo.service;
import static bo.db.JdbcUtil.*;

import java.sql.Connection;

import bo.dao.BoardDAO;
public class BoardDeleteProService {

	public boolean removeArticle(int num, String passwd) throws Exception {
		// TODO Auto-generated method stub
		boolean removeSuccess = false;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int deleteCount = boardDAO.deleteArticle(num, passwd);
		if(deleteCount > 0) {
			commit(con);
			removeSuccess = true;
		}
		else {
			rollback(con);
		}
		return removeSuccess;
	}

}
