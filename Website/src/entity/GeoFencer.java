package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GeoFencer {
	
	
	
	public static void receiveGeolocation(String latitude, String longitude) throws Exception{
		
		// Generate Current Date
		// Generate Current Time
		// Dump (Date | Time | Latitude | Longitude) into Database
		
		


		//Specify Driver
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		

    	
    	// Get Current Date
    	// Create an instance of SimpleDateFormat used for formatting 
    	// the string representation of date (month/day/year)
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    	DateFormat tf = new SimpleDateFormat("HH-mm-ss");
    	// Get the date today using Calendar object.
    	Date today = Calendar.getInstance().getTime();  
    	Date now = new Date();
    	// Using DateFormat format method we can create a string 
    	// representation of a date with the defined format.

    	String date = df.format(today);
    	String time = tf.format(now);

		
		if(latitude != null && longitude != null){
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "xxxx");
			Statement stmt = conn.createStatement();
			String sqlStr = "";


			// Works
			//sqlStr += "INSERT INTO `project`.`geolocation` (`date`, `time`, `lat`, `long`) VALUES ('222', '333', '444', '5555');";
			


			sqlStr += "INSERT INTO geolocation (`date`, `time`, `lat`, `long`) VALUES ('"+date+"', '"+time+"', '"+latitude+"', '"+longitude+"');";
			
			
			//sqlStr += "INSERT INTO geolocation (date, time, lat, long) VALUES ('aaaaaaaaaa', 'aaaaaaaaa', 'aaaaaaaaaa', 'aaaaaaaaa');";
			
			
			
			//	sqlStr += "INSERT INTO geolocation (date, time, lat, long) VALUES ('"+date+"', '"+time+"', '"+latitude+"', '"+longitude+"');";
			
			System.out.println(sqlStr);
          
			stmt.executeUpdate(sqlStr);
			
			// Debugging purposes
			System.out.println("Query Statement : " + sqlStr);

			stmt.close();
			conn.close();

		
		
		}
		

		
		
		
	}
	
	
	
}
