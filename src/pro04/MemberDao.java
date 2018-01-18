package pro04;

import java.sql.*;
import java.util.*;


public class MemberDao {
	// MemberDao 를 작성합니다.
  
	public void insertMember(MemberVo vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "insert into member values(seq_member_id.nextval, ? , ? , ? ,? )";
		    
		    pstmt = conn.prepareStatement(query);
		    
		    pstmt.setString(1,vo.getName());
		    pstmt.setString(2,vo.getEmail());
			pstmt.setString(3,vo.getPassword());
		    pstmt.setString(4,vo.getGender());
		
		    // 4.결과처리
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		       /*if (rs != null) {
		            rs.close();
		        }     select 할때만 필요     */    
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
		
	

	public void updatePassword(MemberVo vo) {
		
				Connection conn = null;
				PreparedStatement pstmt = null;
				//ResultSet rs = null;

				try {
				    // 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName("oracle.jdbc.driver.OracleDriver");

				    // 2. Connection 얻어오기
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					conn = DriverManager.getConnection(url, "webdb", "webdb");
				    // 3. SQL문 준비 / 바인딩 / 실행
					String query = " update member set password = ?  where email = ?";
					
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1,vo.getPassword());
					pstmt.setString(2,vo.getEmail());
					
				    
				    int count = pstmt.executeUpdate();
				    		// 4.결과처리
				    

				} catch (ClassNotFoundException e) {
				    System.out.println("error: 드라이버 로딩 실패 - " + e);
				} catch (SQLException e) {
				    System.out.println("error:" + e);
				} finally {
				   
				    // 5. 자원정리
				    try {
				       /*if (rs != null) {
				            rs.close();
				        }    select 할때만 필요  */            
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

	public void deleteMember(String email) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행

			String query = " delete  from member where email = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,email);
			
			int count = pstmt.executeUpdate();      //insert, update, delete
			

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

	
	public List<MemberVo> getListAll() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		 List<MemberVo> memberList = new ArrayList<MemberVo>();
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = " select m.id, m.name, m.email, m.password, m.gender " + 
					   "  from member m";
		    pstmt = conn.prepareStatement(query);
		    rs = pstmt.executeQuery();
		    
		   
		    
		    // 4.결과처리
		    while(rs.next()) {
		    	
		    
		    	
		    int id = rs.getInt("id");
		    String name = rs.getString("name");
		    String email = rs.getString("email");
		    String password = rs.getString("password");
		    String gender = rs.getString("gender");
		    
		    
		    memberList.add(new MemberVo(id,name,email,password,gender));
		    
    
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

		return memberList;
	}
}
