package com.webcrawling.repositories.webCrawler;
import com.webcrawling.entities.webCrawler.ExceptionRecord;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ExceptionRecordRepository extends ElasticsearchRepository<ExceptionRecord, String> {
}
