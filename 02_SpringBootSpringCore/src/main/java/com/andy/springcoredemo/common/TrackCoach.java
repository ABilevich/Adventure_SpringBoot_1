package com.andy.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
// @Lazy
public class TrackCoach implements Coach{

    public TrackCoach() {
        System.out.println("In Constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k!";
    }

}
