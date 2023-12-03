package com.webcrawling.service;

import com.webcrawling.entity.ExceptionRecord;

import java.util.List;

public interface ExceptionLoggingService {

    List<ExceptionRecord> getFailedSites();
}
