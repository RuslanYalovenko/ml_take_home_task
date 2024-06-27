package com.sugarapp.sugarhometask.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("UserLocationWeather")
public record UserLocationWeather(
        @Id String userID,
        Location location,
        Long timestamp,
        String weather
) implements Serializable {
}
