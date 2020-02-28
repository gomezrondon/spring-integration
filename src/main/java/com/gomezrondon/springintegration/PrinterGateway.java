package com.gomezrondon.springintegration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

import java.util.concurrent.Future;

@MessagingGateway(name = "myGateway")
public interface PrinterGateway {

    @Gateway(requestChannel="inputChannel")
    Future<Message<String>> send(Message<String> message);
}
