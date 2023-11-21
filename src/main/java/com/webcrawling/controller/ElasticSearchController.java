package com.webcrawling.controller;
import com.webcrawling.entities.webCrawler.WebPage;
import com.webcrawling.repositories.webCrawler.WebPageRepository;
import com.webcrawling.service.webCrawler.WebCrawlerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        webCrawlerService.crawlWebsite(url,maxDepth,urlCrawled);
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
