package com.webcrawling.repositories.webCrawler;

import com.webcrawling.entities.webCrawler.WebPage;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface WebPageRepository extends ElasticsearchRepository<WebPage, String> {
   @Query("{\"bool\": {\"should\": [{\"match\": {\"title\": {\"query\": \"?0\", \"operator\": \"and\"}}}, {\"match\": {\"content\": {\"query\": \"?0\", \"operator\": \"and\"}}}]}}")
    List<WebPage> searchByTitleOrContent(String filter);

  //  List<WebPage> searchByTitleOrContent(String filter);

   // List<WebPage> search(BoolQueryBuilder boolQueryBuilder);
}