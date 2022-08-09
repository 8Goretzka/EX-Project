package service;

import vo.ExuserBean;
import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.ExuserDAO;

public class ExuserViewService {
	public ExuserBean getExuser(String viewId) {
		Connection con = getConnection();
		ExuserDAO exuserDAO = ExuserDAO.getInstance();
		exuserDAO.setConnection(con);
		ExuserBean exuser = exuserDAO.selectExuser(viewId);
		close(con);
		return exuser;
	}
}
