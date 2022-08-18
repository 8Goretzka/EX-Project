package exproject.common.service;

import java.sql.Connection;
import java.util.ArrayList;

import exproject.common.dao.MemberDAO;

import static exproject.common.db.JdbcUtil.*;
import exproject.common.vo.ZipCodeVO;

public class MemberZipSearchService {

	public ArrayList<ZipCodeVO> searchZipcodeList(String ro) {
		// TODO Auto-generated method stub
		Connection con =getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		ArrayList<ZipCodeVO> zipCodeList = memberDAO.selectZipCodeList(ro);
		close(con);
		
		return zipCodeList;
	}

}
