package entity;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.math.BigInteger;


public class Login2FA {
	
	
	
	public static String generateOTP(String username) throws ClassNotFoundException, SQLException{
		
		System.out.println("=============================================");
		System.out.println("Generate OTP executed");
		System.out.println("=============================================");
		
		
		
		
		//Generate Random ID Number
		SecureRandom randomID = new SecureRandom();
		String id = new BigInteger(100, randomID).toString(32);
		System.out.println(id);
		
		
		
		//Generate OTP
		SecureRandom random = new SecureRandom();
		String otp = new BigInteger(130, random).toString(32);
		System.out.println(otp);
		
		
		
		//Get Date
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		System.out.println(date);
		
		
		
		//Get Time In
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		Calendar cal2 = Calendar.getInstance();
		String timeIn = timeFormat.format(cal2.getTime());
		System.out.println(timeIn);
		
		
		
		
		//Get Time Expired (5 minutes later)
		cal2.add(Calendar.MINUTE, 5);
		String timeExpiry = timeFormat.format(cal2.getTime());
		System.out.println(timeExpiry);
		
		
		String sqlCommand = "INSERT INTO twofactor (id, username, otp, timein, timeexpiry, date) "
				          + "VALUES ('"+id+"','"+username+ "','" +otp+ "','" +timeIn+ "','" +timeExpiry+"','"+date+"');";
		
		System.out.println(sqlCommand);
		
		//Specify Driver
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "xxxx");
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sqlCommand);
		
		
		
		// Debugging purposes
		stmt.close();
		conn.close();
		
		
		
		
		//Send The OTP Email
		//Login2FA.SendOTP(username, username, otp, timeIn, timeExpiry);
		
		
		
		return id;
	}
	
	
	
	

	
	// Method to check the 2FA OTP => Returns true / false
	public static boolean checkOTP(String id, String otp) throws SQLException, ClassNotFoundException, ParseException{
		boolean check = false;
		
		System.out.println("=============================");
		System.out.println("checkOTP static method executed");
		System.out.println("=============================");
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "xxxx");
		Statement statement = conn.createStatement();
		String sqlCommand = "SELECT * from twofactor WHERE id='"+id+"';";
		ResultSet rset = statement.executeQuery(sqlCommand);
		
		System.out.println(sqlCommand);
		
		
		
		
		String username = "";
		String dbOTP = "";
		String timeExpiry = "";
		String date = "25/11/2014";
		
		while(rset.next()){
		username = rset.getString("username");
		dbOTP = rset.getString("otp");
		timeExpiry = rset.getString("timeexpiry");
		System.out.println("usenname="+username);
		System.out.println("dbOTP="+dbOTP);
		System.out.println("timeExpiry="+timeExpiry);
		System.out.println("date="+date);
		}
		
		System.out.println("username="+username);
		
		System.out.println("dbOTP="+dbOTP);
		System.out.println("OTP="+otp);
		
		
		System.out.println("timeExpiry="+timeExpiry);
		System.out.println("date="+date);
		
		
		//Verify that OTP is correct
		if(otp.equals(dbOTP)){
			check = true;
			System.out.println("Correct OTP");
		}
		
	
		return check;
	}
	
	
}
