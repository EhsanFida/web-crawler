package com.webcrawling.service.webCrawler.impl;

import com.webcrawling.entities.elasticSearch.WebCrawlerDocument;
import com.webcrawling.entities.webCrawler.WebPage;
import com.webcrawling.repositories.webCrawler.WebPageRepository;
import com.webcrawling.service.webCrawler.WebCrawlerService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

@Service
@Component
public class WebCrawlerServiceImpl implements WebCrawlerService {
    @Autowired
    WebPageRepository webPageRepository;
    @Scheduled(fixedRate = 60000) // Run after every 60 seconds
    public void scheduledCrawl() {
        crawlWebsite("https://www.bbc.com/", 32, 52);
    }

    public void  crawlWebsite(String startingUrl, int maxDepth, int maxDocuments) {
       /* try {
            Document document = Jsoup.connect(startingUrl).get();
            WebPage webPage = new WebPage();
            webPage.setUrl(startingUrl);
            webPage.setTitle(document.title());
            webPage.setContent(document.text());

            webPageRepository.save(webPage);

            // Implement recursive crawling for links on the page
            Elements links = document.select("a[href]");
            for (Element link : links) {
                String linkUrl = link.attr("abs:href");
                crawlWebsite(linkUrl, , );
            }
        } catch (IOException e) {
            // Handle exceptions
        }*/
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
                    webPage.setContent(document.text());
                    webPageRepository.save(webPage);
                    documentsCrawled++;

                    Elements links = document.select("a[href]");
                    for (Element link : links) {
                        String linkUrl = link.attr("abs:href");
                        urlQueue.add(linkUrl);
                    }
                } catch (IOException e) {
                    //  logger.error("An error occurred while crawling URL: " + currentUrl, e);
                }
                visitedUrls.add(currentUrl);
            }
            if (urlQueue.isEmpty()) {
                currentDepth++;
            }
        }

    }
}
