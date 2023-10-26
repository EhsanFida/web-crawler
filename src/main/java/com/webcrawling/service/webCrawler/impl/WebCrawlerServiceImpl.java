package com.webcrawling.service.webCrawler.impl;

import com.webcrawling.entities.webCrawler.WebPage;
import com.webcrawling.repositories.webCrawler.WebPageRepository;
import com.webcrawling.service.webCrawler.WebCrawlerService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WebCrawlerServiceImpl implements WebCrawlerService {
    @Autowired
    WebPageRepository webPageRepository;
    @Override
    public void crawlWebsite(String startingUrl) {
        try {
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
                crawlWebsite(linkUrl);
            }
        } catch (IOException e) {
            // Handle exceptions
        }
    }
}
