package ar.edu.itba.it.paw.utils;


public class MailSenderr {

	public static boolean send(String msg, String to, String subject) {

//		try {
//
//			String current = new java.io.File(".").getCanonicalPath();
//			FileInputStream fis = new FileInputStream(current
//					+ "/src/main/resources/mail.properties");
//
//			final Properties props = new Properties();
//			props.load(fis);
//
//			Session session = Session.getInstance(props,
//					new javax.mail.Authenticator() {
//						protected PasswordAuthentication getPasswordAuthentication() {
//
//							return new PasswordAuthentication(
//									"noreply.sentiteguapa@gmail.com",
//									"tatateta");
//						}
//					});
//
//			MimeMessage message = new MimeMessage(session);
//			message.setFrom(new InternetAddress(
//					"noreply.sentiteguapa@gmail.com"));
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
//					to));
//			message.setSubject(subject);
//			message.setText(msg);
//
//			Transport.send(message);
//
//		} catch (Exception e) {
//			return false;
//		}
		return true;
	}

}
