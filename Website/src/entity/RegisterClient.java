package entity;

import java.sql.*;

public class RegisterClient{
	
	public static boolean registerNewClient
	(String username, String password, String senior, 
	 String name, String contact, String address, 
	 String postal) 
	throws Exception
	
	{
		//Specify Driver
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		
		
		
		
    	
    	
		
		if(name != null && password != null){
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "xxxx");
			Statement stmt = conn.createStatement();
			String sqlStr = "";
			sqlStr += "INSERT INTO clientlogin (username, password, senior, name, contact, address, postal) VALUES (";
			sqlStr += "'"+username+"','"+password+"','"+senior+"','"+name+"','"+contact+"','"+address+"','"+postal+"');";

          
			stmt.executeUpdate(sqlStr);
			
			// Debugging purposes
			System.out.println("Query Statement : " + sqlStr);

			stmt.close();
			conn.close();
			
			
			TestCreatePDF.create(username, address, postal);
			
			return true;
			
			
			
		}
		return false;
		
		
		}
	
	
	

	
	public static boolean verifyUsernameExist(String user) throws Exception{
		boolean verify = false;
		if(user != null){
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/project", "root", "xxxx");

			String query = "SELECT * from clientlogin where username ='" + user + "'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			System.out.println(rs.toString());

			if(rs.next()){
				verify = true;
			}
	}

		return verify;

}
	
	
	
	
	
	
	
	
			
	}

