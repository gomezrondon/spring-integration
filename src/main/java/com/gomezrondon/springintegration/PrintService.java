package com.gomezrondon.springintegration;

import org.springframework.messaging.Message;



public class PrintService {

    public void print(Message<?> message) {
        System.out.println(message.getPayload());

       message.getHeaders().forEach((k,v)->System.out.println(k + ":" + v));

    }
}
