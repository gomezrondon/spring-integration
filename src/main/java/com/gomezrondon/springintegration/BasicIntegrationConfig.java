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

import java.util.concurrent.TimeUnit;


@Configuration
@EnableIntegration
public class BasicIntegrationConfig{

    @Autowired
    private PersonDirectoryService service;

    public static final String PRINT_CHANNEL = "printChannel";
    public static final String UPPERCASE_CHANNEL = "uppercaseChannel";



    @Bean(name = PRINT_CHANNEL)
    public DirectChannel requestChannel() {
        return new DirectChannel();
    }


    @Bean
    public IntegrationFlow inboundChannelAdapter() {
        return IntegrationFlows.from(service,"findNewPeople", e -> e.poller(Pollers.fixedRate(1, TimeUnit.SECONDS, 1)))
                .channel(PRINT_CHANNEL)
                .get();
    }

    @Bean
    public IntegrationFlow fileMover() { // punto de entrada
        return IntegrationFlows.from(PRINT_CHANNEL)
                .handle(new PrintService()) //Service Activator
                .get();
    }

    @Bean(name = UPPERCASE_CHANNEL)
    public DirectChannel responseChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow fileMover2() { // punto de entrada
        return IntegrationFlows.from(UPPERCASE_CHANNEL)
                .handle(new UppercaseService(), "execute") //Service Activator
              //   .channel(OUTPUT_CHANNEL) //is in the replyChannel
                .get();
    }



}