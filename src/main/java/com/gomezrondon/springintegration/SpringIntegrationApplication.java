package com.gomezrondon.springintegration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;




@SpringBootApplication
public class SpringIntegrationApplication implements ApplicationRunner {

	@Autowired
	@Qualifier(value = "queuechannel") // this is necessary
	private MessageChannel channel;

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		for (int x = 0; x < 10; x++) {
			Message<String> message = MessageBuilder.withPayload("Payload: "+x).setHeader("number",x).build();
			channel.send(message);
		}

	}
}
