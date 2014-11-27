package entity;


import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	public static void SendEmail
	(String To, String Name, String Username)
	{
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,new javax.mail.Authenticator()  {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("business.spectacles@gmail.com","xxxx");
				}
			});
 
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("business.spectacles@gmail.com","Administrator @ Silver Vitality"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(To));
			message.setSubject("Silver Vitality Registration");
			
			message.setText("Welcome " + Name + "!" 
			        +"\n\n Silver Vitality Account has been created"
			        +"\n\n Login with your username (email)"
					+"\n\n Username: "+Username 
					+"\n\n Your current password is your NRIC (with CAPS)"
					+"\n\n Regards,"
					+"\n\n Silver Vitality Admnistrator"
					);
 
			Transport.send(message);
 
			System.out.println("Email Sent");
 
		} catch (UnsupportedEncodingException ex) {  
             System.out.println("UnsupportedEncodingException ex");
  
        } catch (MessagingException ex) {  
        	System.out.println("MessagingException ex");
        }  
	}
	
	
	
	
	public static void SendResetPassword
	(String To,  String newPassword) throws UnsupportedEncodingException, MessagingException
	{
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,new javax.mail.Authenticator()  {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("business.spectacles@gmail.com","minions975");
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("business.spectacles@gmail.com","Administrator @ Silver Vitality"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(To));
			message.setSubject("Your Login Details");
			
			message.setText("Dear Client," 
			        +"\n\n Your Service Portal Account password has been reseted"
			        +"\n\n Login with your username (email)"
					+"\n\n Username: "+ To
					+"\n\n Your new password is:"
					+"\n\n ==============================="
					+"\n\n "+newPassword
					+"\n\n ==============================="
					+"\n\n You are advised to change your password when you log in."
					+"\n\n Regards,"
					+"\n\n Silver Vitality Admnistrator"
					);
 
			Transport.send(message);
 
			System.out.println("Email Sent");
 
		
        }  
	
	

	public static void SendFeedbackReply
	(String To, String Name)
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
					return new PasswordAuthentication("business.spectacles@gmail.com","xxxx");
				}
			});
 
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("business.spectacles@gmail.com","Administrator @ Silver Vitality"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(To));
			message.setSubject("Feedback Reply");
			
			message.setText("Hi " + Name + "!" 
			        +"\n\n You have recieved one notification"
			        +"\n\n Our staff has replied to your feedback"
			        +"\n\n Please visit our website to view our replies"
			        +"\n\n Thank You."
					+"\n\n Regards,"
					+"\n\n Silver Vitality Admnistrator"
					);
 
			Transport.send(message);
 
			System.out.println("Email Sent");
 
		} catch (UnsupportedEncodingException ex) {  
             System.out.println("UnsupportedEncodingException ex");
  
        } catch (MessagingException ex) {  
        	System.out.println("MessagingException ex");
        }  
	}
	
	
	
	
}
