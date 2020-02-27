package com.gomezrondon.springintegration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;


@Configuration
@EnableIntegration
public class BasicIntegrationConfig{

   /* @Bean
    public IntegrationFlow fileMover() { // punto de entrada

        return IntegrationFlows.from("inputChannel")
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

    @Bean
    public IntegrationFlow myLambdaFlow() {
        return f -> f.channel("inputChannel")
                .transform("Hello "::concat)
                .handle(System.out::println);
    }

}
