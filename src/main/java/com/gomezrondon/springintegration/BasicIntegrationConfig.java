package com.gomezrondon.springintegration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;


@Configuration
@EnableIntegration
public class BasicIntegrationConfig{
    // (1, TimeUnit.SECONDS, 1)
    //jdbc:inbound-channel-adapter
    // https://docs.spring.io/spring-integration/reference/html/dsl.html#java-dsl-gateway
   @Autowired
   private FilePrinter filePrinter;

   @Autowired
   private DataSource dataSource;

    public static final String INPUT_CHANNEL = "printChannel";


    @Bean(name = INPUT_CHANNEL)
    public DirectChannel requestChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageSource<Object> jdbcMessageSource() {
        return new JdbcPollingChannelAdapter(this.dataSource, "SELECT * FROM PERSON;");
    }

    @Bean
    public IntegrationFlow pollingFlow() {
        return IntegrationFlows.from(jdbcMessageSource(),
                c -> c.poller(Pollers.fixedRate(5, TimeUnit.SECONDS, 1).maxMessagesPerPoll(1)))
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

}