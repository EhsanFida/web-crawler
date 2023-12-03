package com.webcrawling.repo;
import com.webcrawling.entity.ExceptionRecord;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ExceptionRecordRepository extends ElasticsearchRepository<ExceptionRecord, String> {

    List<ExceptionRecord> findByMethodName(final String methodName);
}
