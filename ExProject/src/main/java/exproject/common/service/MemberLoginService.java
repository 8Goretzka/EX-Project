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
		//����	 SQL ������ DBMS�� �����ϴ� ������ �ϴ� Ŭ����
		
		memberDAO.setConnection(con);
		
		String loginId = memberDAO.selectLoginId(id,passwd);
		if(loginId!=null) {
			loginSuccess =true;
		}
		close(con);
		return loginSuccess;
	}

}
