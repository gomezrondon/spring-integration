package com.gomezrondon.springintegration;

import org.springframework.integration.transformer.Transformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public class CustomTransformer implements Transformer {


    @Override
    public Message<?> transform(Message<?> message) {
        return   MessageBuilder.withPayload("55").build();
    }
}
