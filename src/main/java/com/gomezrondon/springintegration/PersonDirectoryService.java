package com.gomezrondon.springintegration;


import org.springframework.stereotype.Service;

/***
 * this is mocking an external service, where we pull data
 */
@Service
public class PersonDirectoryService  {

    public Person findNewPeople() {
        return new Person("Maria", "Perez");
    }


}
