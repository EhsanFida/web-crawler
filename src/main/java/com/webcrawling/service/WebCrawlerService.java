package com.webcrawling.service;

import com.webcrawling.entity.WebPage;

import java.util.List;

public interface WebCrawlerService {
    public void crawlWebsite(String startingUrl, int maxDepth, int maxDocuments);

    public List<WebPage> getWebCrawlerDocumentBySearchParam(String filter);

    public List<WebPage> getWebCrawlerDocuments();
    public List<String> getDescriptionFromDocument(String findString);
}
