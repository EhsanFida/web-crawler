package com.webcrawling.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "web_page")
public class WebPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String url;
    private String title;
    private String content;
    private String description;
}
