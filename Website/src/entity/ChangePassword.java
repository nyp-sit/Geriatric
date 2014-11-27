package entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class ChangePassword {
	
	
	public static boolean compareClientNRIC(String username,String nric) 
			throws SQLException, ClassNotFoundException{
		        //Overview
				//Declare boolean variable
				//Retrieve NRIC from database
				//Compare the two NRICs
				//Return the boolean value
				
		        //Declare boolean
				boolean compare = false;
				//Retrieve old password from database
				String sqlCommand = "SELECT nric from client WHERE username ='"+username+"';";
				String driver = "com.mysql.jdbc.Driver";
				Class.forName(driver);
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/project", "root", "xxxx");

				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sqlCommand);
				
				String retrievedNRIC = "";
				
				while (rs.next()) {
					retrievedNRIC = rs.getString("nric");
					System.out.println(retrievedNRIC);
				}
				System.out.println(retrievedNRIC);
				
				if(retrievedNRIC.equals(nric)){
					compare = true;
				}
				
				return compare;
	}
	
	
	
	public static boolean compareOldClientPassword(String username,String old) throws 
	NoSuchAlgorithmException, ClassNotFoundException, SQLException{
		
		//Overview
		//Declare boolean variable
		//Hash old password
		//Retrieve old password from database
		//Compare the two passwords
		//Return the boolean value
		
		boolean compare = false;
		
		
		// SQL Select Statement to hash password
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(old.getBytes());
		byte byteData[] = md.digest();
		//Convert the byte to hex format method 2
		StringBuffer hexString = new StringBuffer();
		for (int i=0;i<byteData.length;i++) 
		{
			String hex=Integer.toHexString(0xff & byteData[i]);
			if(hex.length()==1) hexString.append('0');
			hexString.append(hex);
		}
		System.out.println(hexString.toString());
		
		String hashedOldPassword = hexString.toString();
		
		
		
		
		//Retrieve old password from database
		String sqlCommand = "SELECT password from client WHERE username ='"+username+"';";
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/project", "root", "xxxx");

		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sqlCommand);
		
		String retrievedPassword = "";
		
		while (rs.next()) {
			retrievedPassword = rs.getString("password");
			System.out.println(retrievedPassword);
		}
		System.out.println(retrievedPassword);
		
		
		//Compare retreivedPassword and hashedOldPassword
		if(retrievedPassword.equals(hashedOldPassword)){
			compare = true;
		}
		
		return compare;
	}
	
	public static boolean compareOldStaffPassword(String username, String old) throws 
	NoSuchAlgorithmException, ClassNotFoundException, SQLException{
		
		//Overview
		//Declare boolean variable
		//Hash old password
		//Retrieve old password from database
		//Compare the two passwords
		//Return the boolean value
		
		boolean compare = false;
		
		
		// SQL Select Statement to hash password
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(old.getBytes());
		byte byteData[] = md.digest();
		//Convert the byte to hex format method 2
		StringBuffer hexString = new StringBuffer();
		for (int i=0;i<byteData.length;i++) 
		{
			String hex=Integer.toHexString(0xff & byteData[i]);
			if(hex.length()==1) hexString.append('0');
			hexString.append(hex);
		}
		System.out.println(hexString.toString());
		
		String hashedOldPassword = hexString.toString();
		
		
		
		
		//Retrieve old password from database
		String sqlCommand = "SELECT password from staff WHERE username ='"+username+"';";
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/project", "root", "xxxx");

		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sqlCommand);
		
		String retrievedPassword = "";
		
		while (rs.next()) {
			retrievedPassword = rs.getString("password");
			System.out.println(retrievedPassword);
		}
		System.out.println(retrievedPassword);
		
		
		//Compare retreivedPassword and hashedOldPassword
		if(retrievedPassword.equals(hashedOldPassword)){
			compare = true;
		}
		
		return compare;
	}
	
	
	public static void changeClientPassword(String username, String password) throws 
	SQLException, ClassNotFoundException, NoSuchAlgorithmException{
		//Overview
		//Hash the password
		//Update the database
		
		
		// Hash password
		// Then insert with SQL Select Statement
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				md.update(password.getBytes());
				byte byteData[] = md.digest();
				
		//Convert the byte to hex format method 2
				StringBuffer hexString = new StringBuffer();
				for (int i=0;i<byteData.length;i++) 
				{
					String hex=Integer.toHexString(0xff & byteData[i]);
					if(hex.length()==1) hexString.append('0');
					hexString.append(hex);
				}
				System.out.println(hexString.toString());
				
				String hashedNewPassword = hexString.toString();
				
				
				
		//Update the database
				String sqlCommand = "UPDATE client SET password ='"+hashedNewPassword+"' WHERE username ='"+username+"';";
				String driver = "com.mysql.jdbc.Driver";
				Class.forName(driver);
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/project", "root", "xxxx");
				
				PreparedStatement updatePassword = conn.prepareStatement(sqlCommand);
				updatePassword.execute();
	}
	
	
	
	
	
	
	public static void changeStaffPassword(String username, String password) throws 
	SQLException, ClassNotFoundException, NoSuchAlgorithmException{
		//Overview
		//Hash the password
		//Update the database
		
		
		// SQL Select Statement to hash password
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				md.update(password.getBytes());
				byte byteData[] = md.digest();
				//Convert the byte to hex format method 2
				StringBuffer hexString = new StringBuffer();
				for (int i=0;i<byteData.length;i++) 
				{
					String hex=Integer.toHexString(0xff & byteData[i]);
					if(hex.length()==1) hexString.append('0');
					hexString.append(hex);
				}
				System.out.println(hexString.toString());
				
				String hashedNewPassword = hexString.toString();
				
				
				
				//Update the database
				String sqlCommand = "UPDATE staff SET password ='"+hashedNewPassword+"' WHERE username ='"+username+"';";
				String driver = "com.mysql.jdbc.Driver";
				Class.forName(driver);
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/project", "root", "xxxx");
				
				PreparedStatement updatePassword = conn.prepareStatement(sqlCommand);
				updatePassword.execute();
	}
	
	
	
	
	
	
}
