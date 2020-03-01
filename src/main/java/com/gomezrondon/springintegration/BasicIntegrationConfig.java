package com.gomezrondon.springintegration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.handler.MethodInvokingMessageHandler;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;


@Configuration
@EnableIntegration
public class BasicIntegrationConfig{


    public static final String INPUT_CHANNEL = "inputChannel";

    @Bean(name = INPUT_CHANNEL)
    @Qualifier("directChannel")  // this is necessary
    public MessageChannel requestChannel() {
        return MessageChannels.direct().get();
    }

    @Bean(name = "otroChanel1")
    public MessageChannel otroChannel() {
        return MessageChannels.direct().get();
    }

    @Bean(name = "otroChanel2")
    public MessageChannel otroChannel2() {
        return MessageChannels.direct().get();
    }


/*    @Bean
    public PayloadTypeRouter router() {
        PayloadTypeRouter router = new PayloadTypeRouter();
        router.setChannelMapping(Integer.class.getName(), "otroChanel1");
        router.setChannelMapping(String.class.getName(), "otroChanel2");
        return router;
    }*/

    @Bean
    public IntegrationFlow flowRouting() { // punto de entrada
        return IntegrationFlows.from(INPUT_CHANNEL)
                .<Object, Class<?>>route(Object::getClass, m -> m
                        .channelMapping(String.class, "otroChanel2")
                        .channelMapping(Integer.class, "otroChanel1"))
                .get();
    }


    @Bean
    public IntegrationFlow flowHandler1() { // punto de entrada
        return IntegrationFlows.from("otroChanel1")
                .handle(testingHandle(new NumericPrintService(),"print",5)) //Service Activator
                .get();
    }

    @Bean
    public IntegrationFlow flowHandler2() { // punto de entrada
        return IntegrationFlows.from("otroChanel2")
                .handle(testingHandle(new PrintService(),"print",1)) //Service Activator
                .get();
    }

    public MessageHandler testingHandle(Object object,String methodName,  Integer order) {
        MethodInvokingMessageHandler handler = new MethodInvokingMessageHandler(object,methodName);
        handler.setOrder(order); // the priority is a lower number
        return handler;
    }



}
