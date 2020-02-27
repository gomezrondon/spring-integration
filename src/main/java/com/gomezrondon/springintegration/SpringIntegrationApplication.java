package com.gomezrondon.springintegration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;




@SpringBootApplication
public class SpringIntegrationApplication implements ApplicationRunner {


	@Autowired
	private DirectChannel channel;

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationApplication.class, args);
	}

	@Bean
	public MessageChannel fileChannel() {
		return new DirectChannel();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		//channel.subscribe(message -> new PrintService().print((Message<String>) message));

		Message<String> message = MessageBuilder.withPayload("hola mundo")
				.setHeader("new header", "value heder")
				.build();

	//	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> "+channel.getFullChannelName());

		channel.send(message);
	}
}
