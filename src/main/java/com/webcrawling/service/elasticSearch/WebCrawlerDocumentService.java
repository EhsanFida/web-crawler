package com.webcrawling.service.elasticSearch;


import com.webcrawling.entities.elasticSearch.WebCrawlerDocument;
import com.webcrawling.repositories.elasticSearch.WebCrawlerDocumentRepository;
import com.webcrawling.service.webCrawler.WebCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WebCrawlerDocumentService {

    @Autowired
    public WebCrawlerDocumentRepository webCrawlerDocumentRepository;
    @Autowired
    public WebCrawlerService webCrawlerService;

    public void saveWebCralwerDocument(WebCrawlerDocument webCrawlerDocument) {
        webCrawlerDocumentRepository.save(webCrawlerDocument);
    }

    public List<WebCrawlerDocument> getWebCrawlerDocuments() {
        return StreamSupport.stream(webCrawlerDocumentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void deleteWebCrawlerDocument(String id) {
        webCrawlerDocumentRepository.deleteById(id);
    }

    public void search(String url) {
        webCrawlerService.crawlWebsite(url);
    }
}
