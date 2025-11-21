package com.example.demo.demo_api.repository;

import com.example.demo.demo_api.model.Greeting;
import org.springframework.stereotype.Repository;


import java.util.*;
import java.util.stream.Collectors;

@Repository
public class GreetingRepositoryImpl implements GreetingRepository{
    private final List<Greeting> greetings= new ArrayList<>();

    public Greeting addGreeting(Greeting greeting){
        Greeting newGreeting=new Greeting(greeting.id(),greeting.content());
        greetings.add(newGreeting);
        return newGreeting;
    }

    public List<Greeting> gatAllGreeting(){
        return greetings;
    }

    public Optional<Greeting> findGreetingByID(long id){
        return greetings.stream()
                .filter(g -> g.id()==id)
                .findFirst();
    }

}
