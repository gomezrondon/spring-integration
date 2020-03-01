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


    public static final String INPUT_CHANNEL = "printChannel";
    public static final String UPPERCASE_CHANNEL = "uppercaseChannel";

    @Bean(name = INPUT_CHANNEL)
    public DirectChannel requestChannel() {
        return new DirectChannel();
    }
    @Bean
    public IntegrationFlow fileMover() { // punto de entrada
        return IntegrationFlows.from(INPUT_CHANNEL)
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