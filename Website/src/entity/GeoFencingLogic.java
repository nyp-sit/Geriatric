package entity;


import java.sql.*;

public class GeoFencingLogic {
	
	
	

	// Now perform logical comparison and 
	// immediately send notification
	// -------------------------------------------
	// if (proximity +- ) 
    //	    { // Send good notification to DB}
	// 
	// else  { // Send bad notification to DB}
	// 
	// 
	
	
	
	public static void verifyGeoFence(String inLat, String inLong) throws Exception{
		
		
		boolean satisfyCriteria = false;
		
		
		Class.forName("com.mysql.jdbc.Driver");
		

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "xxxx");
        Statement statement = connection.createStatement() ;

        String dbQuery = "SELECT latitude,longitude from appointment WHERE computed = 'no';";

        ResultSet rset = statement.executeQuery(dbQuery) ; 
		System.out.println(dbQuery);
		
		
		
		
		
		
		
		
		String DBlatitude = "";
		String DBlongitude = "";

		// Get from just one from the database 
		// 
		
		
		
		while (rset.next()){
				DBlatitude = rset.getString(1);
		 		DBlongitude = rset.getString(2);
		}
		

	
		
		System.out.println(DBlatitude);
		System.out.println(DBlongitude);
		
		
		
		double DBlatitudeDouble = Double.parseDouble(DBlatitude);
		double DBlongitudeDouble = Double.parseDouble(DBlongitude);
		
		
		double inLatDouble = Double.parseDouble(inLat);
		double inLongDouble = Double.parseDouble(inLong);
		
		
		
		// 0.001 Latitude  = Approximate Distance: 111.19 meters
		// 0.001 Longitude = Approximate Distance: 107.65 meters
		if(DBlatitudeDouble  - inLatDouble  <  0.002  &
		   DBlatitudeDouble  - inLatDouble  > -0.002  &
		   DBlongitudeDouble - inLongDouble <  0.002  &
		   DBlongitudeDouble - inLongDouble > -0.002) 
		{
			satisfyCriteria = true;
		}
		
		
		// Now Create Notifications
		if (satisfyCriteria == true){
			Notifications.createNotifications("Appointment Success");
		}
		else {
			Notifications.createNotifications("Appointment Failed");
		}
		
	}
	
}
