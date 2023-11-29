package com.webcrawling.service.webCrawler;

import com.webcrawling.service.webCrawler.impl.WebCrawlerServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;


@Configuration
@Service
@Component

public class SchedulerService {
    private static final Logger logger= LoggerFactory.getLogger(SchedulerService.class);
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    @Autowired
    WebCrawlerServiceImpl webCrawlerService;
    @Value("${urls}")
    //List<String> urls = new ArrayList<>();
    List<Callable<String>> urls = new ArrayList<>();
    // Run after every Five(5) Minutes
    @Scheduled(fixedRate = 500000)
        public void scheduledCrawl() {
        urls.add(() -> webCrawlerService.crawlWebsite(urls, 10, 10));
        /*executorService.execute(new Runnable() {
            public void run() {
                logger.info("Asynchronous task");
                for (String url : urls){
                    logger.info("in loop"+url);
                    webCrawlerService.crawlWebsite(url, 10, 10);
                }
            }
        });*/
        executorService.invokeAll();
        executorService.shutdown();
    }
    public Callable newCallable(){
        return newCallable() {
            @Override
                    public Object call() throws Exception {
            }
        }
        }
    }

}
