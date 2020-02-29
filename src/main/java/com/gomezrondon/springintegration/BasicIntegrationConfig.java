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
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.handler.MethodInvokingMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.TimeUnit;


@Configuration
@EnableIntegration
public class BasicIntegrationConfig{


    public static final String INPUT_CHANNEL = "inputChannel";
    public static final String QUEUE_INPUT_CHANNEL = "pollableChannel";

    @Bean
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setThreadNamePrefix("default_task_executor_thread");
        executor.initialize();
        return executor;
    }

    @Bean(name = QUEUE_INPUT_CHANNEL)
    @Qualifier("queuechannel")  // this is necessary
    public MessageChannel requestChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean(name = INPUT_CHANNEL)
    public PublishSubscribeChannel publishSubscribe() {
        return MessageChannels.publishSubscribe(threadPoolTaskExecutor()).get();
    }

    @Bean
    public IntegrationFlow fileWriter() {
        return IntegrationFlows.from(QUEUE_INPUT_CHANNEL)
                .bridge(e -> e.poller(Pollers.fixedRate(1, TimeUnit.SECONDS, 1)))
                .channel(INPUT_CHANNEL)
                .get();
    }

    @Bean
    public IntegrationFlow flowHandler1() { // punto de entrada
        return IntegrationFlows.from(INPUT_CHANNEL)
                .handle(testingHandle(new UppercasePrintService(),"print",5)) //Service Activator
                .get();
    }

    @Bean
    public IntegrationFlow flowHandler2() { // punto de entrada
        return IntegrationFlows.from(INPUT_CHANNEL)
                .handle(testingHandle(new PrintService(),"print",1)) //Service Activator
                .get();
    }

    public MessageHandler testingHandle(Object object,String methodName,  Integer order) {
        MethodInvokingMessageHandler handler = new MethodInvokingMessageHandler(object,methodName);
        handler.setOrder(order); // the priority is a lower number

        return handler;
    }


}
