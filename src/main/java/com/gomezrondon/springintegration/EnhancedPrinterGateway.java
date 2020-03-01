package com.gomezrondon.springintegration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;


@MessagingGateway(name = "myGateway",defaultHeaders = @GatewayHeader(name = "globalHeader", value = "simpleValue"))
public interface EnhancedPrinterGateway {

    @Gateway(requestChannel = "printChannel", headers =  @GatewayHeader(name = "individualHeader", expression = "#args[0].firstName")) //"#arg[0].firstName"
    void print( Person person );

    @Gateway(requestChannel = "uppercaseChannel")
    String uppercase(Person person);
}
