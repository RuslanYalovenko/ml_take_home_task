package com.sugarapp.sugarhometask.domain;

public record UserLocation(
        String userID,
        Location location,
        Long timestamp
) {
}
