package com.truong.doan.news.controller;

import com.truong.doan.news.crawler.ThoiDaiCrawler;
import com.truong.doan.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {

    @Autowired
    NewsService newsService;

    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("news",newsService.findAll());
        return "test";
    }

    @GetMapping("/")
    public String index(Model model){
//        model.addAttribute("listNews",newsService.);
        return "index";
    }

    @GetMapping("/crawl")
    public String crawl(Model model){

        ThoiDaiCrawler crawler=new ThoiDaiCrawler();
        crawler.getData(newsService);
        return "index";
    }

}
