package com.gomezrondon.springintegration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(name = "myGateway")
public interface CustomGateway {

    @Gateway(requestChannel="pollableChannel")
    void print(String message);

}