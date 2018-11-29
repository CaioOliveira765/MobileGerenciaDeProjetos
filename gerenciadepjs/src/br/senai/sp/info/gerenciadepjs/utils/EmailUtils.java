package br.senai.sp.info.gerenciadepjs.utils;

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

public class EmailUtils {

	public static final String remetente = "equipesenaitarde123@gmail.com";

	public static final String senhaRemetente = "senai@132";

	private static Session getMailSession() {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(remetente, senhaRemetente);
			}
		});
		return session;
	}

	public static boolean enviarEmail(String titulo, String corpo, String destinatario)
			throws AddressException, MessagingException {
		try {
			Message msg = new MimeMessage(getMailSession());

			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));

			msg.setFrom(new InternetAddress(remetente));

			msg.setSubject(titulo);

			msg.setText(corpo);

			Transport.send(msg);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
