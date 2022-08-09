package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;
import vo.ExuserBean;
import static db.JdbcUtil.*;

public class ExuserDAO {
	public static ExuserDAO instance;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	private ExuserDAO() {
		
	}
	public static ExuserDAO getInstance() {
		if(instance == null) {
			instance = new ExuserDAO();
		}
		return instance;
	}
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public String selectLoginId(ExuserBean exuser) {
		String loginId = null;
		String sql = "SELECT EXUSER_ID FROM EXUSER WHERE EXUSER_ID=? AND EXUSER_PASS=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, exuser.getEXUSER_ID());
			pstmt.setString(2, exuser.getEXUSER_PASS());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loginId = rs.getString("EXUSER_ID");
			}
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println(" 에러: " + ex);
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return loginId;
	}
	
	public int insertExuser(ExuserBean exuser) {
		String sql = "INSERT INTO EXUSER VALUES (?,?,?,?,?,?)";
		int insertCount = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, exuser.getEXUSER_ID());
			pstmt.setString(2, exuser.getEXUSER_PASS());
			pstmt.setString(3, exuser.getEXUSER_NAME());
			pstmt.setInt(4, exuser.getEXUSER_AGE());
			pstmt.setString(5, exuser.getEXUSER_GENDER());
			pstmt.setString(6, exuser.getEXUSER_EMAIL());
			insertCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("joinExuser 에러: " + ex);
		}
		finally {
			close(pstmt);
		}
		return insertCount;
	}
	
	public ArrayList<ExuserBean> selectExuserList() {
		String sql = "SELECT * FROM EXUSER";
		ArrayList<ExuserBean> exuserList = new ArrayList<ExuserBean>();
		ExuserBean eb = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					eb = new ExuserBean();
					eb.setEXUSER_ID(rs.getString("EXUSER_ID"));
					eb.setEXUSER_PASS(rs.getString("EXUSER_PASS"));
					eb.setEXUSER_NAME(rs.getString("EXUSER_NAME"));
					eb.setEXUSER_AGE(rs.getInt("EXUSER_AGE"));
					eb.setEXUSER_GENDER(rs.getString("EXUSER_GENDER"));
					eb.setEXUSER_EMAIL(rs.getString("EXUSER_EMAIL"));
					exuserList.add(eb);
				} while (rs.next());
			}
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("getDeatilExuser 에러: " + ex);
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return exuserList;
	}
	
	 public ExuserBean selectExuser(String id){
		String sql = "SELECT * FROM EXUSER WHERE EXUSER_ID=?";
		ExuserBean eb = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				eb = new ExuserBean();
				eb.setEXUSER_ID(rs.getString("EXUSER_ID"));
				eb.setEXUSER_PASS(rs.getString("EXUSER_PASS"));
				eb.setEXUSER_NAME(rs.getString("EXUSER_NAME"));
				eb.setEXUSER_AGE(rs.getInt("EXUSER_AGE"));
				eb.setEXUSER_GENDER(rs.getString("EXUSER_GENDER"));
				eb.setEXUSER_EMAIL(rs.getString("EXUSER_EMAIL"));
			}
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("getDeatilExuser 에러: " + ex);
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return eb;
	}
	public int deleteExuser(String id) {
		String sql = "DELETE EXUSER WHERE EXUSER_ID=?";
		int deleteCount = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("deleteExuser 에러: " + ex);
		}
		finally {
			close(pstmt);
		}
		return deleteCount;
	}
}
