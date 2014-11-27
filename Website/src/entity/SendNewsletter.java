package entity;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendNewsletter {
	@SuppressWarnings("unchecked")
	public static void SendAnnouncement(String inputMessage) throws SQLException, ClassNotFoundException{
		{
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			
			Session session = Session.getInstance(props,new javax.mail.Authenticator()  {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("business.spectacles@gmail.com","minions975");
					}
				});
	 
			//Get an array list of all the email addresses from the client database
			String sqlStatement = "SELECT username from client";
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "xxxx");
			Statement stmt = conn.createStatement();
			System.out.println("Query statement is " + sqlStatement);
			ResultSet rset = stmt.executeQuery(sqlStatement);
			
			@SuppressWarnings("rawtypes")
			ArrayList emailList = new ArrayList<String>();
			
			while(rset.next()){
				emailList.add(rset.getString("username").toString());
			}
			System.out.println("Contents of Array List: "+emailList);
			
			
			
			
			for(int i=0;i<emailList.size();i++){
				String To = (String) emailList.get(i);
				try {
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress("business.spectacles@gmail.com","Administrator @ Service Portal"));
					message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(To));
					message.setSubject("Announcement");
					
					message.setText(inputMessage+"\n\n Regards,"+"\n\n Service Portal Admnistrator");
		 
					Transport.send(message);
		 
					System.out.println("Email Sent");
		 
				} catch (UnsupportedEncodingException ex) {  
		             System.out.println("UnsupportedEncodingException ex");
		  
		        } catch (MessagingException ex) {  
		        	System.out.println("MessagingException ex");
		        }  
			}
			}
			
			
		
	}
	
	
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public static void SendLetterWithAttachment(String inputMessage, String filePath) throws SQLException, ClassNotFoundException{

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		Session session = Session.getInstance(props,new javax.mail.Authenticator()  {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("business.spectacles@gmail.com","minions975");
				}
			});
 
		//Get an array list of all the email addresses from the client database
		String sqlStatement = "SELECT username from client";
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "xxxx");
		Statement stmt = conn.createStatement();
		System.out.println("Query statement is " + sqlStatement);
		ResultSet rset = stmt.executeQuery(sqlStatement);
		
		@SuppressWarnings("rawtypes")
		ArrayList emailList = new ArrayList<String>();
		
		while(rset.next()){
			emailList.add(rset.getString("username").toString());
		}
		System.out.println("Contents of Array List: "+emailList);
		
		
		
		
		for(int i=0;i<emailList.size();i++){
			String To = (String) emailList.get(i);
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress("business.spectacles@gmail.com","Administrator @ Service Portal"));
				message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(To));
				message.setSubject("Announcement");
				message.setSentDate(new Date());
				
				//message.setText(inputMessage+"\n\n Regards,"+"\n\n Service Portal Admnistrator");
				
				MimeBodyPart messagePart = new MimeBodyPart();
				messagePart.setText(inputMessage+"\n\n Regards,"+"\n\n Service Portal Admnistrator");

				

				// Set the email attachment file
				MimeBodyPart attachmentPart = new MimeBodyPart();
				FileDataSource fileDataSource = new FileDataSource(filePath) {
					public String getContentType() {return "application/octet-stream";}
				};
				attachmentPart.setDataHandler(new DataHandler(fileDataSource));
				attachmentPart.setFileName(fileDataSource.getName());
				// Add all parts of the email to Multipart object
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messagePart);
				multipart.addBodyPart(attachmentPart);
				message.setContent(multipart);

				
				
				
				
				
				Transport.send(message);
	 
				System.out.println("Email Sent");
	 
			} catch (UnsupportedEncodingException ex) {  
	             System.out.println("UnsupportedEncodingException ex");
	  
	        } catch (MessagingException ex) {  
	        	System.out.println("MessagingException ex");
	        }  
		}
	}

	
}