// Author : Paul Low 122810E
// Entity Class for Simple Client Login

package entity;

import java.security.MessageDigest;
import java.sql.*;



public class Login {


	
	public static boolean verifyUsernameExist(String user) throws Exception{
		boolean verify = false;
		if(user != null){
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/project", "root", "xxxx");

			String query = "SELECT * from client where username ='" + user + "'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			System.out.println(rs.toString());

			if(rs.next()){
				verify = true;
			}
	}

		return verify;

}


	public static boolean verifyStaffUsernameExist(String user) throws ClassNotFoundException, SQLException{
		boolean verify = false;
		if(user != null){
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/project", "root", "xxxx");

			String query = "SELECT * from staff where username ='" + user + "'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			System.out.println(rs.toString());

			if(rs.next()){
				verify = true;
			}
	}

		return verify;

}

	
	
	
	
	



public static boolean verifyPassword(String username , String password) throws Exception{

	// >Overview
	// >Create password SHA-256 Hash
	// >Retrieve user name and hashed password from database
	// >Compare the input user name and password with
	// >the user name and password from the database
	// >Return success / failure boolean value

	
	//Declare boolean value
	boolean passwordsMatch = false;

	// SQL Select Statement to retrieve user name and password (SHA-256)
	MessageDigest md = MessageDigest.getInstance("SHA-256");
	md.update(password.getBytes());

	byte byteData[] = md.digest();

	//Convert the byte to hex format method 1
	StringBuffer sb = new StringBuffer();
	for (int i = 0; i < byteData.length; i++) 
	{
		sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	}
	System.out.println(sb.toString());

	//Convert the byte to hex format method 2
	StringBuffer hexString = new StringBuffer();
	for (int i=0;i<byteData.length;i++) 
	{
		String hex=Integer.toHexString(0xff & byteData[i]);
		if(hex.length()==1) hexString.append('0');
		hexString.append(hex);
	}
	System.out.println(hexString.toString());
	
	
	String hashedPassword = hexString.toString();




	// SQL Select Command
	String sqlState = "Select username , password from client where username = '" + username +"';"; 
	
	String driver = "com.mysql.jdbc.Driver";
	Class.forName(driver);
	Connection conn = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/project", "root", "xxxx");

	Statement st = conn.createStatement();
	ResultSet rs = st.executeQuery(sqlState);
	
	String retrievedPassword = "";
	
	
	while (rs.next()) {
		retrievedPassword = rs.getString("password");
		System.out.println(rs.getString("username"));
		System.out.println(retrievedPassword);
	}
	
	
	System.out.println(retrievedPassword);
	
	if(retrievedPassword.equals(hashedPassword)){
		System.out.println("Passwords Match");
		passwordsMatch = true;
	}
	else{
		System.out.println("Passwords Do Not Match");
	}

	//if match ()
	// return true
	//else return false
	return passwordsMatch;



}



public static boolean verifyStaffPassword(String username , String password) throws Exception{

	// >Overview
	// >Create password SHA-256 Hash
	// >Retrieve user name and hashed password from database
	// >Compare the input user name and password with
	// >the user name and password from the database
	// >Return success / failure boolean value

	
	//Declare boolean value
	boolean passwordsMatch = false;

	// SQL Select Statement to retrieve user name and password (SHA-256)
	MessageDigest md = MessageDigest.getInstance("SHA-256");
	md.update(password.getBytes());

	byte byteData[] = md.digest();

	//Convert the byte to hex format method 1
	StringBuffer sb = new StringBuffer();
	for (int i = 0; i < byteData.length; i++) 
	{
		sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	}
	System.out.println(sb.toString());

	//Convert the byte to hex format method 2
	StringBuffer hexString = new StringBuffer();
	for (int i=0;i<byteData.length;i++) 
	{
		String hex=Integer.toHexString(0xff & byteData[i]);
		if(hex.length()==1) hexString.append('0');
		hexString.append(hex);
	}
	System.out.println(hexString.toString());
	
	
	String hashedPassword = hexString.toString();




	// SQL Select Command
	String sqlState = "Select username , password from staff where username = '" + username +"';"; 
	
	String driver = "com.mysql.jdbc.Driver";
	Class.forName(driver);
	Connection conn = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/project", "root", "xxxx");

	Statement st = conn.createStatement();
	ResultSet rs = st.executeQuery(sqlState);
	
	String retrievedPassword = "";
	
	
	while (rs.next()) {
		retrievedPassword = rs.getString("password");
		System.out.println(rs.getString("username"));
		System.out.println(retrievedPassword);
	}
	
	
	System.out.println(retrievedPassword);
	
	if(retrievedPassword.equals(hashedPassword)){
		System.out.println("Passwords Match");
		passwordsMatch = true;
	}
	else{
		System.out.println("Passwords Do Not Match");
	}

	//if match ()
	// return true
	//else return false
	return passwordsMatch;



}




}
