package com.gomezrondon.springintegration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;


@Configuration
@EnableIntegration
public class BasicIntegrationConfig{


    @Bean(name = "messageChannel")
    public DirectChannel requestChannel() {
        return new DirectChannel();
    }




/*
    @Bean
    public IntegrationFlow fileMover() { // punto de entrada
        return IntegrationFlows.from("inputChannel")
                //.handle(new PrintService(),"print") // solo uno a la vez
                .handle(new ReverseService(),"reverse")
                .get();
    }
*/


/*    @Bean
    public IntegrationFlow myFlow() {
        return IntegrationFlows.from("inputChannel")
                //.filter("World"::equals)
                .transform("Hello "::concat)
                .handle(System.out::println)
                .get();
    }*/

/*    @Bean
    public IntegrationFlow myLambdaFlow() {
        return f -> f.channel("inputChannel")
                .transform("Hello "::concat)
                .handle(System.out::println);
    }*/

}
