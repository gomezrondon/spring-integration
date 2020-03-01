package com.gomezrondon.springintegration;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public class PrintService {

    public Message<?> print(Message<String> message) {
        System.out.println(message.getPayload());

     //   message.getHeaders().forEach((k,v)->System.out.println("Item : " + k + " Count : " + v));
        return MessageBuilder.withPayload("new Message").build();
    }
}
