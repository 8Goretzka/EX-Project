package bo.service;

import bo.vo.BoardVO;
import static bo.db.JdbcUtil.*;

import java.sql.Connection;

import bo.dao.BoardDAO;
public class BoardUpdateProService {

	public boolean modifyArticle(BoardVO article) throws Exception {
		// TODO Auto-generated method stub
		boolean modifySuccess = false;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int updateCount = boardDAO.updateArticle(article);
		if(updateCount > 0) {
			commit(con);
			modifySuccess = true;
		}
		else {
			rollback(con);
		}
		//데이터를 수정하는 작업할 때는 commit과 rollback 해줘야 한다.
		close(con);
		return modifySuccess;
	}

}
