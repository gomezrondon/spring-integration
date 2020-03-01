package com.gomezrondon.springintegration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSelector;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.handler.MethodInvokingMessageHandler;
import org.springframework.integration.router.RecipientListRouter;
import org.springframework.integration.transformer.ContentEnricher;
import org.springframework.integration.transformer.Transformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableIntegration
public class BasicIntegrationConfig{


    public static final String INPUT_CHANNEL = "inputChannel";
    public static final String OUTPUT_CHANNEL = "outputChannel";

    @Bean(name = INPUT_CHANNEL)
    @Qualifier("directChannel")  // this is necessary
    public MessageChannel requestChannel() {
        return MessageChannels.direct().get();
    }

    @Bean(name = OUTPUT_CHANNEL)
    public MessageChannel otroChannel() {
        return MessageChannels.direct().get();
    }


     @Bean
    public IntegrationFlow flowHandler1() { // punto de entrada
// https://docs.spring.io/spring-integration/reference/html/message-transformation.html#payload-enricher
        return IntegrationFlows.from(INPUT_CHANNEL)
                .enrich(e -> e.requestPayload(Message::getPayload)
                                .shouldClonePayload(false)
                                .<Map<String, String>>propertyFunction("lastName", m -> {
                                    String firstName = ((Person) m.getPayload()).getLastName();
                                    if (firstName.equalsIgnoreCase("gomez")) {
                                        return "pepe";
                                    }
                                    return firstName;
                                })
                )
                .channel(OUTPUT_CHANNEL)
                .get();
    }

    @Bean
    public IntegrationFlow flowHandler2() { // punto de entrada
        return IntegrationFlows.from(OUTPUT_CHANNEL)
                .handle(testingHandle(new PrintService(),"print",1)) //Service Activator
                .get();
    }

    public MessageHandler testingHandle(Object object,String methodName,  Integer order) {
        MethodInvokingMessageHandler handler = new MethodInvokingMessageHandler(object,methodName);
        handler.setOrder(order); // the priority is a lower number
        return handler;
    }



}
