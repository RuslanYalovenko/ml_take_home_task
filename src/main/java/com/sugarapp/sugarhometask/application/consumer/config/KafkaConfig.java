package com.sugarapp.sugarhometask.application.consumer.config;

import com.sugarapp.sugarhometask.domain.Location;
import com.sugarapp.sugarhometask.domain.UserLocation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaConfig {

    @Value("${kafka.topic.location.weather}")
    private String locationWeatherTopic = "location-weather";

//    @Bean
//    public NewTopic topic() {
//        return TopicBuilder.name(locationWeatherTopic)
//                .partitions(10)
//                .replicas(1)
//                .build();
//    }

//    @Bean
//    public GlobalKTable<String, String> locationWeatherTable(StreamsBuilder streamsBuilder) {
//        return streamsBuilder.globalTable(locationWeatherTableName);
//    }
//
//    @Bean
//    public ReadOnlyKeyValueStore<String, String> locationWeatherStore(StreamsBuilder streamsBuilder) {
//        streamsBuilder.store("globalTable", QueryableStoreTypes.keyValueStore())
//    }

     @Bean
    public ApplicationRunner runner(KafkaTemplate<String, UserLocation> template) {
        return args -> {
            template.send(
                    locationWeatherTopic,
                    new UserLocation(
                            "1",
                            new Location("52.5162731", "13.3571059"),
                            System.currentTimeMillis()
                    )
            );
        };
    }
}
