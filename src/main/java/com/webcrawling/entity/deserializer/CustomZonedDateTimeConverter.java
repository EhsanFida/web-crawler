package com.webcrawling.entity.deserializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.mapping.PropertyValueConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;
@Slf4j
public class CustomZonedDateTimeConverter implements PropertyValueConverter {


    @Override
    public Object write(Object value) {
       return value;
    }

    @Override
    public Object read(Object value) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.valueOf(Long.valueOf(value.toString()))),
                TimeZone.getDefault().toZoneId());
        return localDateTime;
    }
}