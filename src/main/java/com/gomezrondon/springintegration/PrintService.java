package com.gomezrondon.springintegration;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.Map;

public class PrintService {

    public void print(Message<String> message) {
        MessageHeaders headers = message.getHeaders();

        for (Map.Entry<String, Object> entry : headers.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        System.out.println(message.getPayload());
    }
}
