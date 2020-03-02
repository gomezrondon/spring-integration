package com.gomezrondon.springintegration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class SpringIntegrationApplication   {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringIntegrationApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
	}

}
