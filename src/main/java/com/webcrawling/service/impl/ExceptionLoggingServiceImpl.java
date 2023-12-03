package com.webcrawling.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webcrawling.entity.ExceptionRecord;
import com.webcrawling.repo.ExceptionRecordRepository;
import com.webcrawling.service.ExceptionLoggingService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExceptionLoggingServiceImpl implements ExceptionLoggingService {

    @Autowired
    private ElasticsearchClient esClient;
    @Autowired
    ExceptionRecordRepository exceptionRecordRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<ExceptionRecord> getFailedSites() throws IOException {
        log.info("Fetching failed index details");
        SearchResponse<ExceptionRecord> response =esClient.search(s -> s
                        .index("exception_details")
                        .query(q -> q.match(t -> t.field("methodName").query("crawlWebsite"))
                        ),
                ExceptionRecord.class
        );
        return response.hits().hits().stream().map(value->value.source()).collect(Collectors.toList());
    }
}
