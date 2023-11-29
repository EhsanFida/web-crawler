package com.webcrawling.service.webCrawler;

import com.webcrawling.service.webCrawler.impl.WebCrawlerServiceImpl;

import java.util.concurrent.Callable;

public class ServiceProcessors implements Callable<String> {

    WebCrawlerServiceImpl webCrawlerService;

    private String url;

    public ServiceProcessors(final String url, WebCrawlerServiceImpl webCrawlerService) {
        this.url = url;
        this.webCrawlerService = webCrawlerService;
    }

    @Override
    public String call() {
        webCrawlerService.crawlWebsite(url, 10, 10);
        return "Executed Successfully";
    }
}
