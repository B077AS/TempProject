package Email;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import Exceptions.ExceptionFrame;

public class EmailTemplate {
	public EmailTemplate(String email, String subject, String messageBody) throws Exception {
		sendMail(email, subject, messageBody);
	}

	public void sendMail(String receiver, String subject, String messageBody) throws MessagingException {
		
		System.out.println("Sending");
		Properties property =new Properties();// file di proprietà, ogni proprietà ha una key e una value
		
		property.put("mail.smtp.auth", "true");
		property.put("mail.smtp.starttls.enable", "true");
		property.put("mail.smtp.host", "smtp.office365.com");
		property.put("mail.smtp.port", "587");
		property.put("mail.smtps.ssl.enable", "true");
		
			EmailAccount ea=new EmailAccount();
			String accountEmail=ea.getEmail();
			String accountPassword=ea.getPassword();
			
			Auth a=new Auth(accountEmail, accountPassword);
			Session session =Session.getInstance(property, a);
			
			Message message =prepareMessage(session, accountEmail, receiver, subject, messageBody);

			Transport.send(message);
			System.out.println("Message sent");


	}

	private Message prepareMessage(Session session, String accountEmail, String receiver, String subject, String messageBody){
		
		Message message=new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress(accountEmail));//mittente
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));//destinatario
			message.setSubject(subject);//oggetto della mail
			message.setText(messageBody);//messaggio
			return message;
			
		} catch (AddressException e) {
			ExceptionFrame ex=new ExceptionFrame("\u274C Email not Valid");
		} catch (MessagingException e) {
			System.out.println("errore messaggio");
			e.printStackTrace();
		}
		return null;
	}
	
}

class  Auth extends Authenticator{
	private String accountEmail;
	private String accountPassword;
	public Auth(String accountEmail, String accountPassword) {
		this.accountEmail=accountEmail;
		this.accountPassword=accountPassword;
	}
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(this.accountEmail, this.accountPassword);
	}
}