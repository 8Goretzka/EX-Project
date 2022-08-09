package service;

import vo.ExuserBean;
import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.ExuserDAO;

public class ExuserJoinService {
	public boolean joinExuser(ExuserBean exuser) {
		boolean joinSuccess = false;
		ExuserDAO exuserDAO = ExuserDAO.getInstance();
		Connection con = getConnection();
		exuserDAO.setConnection(con);
		int insertCount = exuserDAO.insertExuser(exuser);
		if(insertCount > 0) {
			joinSuccess = true;
			commit(con);
		}
		else {
			rollback(con);
		}
		close(con);
		return joinSuccess;
	}
}
