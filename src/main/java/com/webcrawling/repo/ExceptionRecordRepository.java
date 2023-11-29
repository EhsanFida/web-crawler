package com.webcrawling.repo;
import com.webcrawling.entity.ExceptionRecord;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ExceptionRecordRepository extends ElasticsearchRepository<ExceptionRecord, String> {
}
