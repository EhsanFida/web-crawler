package com.webcrawling.service.impl;

import com.webcrawling.entity.ExceptionRecord;
import com.webcrawling.entity.WebPage;
import com.webcrawling.repo.ExceptionRecordRepository;
import com.webcrawling.repo.WebPageRepository;
import com.webcrawling.service.WebCrawlerService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.data.elasticsearch.core.SearchHit;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class WebCrawlerServiceImpl implements WebCrawlerService {
    @Autowired
    WebPageRepository webPageRepository;
    @Autowired
    private ExceptionRecordRepository exceptionRecordRepository;
    @Autowired
    ElasticsearchOperations elasticsearchOperations;

    public void crawlWebsite(String startingUrl, int maxDepth, int maxDocuments) {
        log.info("Web Crawling over given URL has been started {} ", startingUrl);
        log.info(Thread.currentThread().getName());
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
                    webPageRepository.save(WebPage.builder()
                            .url(currentUrl)
                            .title(document.title())
                            .description(document.text().length() > 200 ? document.text().substring(0, 200) : document.text())
                            .content(document.text())
                            .build());
                    documentsCrawled++;

                    Elements links = document.select("a[href]");
                    for (Element link : links) {
                        String linkUrl = link.attr("abs:href");
                        urlQueue.add(linkUrl);
                    }
                } catch (IOException e) {
                    log.error("An error occurred while crawling URL : {} {} ", currentUrl, e);
                    saveExceptionRecord(e, "crawlWebsite");
                }
                visitedUrls.add(currentUrl);
                log.info("Web Crawling Completed on {} ", currentUrl);
            }
            if (urlQueue.isEmpty()) {
                currentDepth++;
            }
        }

    }

    public void saveExceptionRecord(IOException e, final String methodName) {
        log.info("Logging error into elastic");
        exceptionRecordRepository.save(ExceptionRecord.builder().methodName(methodName)
                .exceptionMessage(e.toString())
                .timestamp(LocalDateTime.now())
                .build());
    }

    public List<WebPage> getWebCrawlerDocumentBySearchParam(String filter) {
        return webPageRepository.searchByTitleOrContent(filter);
    }

    public List<WebPage> getWebCrawlerDocuments() {
        return StreamSupport.stream(webPageRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
    public List<String> getDescriptionFromDocument(String findString){
        QueryBuilder queryBuilder = QueryBuilders
                .wildcardQuery("description", findString+"*");

        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .withPageable(PageRequest.of(0, 7))
                .build();

        SearchHits<WebPage> searchSuggestions =
                elasticsearchOperations.search(searchQuery,
                        WebPage.class,
                        IndexCoordinates.of("web_page"));

        List<String> suggestions = new ArrayList<String>();

        searchSuggestions.getSearchHits().forEach(searchHit->{
            suggestions.add(searchHit.getContent().getDescription());
        });
        return suggestions;
    }
}


