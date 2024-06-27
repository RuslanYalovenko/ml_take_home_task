package com.sugarapp.sugarhometask.domain;

import com.sugarapp.sugarhometask.domain.UserLocationWeather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLocationWeatherRepository extends CrudRepository<UserLocationWeather, String> {}
