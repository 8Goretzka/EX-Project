package bo.dao;

import java.sql.*;
import static bo.db.JdbcUtil.*;
import bo.vo.BoardVO;
import java.util.*;

public class BoardDAO {
   private Connection con;
	private static BoardDAO instance = new BoardDAO();
   
   public static BoardDAO getInstance() {
       return instance;
   }
   
   private BoardDAO() {
   }
   
   //기존 getConnection() 지우고 생성한 메소드
   public void setConnection(Connection con) {
		// TODO Auto-generated method stub
		this.con = con;
	}

   public int insertArticle(BoardVO article) 
   throws Exception {
   	int insertCount = 0;
       PreparedStatement pstmt = null;
		ResultSet rs = null;

		int num=article.getNum();
		int ref=article.getRef();
		int re_step=article.getRe_step();
		int re_level=article.getRe_level();
		
		int number=0; //새로운 관련글 번호 ref값을 저장할 변수
       String sql="";

       try {

           pstmt = con.prepareStatement
           		("select max(num) from board");
			rs = pstmt.executeQuery();
			
			if (rs.next()) 
				//작성된 글이 하나라도 있으면, 지금 작성한 글이 첫번째 글이 아니면...
		      number=rs.getInt(1)+1; //중복될 수 없음(최대값 + 1)
		    else
		      number=1; 
		   
		    if (num!=0)   //작성한 글이 답변글이면...
		    {  
		      sql="update board set re_step=re_step+1 " +
		      		"where ref= ? and re_step> ?";
             pstmt = con.prepareStatement(sql);
             pstmt.setInt(1, ref);
			  pstmt.setInt(2, re_step);
			  pstmt.executeUpdate();
			  re_step=re_step+1;
			  re_level=re_level+1;
		     }
		    else{ //원글
		  	  ref=number;
			  re_step=0;
			  re_level=0;
		     }	 
           // 쿼리를 작성
           sql = "insert into board" +
          "(num,writer,email,subject,passwd,reg_date,";
           sql+="ref,re_step,re_level,content) " +
           "values(board_seq.nextval,?,?,?,?,?,?,?,?,?)";

           pstmt = con.prepareStatement(sql);
           pstmt.setString(1, article.getWriter());
           pstmt.setString(2, article.getEmail());
           pstmt.setString(3, article.getSubject());
           pstmt.setString(4, article.getPasswd());
			pstmt.setTimestamp(5, article.getReg_date());
           pstmt.setInt(6, ref);
           pstmt.setInt(7, re_step);
           pstmt.setInt(8, re_level);
			pstmt.setString(9, article.getContent());
			
			
			insertCount = pstmt.executeUpdate();
       } catch(Exception ex) {
           ex.printStackTrace();
       } finally {
			close(rs);
			close(pstmt);
       }
       return insertCount;
   }
   
	public int selectArticleCount()
   throws Exception {
       PreparedStatement pstmt = null;
       ResultSet rs = null;

       int x=0;

       try {
           
           pstmt = con.prepareStatement
           		("select count(*) from board"); //전체 레코드 가져오기 = * 
           rs = pstmt.executeQuery();

           if (rs.next()) {
              x= rs.getInt(1);
			}
       } catch(Exception ex) {
           ex.printStackTrace();
       } finally {
          close(rs);
          close(pstmt);
       }
		return x;
   }

	public List selectArticleList(int start, int end)
   throws Exception {
       Statement stmt = null;
       ResultSet rs = null;
       List articleList=null;
       PreparedStatement pstmt = null;
       try {
           
           pstmt = con.prepareStatement(
//SubQuery : MainQuery 내에서 먼저 독립적으로 실행되어 MainQuery에 적용되는 쿼리 문장
//View : 다른 테이블이나 또 다른 뷰를 통해서 원하는 데이터를 조회할 수 있는 쿼리 덩어리 (가상 테이블)
//뷰를 통해서 원하는 형태로 테이블이나 뷰에 있는 데이터를 보는 것
//SubQuery 중에 FROM 절 뒤에 나오는 SubQuery를 inline view라고 함.
//inline view는 MainQuery가 실행될 때 생성되었다가 실행이 완료되면 바로 소멸됨. 그리고 반드시 별칭이 있어야 한다.      		
"select list2.* from(select rownum r, list1.*  " +
"from(select *  from board order by ref desc, re_step asc)list1) " +
"list2 where r between ? and ?");
           pstmt.setInt(1, start);
			pstmt.setInt(2, start+end-1);
           rs = pstmt.executeQuery();
  
  
  
               if (rs.next()) {
               int i=0;
               articleList = new ArrayList(end);
               do{
                 BoardVO article= new BoardVO();
     article.setNum(rs.getInt("num"));
     article.setWriter(rs.getString("writer"));
                 article.setEmail(rs.getString("email"));
                 article.setSubject(rs.getString("subject"));
                 article.setPasswd(rs.getString("passwd"));
        article.setReg_date(rs.getTimestamp("reg_date"));
     article.setReadcount(rs.getInt("readcount"));
                 article.setRef(rs.getInt("ref"));
                 article.setRe_step(rs.getInt("re_step"));
     article.setRe_level(rs.getInt("re_level"));
                 article.setContent(rs.getString("content"));
     
                 articleList.add(article);
                 i++;
      }while(rs.next()&& i<end);
  }
       } catch(Exception ex) {
           ex.printStackTrace();
       } finally {
          close(rs);
          close(pstmt);
       }
 return articleList;
   }


   public BoardVO selectArticle(int num)
   throws Exception {
     
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       BoardVO article=null;
       try {

           pstmt = con.prepareStatement(
           	"update board set readcount=readcount+1 "
           	+ "where num = ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

           pstmt = con.prepareStatement(
           	"select * from board where num = ?");
           pstmt.setInt(1, num);
           rs = pstmt.executeQuery();

           if (rs.next()) {
               article = new BoardVO();
               article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
               article.setEmail(rs.getString("email"));
               article.setSubject(rs.getString("subject"));
               article.setPasswd(rs.getString("passwd"));
			    article.setReg_date(rs.getTimestamp("reg_date"));
				article.setReadcount(rs.getInt("readcount"));
               article.setRef(rs.getInt("ref"));
               article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
               article.setContent(rs.getString("content"));
			}
       } catch(Exception ex) {
           ex.printStackTrace();
       } finally {
          close(rs);
          close(con);
       }
		return article;
   }

   public BoardVO selectUpdateArticle(int num)
   throws Exception {
      
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       BoardVO article=null;
       try {

           pstmt = con.prepareStatement(
           	"select * from board where num = ?");
           pstmt.setInt(1, num);
           rs = pstmt.executeQuery();

           if (rs.next()) {
               article = new BoardVO();
               article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
               article.setEmail(rs.getString("email"));
               article.setSubject(rs.getString("subject"));
               article.setPasswd(rs.getString("passwd"));
			    article.setReg_date(rs.getTimestamp("reg_date"));
				article.setReadcount(rs.getInt("readcount"));
               article.setRef(rs.getInt("ref"));
               article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
               article.setContent(rs.getString("content"));   
			}
       } catch(Exception ex) {
           ex.printStackTrace();
       } finally {
           close(rs);
           close(pstmt);
       }
		return article;
   }
   

   public int updateArticle(BoardVO article)
   throws Exception {
      
       PreparedStatement pstmt = null;
       ResultSet rs= null;

       String dbpasswd="";
       String sql="";
		int updateCount=0;
       try {
         
           
			pstmt = con.prepareStatement(
           	"select passwd from board where num = ?");
           pstmt.setInt(1, article.getNum());
           rs = pstmt.executeQuery();
           
			if(rs.next()){
			  dbpasswd= rs.getString("passwd"); 
			  if(dbpasswd.equals(article.getPasswd())){
               sql="update board set writer=?,email=?,subject=?,passwd=?";
			    sql+=",content=? where num=?";
               pstmt = con.prepareStatement(sql);

               pstmt.setString(1, article.getWriter());
               pstmt.setString(2, article.getEmail());
               pstmt.setString(3, article.getSubject());
               pstmt.setString(4, article.getPasswd());
               pstmt.setString(5, article.getContent());
			    pstmt.setInt(6, article.getNum());
               updateCount = pstmt.executeUpdate();
			  }
			}
       } catch(Exception ex) {
           ex.printStackTrace();
       } finally {
			close(rs);
			close(pstmt);
       }
		return updateCount;
   }
   
   public int deleteArticle(int num, String passwd)
   throws Exception {
      
       PreparedStatement pstmt = null;
       ResultSet rs= null;
       String dbpasswd="";
       int deleteCount = 0;
       try {
			

           pstmt = con.prepareStatement(
           	"select passwd from board where num = ?");
           pstmt.setInt(1, num);
           rs = pstmt.executeQuery();
           
			if(rs.next()){
				dbpasswd= rs.getString("passwd"); 
				if(dbpasswd.equals(passwd)){
					pstmt = con.prepareStatement(
           	      "delete from board where num=?");
                   pstmt.setInt(1, num);
                  deleteCount = pstmt.executeUpdate();
				}
       } 
       }catch(Exception ex) {
           ex.printStackTrace();
       } finally {
           close(rs);
           close(pstmt);
       }
		return deleteCount;
   }


}
