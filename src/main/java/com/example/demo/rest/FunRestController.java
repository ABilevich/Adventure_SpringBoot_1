package com.example.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    @GetMapping("/")
    public String sayHello(){
        return "Hello World!";
    }

    // expose a new endpoint for workout
    @GetMapping("/workout")
    public String getDailyWorkout(){
        return "Run a hard 5k!";
    }

    // expose a new endpoint for "fortune"
    @GetMapping("/fortune")
    public String getDailyFortune(){
        return "Today is your lucky day!";
    }

        // expose a new endpoint for "fortune"
    @GetMapping("/fortune2")
    public String getDailyFortune2(){
        return "Today is your lucky day!";
    }

}
