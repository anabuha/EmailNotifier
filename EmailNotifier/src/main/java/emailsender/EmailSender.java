package emailsender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages = {"rabbitmq", "GmailSender"})
public class EmailSender {

	public static void main(String[] args) {
		SpringApplication.run(EmailSender.class, args);

	}

}
