package pro03;

import java.sql.*;

public class Pro03 {

	public static void main(String[] args) {
		// 0. import java.sql.*;
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {
				    // 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName("oracle.jdbc.driver.OracleDriver");
				    // 2. Connection 얻어오기
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					conn = DriverManager.getConnection(url, "hr", "hr");
				    // 3. SQL문 준비 / 바인딩 / 실행
				    String query = " select em.last_name, em.email, de.department_name, "
				    					+ " jo.job_title, em.employee_id, lo.city, em.job_id "
				    		+ " from employees em , departments de, jobs jo, locations lo "
				    		+ " where em.department_id = de.department_id "
				    			  + " and de.location_id = lo.location_id and em.job_id = jo.job_id"
				    			  + " and jo.job_id like 'PU_CLERK' and lo.city like 'Seattle'"
				    		+ " order by em.employee_id desc ";
				    pstmt = conn.prepareStatement(query);
				    rs = pstmt.executeQuery();
				    
				    // 4.결과처리
				    while(rs.next()) {
				    int employeeId = rs.getInt("employee_id");
				    String lastName = rs.getString("last_name");
				    String email=rs.getString("email");
				    String departmentName=rs.getString("department_name");
				    String jobTitle=rs.getString("job_title");
				    String city=rs.getString("city");
				    String jobId = rs.getString("job_id");
				    
				    
				    
				    
				    System.out.println(employeeId + " | "+ lastName + " | " + email + " | " + departmentName + " | " + jobTitle + " | " + city + " | " + jobId);
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

    }


}
