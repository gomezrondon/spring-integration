package com.gomezrondon.springintegration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;



@MessagingGateway(name = "myGateway")
public interface PrinterGateway {

    @Gateway(requestChannel="inputChannel")
    void print(Message<Person> message);
}
