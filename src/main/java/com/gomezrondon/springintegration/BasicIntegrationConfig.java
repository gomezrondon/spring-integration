package com.gomezrondon.springintegration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.messaging.MessageHandler;

import java.io.File;
import java.util.concurrent.TimeUnit;


@Configuration
@EnableIntegration
public class BasicIntegrationConfig{
 // https://docs.spring.io/spring-integration/reference/html/file.html
   @Autowired
   private FilePrinter filePrinter;

    public static final String INPUT_DIR = "C:\\Users\\jrgm\\Downloads\\spring-integration\\source";
    public static final String OUTPUT_DIR = "C:\\Users\\jrgm\\Downloads\\spring-integration\\destino";
    public static final String INPUT_CHANNEL = "printChannel";


    @Bean(name = INPUT_CHANNEL)
    public DirectChannel requestChannel() {
        return new DirectChannel();
    }


    @Bean
    public IntegrationFlow fileReadingFlow() {
        return IntegrationFlows
                .from(Files.inboundAdapter(new File(INPUT_DIR))
                                .patternFilter("*.txt"),
                        e -> e.poller(Pollers.fixedRate(1, TimeUnit.SECONDS, 1)))
                .handle(Files.outboundAdapter(new File(OUTPUT_DIR)))
                .get();
    }

}