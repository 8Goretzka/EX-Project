package service;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.ExuserDAO;

public class ExuserDeleteService {
	public boolean deleteExuser(String deleteId) {
		boolean deleteResult = false;
		Connection con = getConnection();
		ExuserDAO exuserDAO = ExuserDAO.getInstance();
		exuserDAO.setConnection(con);
		int deleteCount = exuserDAO.deleteExuser(deleteId);
		if(deleteCount > 0) {
			commit(con);
			deleteResult = true;
		}
		else {
			rollback(con);
		}
		close(con);
		return deleteResult;
	}
}
