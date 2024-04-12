package rabbitmq;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import GmailSender.GmailSender;
import util.dto.DtoEmail;

@Service
public class RabbitMQConsumer {

	@Autowired
	GmailSender gmailSender;
	@Value("${gmail.email.user}")
    private String emailSender;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.email.name}"})
    public void consume(String listEmail) throws JsonMappingException, JsonProcessingException{
    	ObjectMapper objectMapper = new ObjectMapper();

		// Deserijalizacija JSON stringa u listu objekata
		List<DtoEmail> listOfEmployees = objectMapper.readValue(listEmail,
				new TypeReference<List<DtoEmail>>() {
				});

		for (DtoEmail dtoEmail : listOfEmployees) {
			
			gmailSender.sendEmail(dtoEmail.getEmail(), emailSender, dtoEmail, dtoEmail.getMessage());
			
			LOGGER.info(String.format("Zaposleni: %s je obavesten da je projekat izmenjen putem email: %s",dtoEmail.getEmployeeName(), dtoEmail.getEmail()));
		}
    }
}