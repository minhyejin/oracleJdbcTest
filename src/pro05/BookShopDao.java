package pro05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class BookShopDao {
	public void insert(BookVo vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "insert into bookshop values(seq_bookshop_id.nextval, ? , ? , ? , ? , ? )";
		    
		    pstmt = conn.prepareStatement(query);
		    
		    pstmt.setString(1,vo.getTitle());
		    pstmt.setString(2,vo.getPubs());
			pstmt.setString(3,vo.getPubDate());
		    pstmt.setString(4,vo.getAuthorName());
		    pstmt.setInt(5,vo.getStateCode());
		
		    // 4.결과처리
		    
		    pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		      
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}

	}
		
	
	public void selectBook(int BookId) {
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

	try {
	    // 1. JDBC 드라이버 (Oracle) 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");

	    // 2. Connection 얻어오기
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(url, "webdb", "webdb");

	    // 3. SQL문 준비 / 바인딩 / 실행
	    String query = "";
	    
	    pstmt = conn.prepareStatement(query);
	    rs = pstmt.executeQuery();
	    // 4.결과처리

	    while(rs.next()) {
	    	int id = rs.getInt("id");
		    String title= rs.getString("title");
		    String pubs = rs.getString("pubs");
		    String pubDate = rs.getString("pub_date");
		    String authorName = rs.getString("author_name");
		    int stateCode = rs.getInt("state_code");
 
		    System.out.println(id + " . " + title +" | "+ pubs +" | "+ pubDate +" | "+ authorName +
		    	 " | "+ stateCode);
		    System.out.println("=================================================================");
		   } 
	    
	} catch (ClassNotFoundException e) {
	    System.out.println("error: 드라이버 로딩 실패 - " + e);
	} catch (SQLException e) {
	    System.out.println("error:" + e);
	} finally {
	   
	    // 5. 자원정리
	    try {
	        if (rs != null) {
	            rs.close();
	        }                
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    } catch (SQLException e) {
	        System.out.println("error:" + e);}
	    }
	} 

	public void rent(int id) {
		

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		    
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			
			pstmt = conn.prepareStatement(query);
			
			
		    
		     pstmt.executeUpdate();
		    		// 4.결과처리
		    

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		               
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}


	}
	

	
	public List<BookVo> getListAll() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		 List<BookVo> BookList = new ArrayList<BookVo>();
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "";
		    pstmt = conn.prepareStatement(query);
		    rs = pstmt.executeQuery();
		    
		   
		    
		    // 4.결과처리
		    while(rs.next()) {

		    int id = rs.getInt("id");
		    String title= rs.getString("title");
		    String pubs = rs.getString("pubs");
		    String pubDate = rs.getString("pub_date");
		    String authorName = rs.getString("author_name");
		    int stateCode = rs.getInt("state_code");
		    
		    
		    BookList.add(new BookVo(id,title,pubs,pubDate,authorName,stateCode));
		    
    
		   } 
		    
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (rs != null) {
		            rs.close();
		        }                
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}

		return BookList;}

	
}
