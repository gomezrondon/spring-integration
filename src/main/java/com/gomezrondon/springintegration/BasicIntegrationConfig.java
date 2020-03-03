package com.gomezrondon.springintegration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.http.dsl.Http;


@Configuration
@EnableIntegration
public class BasicIntegrationConfig{
    //c -> c.poller(Pollers.fixedRate(5, TimeUnit.SECONDS, 1).maxMessagesPerPoll(1))
    // (1, TimeUnit.SECONDS, 1)
    //http:inbound-channel-adapter
    // https://github.com/spring-projects/spring-integration-java-dsl/wiki/Spring-Integration-Java-DSL-Reference
   @Autowired
   private FilePrinter filePrinter;

    public static final String INPUT_CHANNEL = "printChannel";


    @Bean(name = INPUT_CHANNEL)
    public DirectChannel requestChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow pollingFlow() {
        return IntegrationFlows.from(Http.inboundChannelAdapter("/hola/mundo") //recibe get request
                .requestMapping(r -> r.methods(HttpMethod.GET)))
                .transform(Transformers.toJson())
                .channel(INPUT_CHANNEL)
                .get();
    }
    @Bean
    public IntegrationFlow fileMover() { // punto de entrada
        return IntegrationFlows.from(INPUT_CHANNEL)
                .handle(new PrintService(),"print") //Service Activator
                .get();
    }

    /*
    @Bean
public IntegrationFlow errorRecovererFlow() {
    return IntegrationFlows.from(Function.class, (gateway) -> gateway.beanName("errorRecovererFunction"))
            .handle((GenericHandler<?>) (p, h) -> {
                throw new RuntimeException("intentional");
            }, e -> e.advice(retryAdvice()))
            .get();
}
    */

}