package com.gomezrondon.springintegration;


import org.springframework.stereotype.Component;

@Component
public class UppercaseService {

    public String execute(Person person) {
        return (person.getFirstName() + " " + person.getLastName()).toUpperCase();
    }
}
