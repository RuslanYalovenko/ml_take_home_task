package com.sugarapp.sugarhometask.application.web;

import com.sugarapp.sugarhometask.domain.UserLocationWeather;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserWeatherController  {

    @Autowired
    public UserWeatherService userWeatherService;

    @GetMapping("/user/{userId}")
    public UserLocationWeather getUserWeather(@PathParam("userId") String userId) {
        try {
            return userWeatherService.findWeatherUserById(userId);
        } catch (Exception e) {
            return null;
        }
    }
}
