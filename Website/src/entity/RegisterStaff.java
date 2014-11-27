package entity;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import entity.SendMail;


public class RegisterStaff {

	public static boolean registerNewStaff
	(String username, String nric, String name, String contact, String address, String email, String dateofbirth) throws ClassNotFoundException, NoSuchAlgorithmException, SQLException
	{
		//Specify Driver
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		
		
		
		
		// SHA 256 hashing of password (NRIC)
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(nric.getBytes());
        byte byteData[] = md.digest();
      //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        System.out.println("Hex format : " + sb.toString());
      //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<byteData.length;i++) {
    		String hex=Integer.toHexString(0xff & byteData[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
    	
		
    	String hashedpassword = hexString.toString();
    	
    	System.out.println("hashedpassword = : " + hashedpassword);
    	
    	
    	
    	
    	// Get Current Date
    	
    	// Create an instance of SimpleDateFormat used for formatting 
    	// the string representation of date (month/day/year)
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    	// Get the date today using Calendar object.
    	Date today = Calendar.getInstance().getTime();        
    	// Using DateFormat format method we can create a string 
    	// representation of a date with the defined format.

    	// Print what date is today!
    	
    	String datejoined = df.format(today);
    	
    	System.out.println("Date Joined : " + datejoined);
    	
    	
    	String role = "staff";
		
		if(name != null && nric != null && name != null){
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "xxxx");
			//PreparedStatement pstatement = null;
			Statement stmt = conn.createStatement();
			String sqlStr = "";
			sqlStr += "INSERT INTO staff (username, password, name, contact, address, email, datecreated, dateofbirth, nric, role) VALUES (";
			sqlStr += "'"+username+"','"+hashedpassword+"','"+name+"','"+contact+"','"+address+"','"+email+"','"+datejoined+"','"+dateofbirth+"','"+nric+"','"+role+"');";

          
			stmt.executeUpdate(sqlStr);
			
			// Debugging purposes
			System.out.println("Query Statement : " + sqlStr);

			stmt.close();
			conn.close();
			
			
			SendMail.SendEmail(username, name, username);
			
			return true;
			
			
			
		}
		return false;
		
		
		}
	
}
