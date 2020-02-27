package com.gomezrondon.springintegration;


import org.springframework.stereotype.Component;

@Component
public class PrintService {

    public void print(String message) {
        System.out.println(message);
    }
}
