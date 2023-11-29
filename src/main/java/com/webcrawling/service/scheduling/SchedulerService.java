package com.webcrawling.service.scheduling;

import com.webcrawling.service.impl.WebCrawlerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
@Slf4j
public class SchedulerService {
    @Autowired
    WebCrawlerServiceImpl webCrawlerService;
    @Value("${urls}")
    private String url;
    private List<String> urls;

    @PostConstruct
    public void init() {
        urls = new ArrayList<>();
        urls.addAll(Arrays.stream(url.split(",")).toList());
    }

    // Run after every Five(5) Minutes
    @Scheduled(fixedRate = 500000)
    public void scheduledCrawl() throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<String>> callables = new ArrayList<>();
        urls.forEach(val -> {
            callables.add(new ServiceProcessors(val, webCrawlerService));
        });
        executorService.invokeAll(callables);
        executorService.shutdown();
    }

}
