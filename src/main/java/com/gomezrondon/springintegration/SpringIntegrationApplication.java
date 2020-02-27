package com.gomezrondon.springintegration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;

import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
public class SpringIntegrationApplication implements ApplicationRunner {


	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("key", "value");
		MessageHeaders headers = new MessageHeaders(map);
		Message<String> message =  new GenericMessage("Hola Mundo",headers);


		PrintService service = new PrintService();
		service.print(message);
	}
}
