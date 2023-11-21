package com.webcrawling.service.webCrawler;

import com.webcrawling.service.webCrawler.impl.WebCrawlerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Configuration
@Service
public class SchedulerService {
    @Autowired
    WebCrawlerServiceImpl webCrawlerService;
    @Scheduled(fixedRate = 500000)  // Run after every Five(5) Minutes
    public void scheduledCrawl() {
        webCrawlerService.crawlWebsite("https://www.tutorialspoint.com/", 10, 10);
    }
}
