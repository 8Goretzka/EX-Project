package exproject.common.service;

import java.sql.Connection;

import exproject.common.dao.MemberDAO;

import static exproject.common.db.JdbcUtil.*;
import exproject.common.vo.MemberVO;

public class LoginService {

	public static MemberVO getLoginMember(String id, String passwd) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		MemberVO loginMember = memberDAO.selectLoginMember(id, passwd);
		
		close(con);
		return loginMember;
	}

}
