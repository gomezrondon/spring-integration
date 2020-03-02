package com.gomezrondon.springintegration;


import org.springframework.stereotype.Service;

/***
 * Outbound service adapter
 */
@Service
public class PersonRegistrationService {
    public void registerEmail(Person person) {
        System.out.println("Email create for: "+person.getFirstName());
    }
}
