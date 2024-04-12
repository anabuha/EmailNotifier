package GmailSender;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import util.dto.DtoEmail;

@Service
public class GmailSender {

	@Value("${gmail.email.user}")
	private String username;
	@Value("${gmail.email.pass}")
	private String password;

	public boolean sendEmail(String to, String from, DtoEmail dtoEmail, String text) {
		boolean flag = false;

		// TODO remove data from app to .prop file
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.host", "smtp.gmail.com");

		// session
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			MimeMessage message = new MimeMessage(session);
			message.setRecipient(jakarta.mail.Message.RecipientType.TO, new InternetAddress(to));
			message.setFrom(new InternetAddress(from));
			String filePath = "C:\\Users\\anape\\eclipse-workspace\\EmailNotifier\\src\\main\\resources\\templates\\email_template.html";
			
			
			String htmlContent = new String(Files.readAllBytes(Paths.get(filePath)));
			
			   Map<String, String> replacements = Map.of(
		                "{employeeName}", dtoEmail.getEmployeeName(),
		                "{managerName}", dtoEmail.getManagerName(),
		                "{message}", dtoEmail.getMessage()
		            );

		            for (Map.Entry<String, String> entry : replacements.entrySet()) {
		                htmlContent = htmlContent.replace(entry.getKey(), entry.getValue());
		            }
			message.setContent(htmlContent, "text/html");
			Transport.send(message);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

}