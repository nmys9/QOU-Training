package com.example.demo.demo_api.service;

import com.example.demo.demo_api.model.Greeting;
import com.example.demo.demo_api.repository.GreetingRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GreetingService {
    private final GreetingRepositoryImpl greetingRepositoryImpl;

    public GreetingService(GreetingRepositoryImpl greetingRepositoryImpl){
        this.greetingRepositoryImpl = greetingRepositoryImpl;
        greetingRepositoryImpl.addGreeting(new Greeting(1,"Welcome to Spring"));
        greetingRepositoryImpl.addGreeting(new Greeting(2,"This is a REST API test"));

    }

    public Greeting addGreeting(Greeting greeting){
//        if(id < 0) throw new IllegalArgumentException("ID must be positive number");
//        if(content==null || content.trim().isEmpty()) throw new IllegalArgumentException("Content must be not null");
        Optional<Greeting> existsGreeting= greetingRepositoryImpl.findGreetingByID(greeting.id());
        if(existsGreeting.isPresent()) return existsGreeting.get();
        else
        return greetingRepositoryImpl.addGreeting(greeting);
    }

    public List<Greeting> getAllGreeting(){
        return greetingRepositoryImpl.gatAllGreeting();
    }

    public Greeting findGreetingById(long id){
        return greetingRepositoryImpl.findGreetingByID(id).orElseThrow(
                () -> new NoSuchElementException("Greeting ID "+ id +" not found")
        );
    }



}
