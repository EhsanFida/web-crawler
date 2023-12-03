package com.webcrawling.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webcrawling.entity.ExceptionRecord;
import com.webcrawling.repo.ExceptionRecordRepository;
import com.webcrawling.service.ExceptionLoggingService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExceptionLoggingServiceImpl implements ExceptionLoggingService {

    RestHighLevelClient restHighLevelClient;
    @Autowired
    ExceptionRecordRepository exceptionRecordRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<ExceptionRecord> getFailedSites() {
        log.info("Fetching failed index details");
        return exceptionRecordRepository.findByMethodName("crawlWebsite");
        /*MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("methodName", "crawlWebsite")
                .analyzer("synonym_analyzer");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(matchQuery);

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.source(searchSourceBuilder);
        searchRequest.types("exception_details");
        log.info("Search Query prepared : {} ", searchRequest);
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            return Arrays.stream(searchResponse.getHits().getHits())
                    .map(hit -> objectMapper.convertValue(hit.getSourceAsMap(), ExceptionRecord.class))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Failed to execute search {} ", e);
        }*/
    }
}
