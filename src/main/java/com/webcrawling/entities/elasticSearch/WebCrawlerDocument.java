package com.webcrawling.entities.elasticSearch;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Getter
@Setter
@NoArgsConstructor
@Document(indexName = "web_crawler_documet")
public class WebCrawlerDocument {
    @Id
    private String id;
    private String title;
    private String description;
}
