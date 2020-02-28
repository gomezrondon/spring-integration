package com.gomezrondon.springintegration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PriorityChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;




@Configuration
@EnableIntegration
public class BasicIntegrationConfig{

    public static final String INPUT_CHANNEL = "inputChannel";

    @Bean(name = INPUT_CHANNEL)
    public PriorityChannel requestChannel() {
        return MessageChannels.priority().capacity(10).get();
    }

    @Bean
    public IntegrationFlow fileMover() { // punto de entrada
        return IntegrationFlows.from(INPUT_CHANNEL)
                .bridge(e -> e.poller(Pollers.fixedRate(1000).maxMessagesPerPoll(2)))
                .handle(new PrintService(),"print") //Service Activator
                .get();
    }


/*    @Bean
    public IntegrationFlow myLambdaFlow() {
        return f -> f.channel(INPUT_CHANNEL)
                .bridge(e -> e.poller(Pollers.fixedRate(1000).maxMessagesPerPoll(3)))
                .handle(new PrintService(),"print");
    }*/

}
