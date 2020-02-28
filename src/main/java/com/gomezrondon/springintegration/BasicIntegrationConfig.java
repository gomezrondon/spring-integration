package com.gomezrondon.springintegration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;


@Configuration
@EnableIntegration
public class BasicIntegrationConfig{

    @Bean(name = "messageChannel")
    public DirectChannel requestChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow fileMover() { // punto de entrada
        return IntegrationFlows.from("messageChannel")
                .publishSubscribeChannel(pubSub -> pubSub
                        .subscribe(flow -> flow.handle(new PrintService(),"print"))

                        .subscribe(flow -> flow.handle(new UppercasePrintService(),"print"))
                )
                .get();
    }


/*    @Bean
    public IntegrationFlow myLambdaFlow() {
        return f -> f.channel("inputChannel")
                .transform("Hello "::concat)
                .handle(System.out::println);
    }*/

}
