package com.webcrawling.entities.webCrawler;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Data
@Getter
@Setter
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
