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


    public static final String INPUT_CHANNEL = "inputChannel";
    public static final String OUTPUT_CHANNEL = "outputChannel";

    @Bean(name = INPUT_CHANNEL)
    public DirectChannel requestChannel() {
        return new DirectChannel();
    }

    @Bean(name = OUTPUT_CHANNEL)
    public DirectChannel responseChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow fileMover() { // punto de entrada
        return IntegrationFlows.from(INPUT_CHANNEL)
                .handle(new PrintService(),"print") //Service Activator
                .channel(OUTPUT_CHANNEL)
                .get();
    }


/*    @Bean
    public IntegrationFlow myLambdaFlow() {
        return f -> f.channel("inputChannel")
                .transform("Hello "::concat)
                .handle(System.out::println);
    }*/

}