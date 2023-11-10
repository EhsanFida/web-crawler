package com.webcrawling.config;

import com.webcrawling.service.webCrawler.impl.WebCrawlerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulingConfig {
    @Autowired
    WebCrawlerServiceImpl webCrawlerService;
    @Scheduled(fixedRate = 600000)  // Run after every 6 Minutes
    public void scheduledCrawl() {
                webCrawlerService.crawlWebsite("https://www.tutorialspoint.com/", 10, 10);
    }

}
