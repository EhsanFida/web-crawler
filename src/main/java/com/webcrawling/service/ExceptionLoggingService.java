package com.webcrawling.service;

import com.webcrawling.entity.ExceptionRecord;

import java.io.IOException;
import java.util.List;

public interface ExceptionLoggingService {

    List<ExceptionRecord> getFailedSites() throws IOException;
}
