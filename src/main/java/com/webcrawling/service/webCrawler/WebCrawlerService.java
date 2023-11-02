package com.webcrawling.service.webCrawler;

public interface WebCrawlerService {
    public void crawlWebsite(String startingUrl, int maxDepth, int maxDocuments);
}
