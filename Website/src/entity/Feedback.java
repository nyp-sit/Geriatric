package entity;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Feedback {
	
	

	public static void createFeedback
	(String subject, String content, String technical, String ip, String feedback, String access, String other) 
	throws ClassNotFoundException, NoSuchAlgorithmException, SQLException
	
	
	{
		//Specify Driver
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		
		
		
    	
    	
    	// Get Current Date
    	//==================================================================
    	// Create an instance of SimpleDateFormat used for formatting 
    	// the string representation of date (month/day/year)
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    	// Get the date today using Calendar object.
    	Date today = Calendar.getInstance().getTime();        
    	// Using DateFormat format method we can create a string 
    	// representation of a date with the defined format.
    	// Print what date is today!
    	String datetime = df.format(today);
    	System.out.println("Date Joined : " + datetime);
    	//==================================================================
		
    	
    	
    	
    	
    	
    	// Execute the SQL Query
    	// Dump content into database
    	
		if(subject != null && content != null){
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "xxxx");
			Statement stmt = conn.createStatement();
			String sqlStr = "";
			sqlStr += "INSERT INTO feedback (subject, content, technical, ip, feedback, access, other, datetime) VALUES (";
			sqlStr += "'"+subject+"','"+content+"','"+technical+"','"+ip+"','"+feedback+"','"+access+"','"+other+"','"+datetime+"');";
			
			System.out.println(sqlStr);
          
			stmt.executeUpdate(sqlStr);
			
			// Debugging purposes
			System.out.println("Query Statement : " + sqlStr);

			stmt.close();
			conn.close();
			
			
			
			// Send mail to thank client for feedback
			//SendMail.SendEmail(username, name, username);
			
			
			
			
		}
		
		
		}
	
	
	
	
	
	
	
	
	
	
	

}
