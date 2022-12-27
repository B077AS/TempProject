package Email;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.Properties;
import Exceptions.*;


public class EmailBuilder {
	private int number;
	private String email;
	public EmailBuilder(String email, int number) {
		this.email=email;
		this.number=number;
		
		try {
			sendMail(this.email);
		} catch (MessagingException e) {
			System.out.println("errore creazione emailbuilder");
			//TODO ECCEZIONE
		}
	}

	public void sendMail(String ricevitore) throws MessagingException {
		
		System.out.println("Sending");
		Properties property =new Properties();// file di proprietà, ogni proprietà ha una key e una value
		
		property.put("mail.smtp.auth", "true");
		property.put("mail.smtp.starttls.enable", "true");
		property.put("mail.smtp.host", "smtp.office365.com");
		property.put("mail.smtp.port", "587");
		property.put("mail.smtps.ssl.enable", "true");
		
		try {
			EmailAccount ea=new EmailAccount("Files/EmailDetails.csv");
			String accountEmail=ea.getEmail();
			String accountPassword=ea.getPassword();
			
			Auth a=new Auth(accountEmail, accountPassword);
			
			Session session =Session.getInstance(property, a);
			
			Message message =prepareMessage(session, accountEmail, ricevitore, number);//preparo l'oggetto messaggio che contiene il messaggio e destinatario
			
			Transport.send(message);
			System.out.println("Message sent");
		} catch (FileNotFoundException e) {
			System.out.println("file non trovato");
		} catch (IOException e) {
		}

	}

	private Message prepareMessage(Session session, String accountEmail, String ricevitore, int number) {
		
		Message message=new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress(accountEmail));//mittente
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(ricevitore));//destinatario
			message.setSubject("Verifica Account");//oggetto della mail
			message.setText("Codice Conferma: "+number);//messaggio
			return message;
			
		} catch (AddressException e) {
			EmailFormatEx ex=new EmailFormatEx();
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