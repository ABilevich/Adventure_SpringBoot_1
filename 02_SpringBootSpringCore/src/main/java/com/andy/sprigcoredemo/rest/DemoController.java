package com.andy.sprigcoredemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andy.sprigcoredemo.common.Coach;

@RestController
public class DemoController {
    // Define a private field for the dependency
    private Coach myCoach;

    // Define a contstructor for dependency injection
    @Autowired
    public DemoController(Coach theCoach){
        myCoach = theCoach;
    }

    // Setup with setter injection (this method could have any name)
    // @Autowired
    // public void setCoach(Coach theCoach){
    //     myCoach = theCoach;
    // }
    
    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }
}
