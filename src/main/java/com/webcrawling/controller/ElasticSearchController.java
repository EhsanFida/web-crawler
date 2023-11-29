package com.webcrawling.controller;

import com.webcrawling.entity.WebPage;
import com.webcrawling.service.WebCrawlerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elasticSearch")
@Api(tags = "Web Crawler API", description = "Operations for web crawling over specified URLs")
public class ElasticSearchController {
    @Autowired
    public WebCrawlerService webCrawlerService;

    @GetMapping("/crawl")
    public <Int> String processWebCrawling(@RequestParam String url, @RequestParam int maxDepth, @RequestParam int urlCrawled) {
        webCrawlerService.crawlWebsite(url, maxDepth, urlCrawled);
        return "Received string value: " + url;
    }

    @GetMapping
    public List<WebPage> getWebCrawlerDocument() {
        return webCrawlerService.getWebCrawlerDocuments();
    }

    @GetMapping("/search")
    public List<WebPage> getWebCrawlerDocument(@RequestParam String filter) {
        return webCrawlerService.getWebCrawlerDocumentBySearchParam(filter);
    }

}
