package com.gomezrondon.springintegration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.handler.MethodInvokingMessageHandler;
import org.springframework.messaging.MessageHandler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


@Configuration
@EnableIntegration
public class BasicIntegrationConfig{


    public static final String INPUT_CHANNEL = "inputChannel";

    @Bean(name = INPUT_CHANNEL)
    @Qualifier("pubSub")  // this is necessary
    public PublishSubscribeChannel publishSubscribe() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setThreadNamePrefix("default_task_executor_thread");
        executor.initialize();
        return executor;
    }

    @Bean
    public IntegrationFlow flowHandler1() { // punto de entrada
        return IntegrationFlows.from(INPUT_CHANNEL)
                .channel(MessageChannels.executor(this.threadPoolTaskExecutor())) // si funciona
                .handle(testingHandle(new UppercasePrintService(),"print",5)) //Service Activator
                .get();
    }

    @Bean
    public IntegrationFlow flowHandler2() { // punto de entrada
        return IntegrationFlows.from(INPUT_CHANNEL)
                //.channel(MessageChannels.executor(this.threadPoolTaskExecutor())) // si funciona
                .handle(testingHandle(new PrintService(),"print",1)) //Service Activator
                .get();
    }

    public MessageHandler testingHandle(Object object,String methodName,  Integer order) {
        MethodInvokingMessageHandler handler = new MethodInvokingMessageHandler(object,methodName);
        handler.setOrder(order); // the priority is a lower number

        return handler;
    }


/*    @Bean
    public IntegrationFlow myLambdaFlow() {
        return f -> f.channel("inputChannel")
                .transform("Hello "::concat)
                .handle(System.out::println);
    }*/

}
