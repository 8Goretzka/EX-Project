package service;

import dao.MemberDAO;
import vo.MemberVO;

import static db.JdbcUtil.*;

import java.sql.Connection;

public class MemberRegistService {

	public boolean registMember(MemberVO newMemberVO) {
		// TODO Auto-generated method stub
		boolean registSuccess = false;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		int insertCount = memberDAO.insertMember(newMemberVO);
		
		if(insertCount > 0) {
			commit(con);
			registSuccess = true;
		}
		else {
			rollback(con);
		}
		close(con);
		
		return registSuccess;

	}

}
