package com.gomezrondon.springintegration;

import org.springframework.messaging.Message;

public class PrintService {

    public void print(Message<String> message) {
        System.out.println(message.getPayload());

        message.getHeaders().forEach((k,v)->System.out.println("Item : " + k + " Count : " + v));

    }
}
