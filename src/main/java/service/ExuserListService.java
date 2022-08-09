package service;

import java.sql.Connection;
import java.util.ArrayList;
import dao.ExuserDAO;
import static db.JdbcUtil.*;
import vo.ExuserBean;

public class  ExuserListService {
	public ArrayList<ExuserBean> getExuserList() {
		Connection con = getConnection();
		ExuserDAO exuserDAO = ExuserDAO.getInstance();
		exuserDAO.setConnection(con);
		ArrayList<ExuserBean> exuserList = exuserDAO.selectExuserList();
		close(con);
		return exuserList;
	}
}
