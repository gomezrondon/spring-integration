package com.gomezrondon.springintegration;


import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;




@SpringBootApplication
public class SpringIntegrationApplication implements ApplicationRunner {


	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {

		Message<String> message = MessageBuilder.withPayload("hola mundo")
				.setHeader("new header", "value heder")
				.build();


		PrintService service = new PrintService();
		service.print(message);
	}
}
