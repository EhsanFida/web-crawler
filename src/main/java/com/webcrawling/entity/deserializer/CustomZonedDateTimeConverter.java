package com.webcrawling.entity.deserializer;

import org.springframework.data.elasticsearch.core.mapping.PropertyValueConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class CustomZonedDateTimeConverter implements PropertyValueConverter {


    @Override
    public Object write(Object value) {
       return value;
    }

    @Override
    public Object read(Object value) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.valueOf(Long.valueOf(value.toString()))),
                TimeZone.getDefault().toZoneId());
    }
}