package com.sugarapp.sugarhometask.application.consumer;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.netty.channel.ChannelOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Service
public class OpenWeatherMapApiClient {

    @Value("${openweathermap.api.url}")
    private String apiUrl = "https://api.openweathermap.org/data/2.5/weather";

    @Value("${openweathermap.api.key}")
    private String apiKey;

    int maxBufferSizeBytes = 100 * 1024 * 1024;

    private static final JsonMapper jsonMapper = new Jackson2ObjectMapperBuilder()
            .failOnUnknownProperties(false)
            .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .modules(new JavaTimeModule())
            .build();

    private ExchangeStrategies strategies = ExchangeStrategies
            .builder()
            .codecs(clientDefaultCodecsConfigurer -> {
                clientDefaultCodecsConfigurer.defaultCodecs()
                        .jackson2JsonEncoder(new Jackson2JsonEncoder(jsonMapper, MediaType.APPLICATION_JSON));
                clientDefaultCodecsConfigurer.defaultCodecs()
                        .jackson2JsonDecoder(new Jackson2JsonDecoder(jsonMapper, MediaType.APPLICATION_JSON));
                clientDefaultCodecsConfigurer.defaultCodecs()
                        .maxInMemorySize(maxBufferSizeBytes);
            })

            .build();

    HttpClient httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .responseTimeout(Duration.ofMillis(5000));

    WebClient client = WebClient.builder()
            .baseUrl(apiUrl)
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .exchangeStrategies(strategies)
            .build();


    public String getWeather(String lat, String lng) {
        String params = String.format("?lat=%f&lon=%f&appid=%s", lat, lng, apiKey);
        return client.get().uri(params).exchangeToMono(response -> {
            if (response.statusCode() == HttpStatus.OK) {
                return response.bodyToMono(String.class);
            } else {
                return response.createException().flatMap(e -> Mono.error(e));
            }
        }).block();
    }

}
