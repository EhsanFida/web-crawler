package com.webcrawling.controller;

import com.webcrawling.entities.elasticSearch.WebCrawlerDocument;
import com.webcrawling.service.elasticSearch.WebCrawlerDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elasticSearch")
public class ElasticSearchController {
    @Autowired
    public WebCrawlerDocumentService webCrawlerDocumentService;

    @GetMapping("/crawl")
    public String processWebCrawling(@RequestParam String url) {
        webCrawlerDocumentService.search(url);
        return "Received string value: " + url;
    }

    @PostMapping("/saveData")
    public ResponseEntity<String> saveData(@RequestBody WebCrawlerDocument webCrawlerDocument) {
        webCrawlerDocumentService.saveWebCralwerDocument(webCrawlerDocument);
        return ResponseEntity.ok("Saved successfully");
    }

    @GetMapping
    public List<WebCrawlerDocument> getWebCrawlerDocument() {
        return webCrawlerDocumentService.getWebCrawlerDocuments();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletedWebCrawlerDocument(@PathVariable String id) {
        webCrawlerDocumentService.deleteWebCrawlerDocument(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
