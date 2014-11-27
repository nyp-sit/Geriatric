package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthenticationSuite {

	public static boolean AccountExist(String username) throws SQLException, ClassNotFoundException{
		boolean existence = false;
		if(username != null){
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/project", "root", "xxxx");

			String query = "SELECT * from loginclient where username ='" + username + "'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			System.out.println(rs.toString());
			if(rs.next()){existence = true;}
			
			
			conn.close();
			rs.close();
			st.close();
			

	}

		return existence;
	}
	
	
	
	
	public static boolean VerifyClientLogin(String username, String password){
		boolean verification = false;
		
		return verification;
	}
	
	
	
	public static boolean VerifyHTTPRequest(String username, String secretKey) throws Exception{
		boolean verification = false;
		
		if(AccountExist(username) == true){
			
			
			
			// SQL Select Command
			String sqlState = "Select secretkey from loginclient where username = '" + username +"';"; 
			
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/project", "root", "xxxx");

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sqlState);
			
			String retrievedsecretKey = "";
			
			
			while (rs.next()) {
				retrievedsecretKey = rs.getString("secretkey");
			}
			
			
			System.out.println(retrievedsecretKey);
			
			if(retrievedsecretKey.equals(secretKey)){
				verification = true;
			}

			
			rs.close();
			st.close();
			conn.close();
			
			
			return verification;
		}
		else{
			return verification;
		}
		

	}
	
	
	
	
	
}
