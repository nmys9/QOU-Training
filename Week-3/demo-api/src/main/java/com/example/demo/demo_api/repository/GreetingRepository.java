package com.example.demo.demo_api.repository;

import com.example.demo.demo_api.model.Greeting;

import java.util.List;
import java.util.Optional;

public interface GreetingRepository {
    Greeting addGreeting(Greeting greeting);
    List<Greeting> gatAllGreeting();
    Optional<Greeting> findGreetingByID(long id);
}
