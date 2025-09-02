package vn.khanhduy.utils;

import java.util.Properties;
import java.util.Random;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import vn.khanhduy.models.UserModel;

public class Email {
	//Ham sinh ma code ngau nhien
		public String getRandom() {
			Random rdm = new Random();
			int number = rdm.nextInt(999999);//0 -> 999999
			return String.format("%06d", number);//so 6 ky tu kieu chuoi
		}

		//send email to user email
		public boolean sendCodeTodMail(UserModel user) {
			boolean test = false;
			
			String toEmail = user.getEmail();
			String fromEmail = "khanhduyspkt@gmail.com";
			String password = "12345678";
			
			try {
				//your host email smtp server details
				Properties pr = configEmail(new Properties());
				//get session to authenticate to host email address and password
				Session session = Session.getInstance(pr, new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(fromEmail, password);
					}
				});
				
				//set email message details
				Message mess = new MimeMessage(session);
				mess.setHeader("Content-Type", "text/plain; charset=UTF-8");
				
				//set from email address
				mess.setFrom(new InternetAddress(fromEmail));
				
				//set to email address
				mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
				
				mess.setSubject("Your code");
				
				//mess.setText("User code: " + user.getCode());
				
				//send the message
				Transport.send(mess);
				test = true;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return test;
		}
		
		public boolean sendPasswordToEmail(UserModel user) {
			boolean test = false;
			
			String toEmail = user.getEmail();
			String fromEmail = "volekhanhduy26032005@gmail.com";
			String password = "qzsmnqguocpvntec";
			
			try {
				//your host email smtp server details
				Properties pr = configEmail(new Properties());
				//get session to authenticate to host email address and password
				Session session = Session.getInstance(pr, new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(fromEmail, password);
					}
				});
				
				//set email message details
				Message mess = new MimeMessage(session);
				mess.setHeader("Content-Type", "text/plain; charset=UTF-8");
				
				//set from email address
				mess.setFrom(new InternetAddress(fromEmail));
				
				//set to email address
				mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
				
				mess.setSubject("Your password");
				
				mess.setText("User password: " + user.getPassWord());
				
				//send the message
				Transport.send(mess);
				test = true;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return test;
		}
		
		public Properties configEmail(Properties pr) {
			//your host email smtp server details 
			pr.setProperty("mail.smtp.host", "smtp.gmail.com");
			pr.setProperty("mail.smtp.port", "587");
			pr.setProperty("mail.smtp.auth", "true");
			pr.setProperty("mail.smtp.starttls.enable", "true");
			//pr.put("mail.smtp.socketFactory.port", "587");
			//pr.put("mail.smtp.socketFactory.class", "jakarta.net.ssl.SSLSocketFactory");
			return pr;
		}
}
