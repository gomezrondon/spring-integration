package com.gomezrondon.springintegration;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class EndPoint {

    @GetMapping("/hola")
    public String getGreeting() {
        return "hola mundo at: " + LocalDateTime.now();
    }

}
