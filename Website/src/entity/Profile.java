package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Profile {
	

	public static void editProfile2
	(String username, String senior,    String name, 
	 String contact,  String address,  String postal,
	 String secretquestion,  String secretanswer )
	throws Exception
	{
		
		
		// Make secret answer lower case
		String secretanswer2 = secretanswer.toLowerCase();
		String secretanswer3 = secretanswer2.replaceAll(" ","");

		
		//Specify Driver
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);    	
    	// Execute the SQL Query
    	// Dump content into database
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "xxxx");
			Statement stmt = conn.createStatement();
			String sqlStr = "";
			sqlStr += "UPDATE clientlogin SET "
				   +   "senior = '"+senior+"' , name = '"+name+"' , contact='"+contact+"' , address='"+address+"' "
				   + ", postal='"+postal+"' , secretquestion='"+secretquestion+"' , secretanswer='"+secretanswer3+"' "
				   +  "WHERE username = '"+username+"';";
			
			// Debugging purposes
			System.out.println(sqlStr);
			
			// Execute The Statement
			stmt.executeUpdate(sqlStr);
			

			stmt.close();
			conn.close();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void editProfile
	(String username, String name, String contact, String address, String postal)
	throws Exception
	{
		//Specify Driver
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);    	
    	// Execute the SQL Query
    	// Dump content into database
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "xxxx");
			Statement stmt = conn.createStatement();
			String sqlStr = "";
			sqlStr += "UPDATE clientlogin SET "
				   +  "name = '"+name+"' , contact = '"+contact+"' , address='"+address+"' , postal='"+postal+"' "
				   +  "WHERE username = '"+username+"';";
			
			// Debugging purposes
			System.out.println(sqlStr);
			
			// Execute The Statement
			stmt.executeUpdate(sqlStr);
			

			stmt.close();
			conn.close();
		
	}

}
