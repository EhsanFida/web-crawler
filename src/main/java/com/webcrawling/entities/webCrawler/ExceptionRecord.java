package com.webcrawling.entities.webCrawler;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@Document(indexName = "exception_details")
public class ExceptionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String className;
    private String methodName;
    private String exceptionMessage;
    private LocalDateTime timestamp;
}
