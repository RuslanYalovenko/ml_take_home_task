package com.sugarapp.sugarhometask.application.web;

import com.sugarapp.sugarhometask.domain.UserLocationWeather;
import com.sugarapp.sugarhometask.domain.UserLocationWeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserWeatherService {

    @Autowired
    private UserLocationWeatherRepository userLocationWeatherRepository;

    public UserLocationWeather findWeatherUserById(String userId) {
        UserLocationWeather userWeather = userLocationWeatherRepository.findById(userId)
                .orElseGet(() -> {
                    throw new NoSuchElementException();
                });
        if (userWeather.timestamp() > System.currentTimeMillis()-30*60*1000) {
            return userWeather;
        } else {
            throw new NoSuchElementException();
        }
    }

}
