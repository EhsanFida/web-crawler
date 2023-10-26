package com.webcrawling.repositories.elasticSearch;

import com.webcrawling.entities.elasticSearch.WebCrawlerDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface WebCrawlerDocumentRepository extends ElasticsearchRepository<WebCrawlerDocument, String> {
}
