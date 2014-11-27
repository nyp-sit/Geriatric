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

public class Appointment {	

	public static void createAppointment (String postal) 
	throws Exception
	
	
	{
		
		
		// Generate variables (latitude, longitude, received, deleted)
		// received and deleted = "no"
		// latitude and longitude

		String time = "1037";
		
		
		
		String latitude = GMapsRequest.SendURL(postal)[0];
		String longitude = GMapsRequest.SendURL(postal)[1];
		
		
		//Specify Driver
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);    	
    	// Execute the SQL Query
    	// Dump content into database
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "xxxx");
			Statement stmt = conn.createStatement();
			String sqlStr = "";
			sqlStr += "INSERT INTO appointment (username, date, time, place, postal, doctor, latitude, longitude) VALUES (";
			sqlStr += "'donutsandnuts@gmail.com','25/11/2014','"+time+"','"+postal+"','"+postal+"','Dr Tan','"+latitude+"','"+longitude+"');";
			
			// Debugging purposes
			System.out.println(sqlStr);
			
			// Execute The Statement
			stmt.executeUpdate(sqlStr);
			

			stmt.close();
			conn.close();
			

		}
	
	
	
	
	public static void editAppointment
	(String senior, String caregiver, String postalcode, String address, String date, String time) 
	throws Exception
	
	
	{
		// Generate variables (latitude, longitude, received, deleted)
		// received and deleted = "no"
		// latitude and longitude
		
		
		
		
		
		
		
		//Specify Driver
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);    	
    	// Execute the SQL Query
    	// Dump content into database
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "xxxx");
			Statement stmt = conn.createStatement();
			String sqlStr = "";
			sqlStr += "UPDATE appointment SET "
				   +  "postal = '' , time = '' , date='' lat='' , long= '' address= '' ;";
			
			// Debugging purposes
			System.out.println(sqlStr);
			
			// Execute The Statement
			stmt.executeUpdate(sqlStr);
			

			stmt.close();
			conn.close();
			

		}
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static void deleteAppointment (String id) 
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
			sqlStr += "DELETE FROM appointment WHERE ID = '";
			sqlStr += id;
			sqlStr += "';";
			
			System.out.println(sqlStr);
			
			
			// Debugging purposes
			System.out.println(sqlStr);
			
			// Execute The Statement
			stmt.executeUpdate(sqlStr);
			

			stmt.close();
			conn.close();
			

		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
