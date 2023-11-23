package com.webcrawling.service.webCrawler;

import com.webcrawling.service.webCrawler.impl.WebCrawlerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Configuration
@Service
@Component

public class SchedulerService {
    @Autowired
    WebCrawlerServiceImpl webCrawlerService;
    @Value("${url3}")
    String url;
    @Scheduled(fixedRate = 500000)  // Run after every Five(5) Minutes
    public void scheduledCrawl() {
        webCrawlerService.crawlWebsite(url, 10, 10);
    }
}
