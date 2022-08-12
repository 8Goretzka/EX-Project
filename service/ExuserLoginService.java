package service;

import vo.ExuserBean;
import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.ExuserDAO;

public class ExuserLoginService {
	public boolean login(ExuserBean exuser) {
		
		Connection con = getConnection();
		ExuserDAO exuserDAO = ExuserDAO.getInstance();
		exuserDAO.setConnection(con);
		boolean loginResult = false;
		String loginId = exuserDAO.selectLoginId(exuser);
		
		if(loginId != null) {
			loginResult = true;
		}
		close(con);
		return loginResult;
	}
}
