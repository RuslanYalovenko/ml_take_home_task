package com.sugarapp.sugarhometask.application.consumer;

import com.sugarapp.sugarhometask.domain.UserLocationWeatherRepository;
import com.sugarapp.sugarhometask.domain.UserLocation;
import com.sugarapp.sugarhometask.domain.UserLocationWeather;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserLocationConsumer {

    @Autowired
    public OpenWeatherMapApiClient openWeatherMapApiClient;

    @Autowired
    public UserLocationWeatherRepository userLocationWeatherRepository;

    @KafkaListener(id = "location-weather-consumer", topics = "${kafka.topic.location.weather}")
    public void listen(ConsumerRecord<String, UserLocation> record) {
        System.out.println("Consumed message");
        UserLocation userLocation = record.value();
        String weather = openWeatherMapApiClient.getWeather(
                userLocation.location().lat(),
                userLocation.location().lng()
        );
        if (weather.contains("weather")) {
            userLocationWeatherRepository.save(new UserLocationWeather(
                    userLocation.userID(),
                    userLocation.location(),
                    userLocation.timestamp(),
                    weather
            ));
        }
        System.out.println(record.value());
    }

}
