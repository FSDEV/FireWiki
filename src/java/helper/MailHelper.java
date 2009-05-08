package helper;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Used to send email.
 *
 * @author cmiller
 */
public class MailHelper {

	private static Session s;

	public static boolean send(String message, String subject, String address) {
		try {
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.port", "465");
			props.setProperty("mail.smtp.auth", "true");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.setProperty("mail.smtp.starttls", "true");
			props.setProperty("mail.smtp.quitwait", "false");
			props.setProperty("mail.smtp.user", MailCredentials.UserName.getVal());
			props.setProperty("mail.smtp.password", MailCredentials.Password.getVal());
			if (s == null) {
				s = Session.getDefaultInstance(props,
						new javax.mail.Authenticator() {
					
							protected PasswordAuthentication getPasswordAuthentication()
								{ return new PasswordAuthentication("firewikibot@gmail.com","botsn0getin&");	}
						});
			}
			Message m = new MimeMessage(s);
			m.setSubject(subject);
			m.setText(message);
			m.setFrom(new InternetAddress(props.getProperty("mail.smtp.user")));
			m.setRecipient(Message.RecipientType.TO, new InternetAddress(address));
			Transport t = s.getTransport();
			//t.connect(
			//		props.getProperty("mail.smtps.host"),
			//		Integer.parseInt(props.getProperty("mail.smtps.port")),
			//		props.getProperty("mail.smtps.user"),
			//		props.getProperty("mail.smtps.password"));
			t.connect(props.getProperty("mail.smtp.user"), props.getProperty("mail.smtp.password"));
			t.send(m);
			return true;
		} catch (MessagingException ex) {

			Logger.getLogger(MailHelper.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	/*
	if(mailServer==null)
			mailServer = new GreenMail(ServerSetup.SMTP);
		mailServer.start();
		try {
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", "localhost");
			props.put("mail.smtp.port", "25");

			MimeMessage m = mailServer.util().newMimeMessage(message.toString());
			m.setFrom(new InternetAddress("noreply@localhost.localdomain"));
			m.addRecipients(RecipientType.TO, u.getEmail());
			m.setSubject("FireWiki Activation");

		} catch (MessagingException ex) {
			Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
		}*/
}
