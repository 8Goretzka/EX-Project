package bo.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class JdbcUtil {
	/*static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	public static Connection getConnection() {
		Connection con = null;
		try {
//		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", 
//				"java", "java");
		Context initCtx = new InitialContext();//톰캣 자체의 컨텍스트를 얻어옴
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		//Resource 정의 컨텍스트를 얻어옴
		DataSource ds = (DataSource)envCtx.lookup("jdbc/jsptest");
		con = ds.getConnection();
		
		con.setAutoCommit(false); //명시적으로 해주지않으면 autoCommit의 defalut값이 true
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	

	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void close(PreparedStatement pstmt) {
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void commit(Connection con) {
		
		try {
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void rollback(Connection con) {
		
		try {
			con.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
