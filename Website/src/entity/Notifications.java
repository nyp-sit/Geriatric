package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Notifications {

	public static boolean thereIsNewNotifications() throws ClassNotFoundException, SQLException{
		boolean notif = false;

		
		
		
		

		// Database Query Here:
		// id | taken | body
		String query = "SELECT * from notification where taken = 'no';";



		//Specify Driver
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);    	
		// Execute the SQL Query
		// Dump content into database
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "xxxx");
		Statement stmt = conn.createStatement();
		Statement stmt2 = conn.createStatement();



		// Get the notification
		ResultSet resultSet = 	stmt.executeQuery(query);


		while(resultSet.next()){
			if (resultSet.getString(1) != null){
				notif = true;
			}
		}
		
		
		
		
		
		
		
		
		
		//if (){		}
		//
		//
		//


		return notif;
	}



	public static String getNotifications() throws SQLException, ClassNotFoundException{
		String notifcation = "";

		// Database Query Here:
		// id | taken | body
		String query = "SELECT * from notification where taken = 'no';";



		//Specify Driver
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);    	
		// Execute the SQL Query
		// Dump content into database
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "xxxx");
		Statement stmt = conn.createStatement();



		// Get the notification
		ResultSet resultSet = 	stmt.executeQuery(query);


		while(resultSet.next()){
			//notifcation += "id:";
			//notifcation += resultSet.getString(1);
			//notification += "taken:";
			//notification += resultSet.getString(2);
			notifcation += "body:";
			notifcation += resultSet.getString(4);
		}
		
		
		
		// Update "taken" to yes
		String query2 = "UPDATE notification SET taken ='yes';";
		PreparedStatement updateNotification = conn.prepareStatement(query2);
		updateNotification.execute();
		
	

		conn.close();
		stmt.close();
		updateNotification.close();
		
		

		return notifcation;
	}

	
	
	
	
	
	
	
	
	
	
	

	public static void createNotifications(String notifcation) throws Exception{

		

		//Specify Driver
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);    	
		// Execute the SQL Query
		// Dump content into database
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "xxxx");
		Statement stmt = conn.createStatement();

		
		String query1 = "INSERT INTO notification (body) VALUES('" + notifcation+ "');";
		PreparedStatement pstm = conn.prepareStatement(query1);
		pstm.execute();
		

		conn.close();
		stmt.close();
		pstm.close();
		

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	






}
