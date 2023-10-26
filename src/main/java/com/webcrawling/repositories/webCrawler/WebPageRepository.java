package com.webcrawling.repositories.webCrawler;

import com.webcrawling.entities.webCrawler.WebPage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface WebPageRepository extends ElasticsearchRepository<WebPage, Long> {
}
