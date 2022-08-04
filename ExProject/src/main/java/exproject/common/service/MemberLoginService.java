package exproject.common.service;

import static exproject.common.db.JdbcUtil.*;

import java.sql.Connection;

import exproject.common.dao.MemberDAO;

public class MemberLoginService {

	public boolean login(String id, String passwd) {
		// TODO Auto-generated method stub
		boolean loginSuccess = false;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		//DAO(Data Access Object)
		//직접	 SQL 구문을 DBMS에 전송하는 역할을 하는 클래스
		
		memberDAO.setConnection(con);
		
		String loginId = memberDAO.selectLoginId(id,passwd);
		if(loginId!=null) {
			loginSuccess =true;
		}
		close(con);
		return loginSuccess;
	}

}
