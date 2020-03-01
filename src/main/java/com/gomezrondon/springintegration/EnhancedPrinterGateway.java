package com.gomezrondon.springintegration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(name = "myGateway")
public interface EnhancedPrinterGateway {

    @Gateway(requestChannel = "printChannel")
    void print(Person person);

    @Gateway(requestChannel = "uppercaseChannel")
    String uppercase(Person person);
}
