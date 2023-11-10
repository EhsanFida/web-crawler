package com.webcrawling.service.webCrawler;

import com.webcrawling.entities.webCrawler.WebPage;

import java.util.List;

public interface WebCrawlerService {
    public void crawlWebsite(String startingUrl, int maxDepth, int maxDocuments);

    public List<WebPage> getWebCrawlerDocumentBySearchParam(String filter);

    public List<WebPage> getWebCrawlerDocuments();
}
