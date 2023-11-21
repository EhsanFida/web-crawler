package com.webcrawling.service.webCrawler.impl;
import com.webcrawling.entities.webCrawler.ExceptionRecord;
import com.webcrawling.entities.webCrawler.WebPage;
import com.webcrawling.repositories.webCrawler.ExceptionRecordRepository;
import com.webcrawling.repositories.webCrawler.WebPageRepository;
import com.webcrawling.service.webCrawler.WebCrawlerService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class WebCrawlerServiceImpl implements WebCrawlerService {
    private static final Logger logger= LoggerFactory.getLogger(WebCrawlerServiceImpl.class);
    ExceptionRecord exceptionRecord = new ExceptionRecord();
    @Autowired
    WebPageRepository webPageRepository;
    @Autowired
    private ExceptionRecordRepository exceptionRecordRepository;
        public void  crawlWebsite(String startingUrl, int maxDepth, int maxDocuments) {
       logger.info("Web Crawling over given URL has been started");
        Queue<String> urlQueue = new LinkedList<>();
        Set<String> visitedUrls = new HashSet<>();
        urlQueue.add(startingUrl);
        int currentDepth = 0;
        int documentsCrawled = 0;
        while (!urlQueue.isEmpty() && currentDepth <= maxDepth && documentsCrawled < maxDocuments) {
            String currentUrl = urlQueue.poll();
            if (!visitedUrls.contains(currentUrl)) {
                try {
                    Document document = Jsoup.connect(currentUrl).get();
                    WebPage webPage = new WebPage();
                    webPage.setUrl(currentUrl);
                    webPage.setTitle(document.title());
                    // Counting words and limiting to 200
                    String content = document.text();
                    String[] words = content.split("\\s+");
                    int wordCount = words.length;

                    if (wordCount > 200) {
                        // Truncate the content to 200 words
                        content = String.join(" ", Arrays.copyOfRange(words, 0, 200));
                    }
                    webPage.setContent(document.text());
                    webPageRepository.save(webPage);
                    documentsCrawled++;

                    Elements links = document.select("a[href]");
                    for (Element link : links) {
                        String linkUrl = link.attr("abs:href");
                        urlQueue.add(linkUrl);
                    }
                } catch (IOException e) {
                      logger.error("An error occurred while crawling URL: " + currentUrl, e);
                      saveExceptionRecord(e);
                }
                visitedUrls.add(currentUrl);
                logger.info("Web Crawling Completed on " +currentUrl);
            }
            if (urlQueue.isEmpty()) {
                currentDepth++;
            }
        }

    }

    public void saveExceptionRecord(IOException e) {
        exceptionRecord.setMethodName("crawlWebsite");
        exceptionRecord.setExceptionMessage(e.getMessage());
        exceptionRecord.setTimestamp(LocalDateTime.now());

        exceptionRecordRepository.save(exceptionRecord);
    }

    public List<WebPage> getWebCrawlerDocumentBySearchParam(String filter) {
        return webPageRepository.searchByTitleOrContent(filter);
    }

    public List<WebPage> getWebCrawlerDocuments() {
        return StreamSupport.stream(webPageRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

}
