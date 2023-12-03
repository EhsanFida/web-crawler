package com.webcrawling.entity;

import com.webcrawling.entity.deserializer.CustomZonedDateTimeConverter;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "exception_details")
public class ExceptionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Field
    private String className;

    @Field
    private String methodName;

    @Field
    private String exceptionMessage;

    @Field
    @ValueConverter(CustomZonedDateTimeConverter.class)
    private LocalDateTime timestamp;
}
