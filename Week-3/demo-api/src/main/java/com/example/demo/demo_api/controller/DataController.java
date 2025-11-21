package com.example.demo.demo_api.controller;

import com.example.demo.demo_api.model.Greeting;
import com.example.demo.demo_api.service.GreetingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/greetings")
public class DataController {
    private final GreetingService greetingService;

    public DataController(GreetingService greetingService){
        this.greetingService=greetingService;
    }

    @GetMapping
    public List<Greeting> getAllGreeting(){
        return greetingService.getAllGreeting();
    }

    @GetMapping("/{id}")
    public Greeting getSingleGreeting(@PathVariable long id){
        return greetingService.findGreetingById(id);
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public Greeting addNewGreeting(@RequestBody @Valid Greeting greeting){
        return greetingService.addGreeting(greeting);
    }



}
