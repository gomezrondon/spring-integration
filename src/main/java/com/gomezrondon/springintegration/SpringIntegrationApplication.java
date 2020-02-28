package com.gomezrondon.springintegration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;




@SpringBootApplication
public class SpringIntegrationApplication implements ApplicationRunner {

	@Autowired
	private DirectChannel inputChannel;

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {


		Message<String> message = MessageBuilder.withPayload("hola mundo")
				.setHeader("new header", "value heder")
				.build();



		MessagingTemplate template = new MessagingTemplate();
		Message<?> message1 = template.sendAndReceive(inputChannel, message);

		System.out.println(">>>>>>>> "+message1.getPayload());

	}
}
