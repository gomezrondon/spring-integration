package com.gomezrondon.springintegration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dispatcher.LoadBalancingStrategy;
import org.springframework.integration.dispatcher.RoundRobinLoadBalancingStrategy;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;



@Configuration
@EnableIntegration
public class BasicIntegrationConfig{


    public static final String INPUT_CHANNEL = "inputChannel";

    @Bean(name = INPUT_CHANNEL)
    public DirectChannel requestChannel() {
        DirectChannel directChannel = new DirectChannel();
        return directChannel;
    }

    @Bean
    public IntegrationFlow flowHandler1() { // punto de entrada
        return IntegrationFlows.from(INPUT_CHANNEL)
                .handle(new UppercasePrintService(),"print") //Service Activator
                .get();
    }

    @Bean
    public IntegrationFlow flowHandler2() { // punto de entrada
        return IntegrationFlows.from(INPUT_CHANNEL)
                .handle(new PrintService(),"print") //Service Activator
                .get();
    }


/*    @Bean
    public IntegrationFlow myLambdaFlow() {
        return f -> f.channel("inputChannel")
                .transform("Hello "::concat)
                .handle(System.out::println);
    }*/

}
