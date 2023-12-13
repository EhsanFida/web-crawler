package com.webcrawling.repo;

import com.webcrawling.entity.WebPage;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface WebPageRepository extends ElasticsearchRepository<WebPage, String> {
   @Query("{\"bool\": {\"should\": [{\"match\": {\"title\": {\"query\": \"?0\", \"operator\": \"and\"}}}, {\"match\": {\"content\": {\"query\": \"?0\", \"operator\": \"and\"}}}]}}")
    List<WebPage> searchByTitleOrContent(String filter);
    /*@Query("{\"bool\": {\"should\": [{\"match\":{\"description\": {\"query\": \"?0\", \"operator\": \"and\"}}}")
   List<WebPage> findStringInDescription(String findString);*/

}