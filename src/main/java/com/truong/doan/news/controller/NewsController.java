package com.truong.doan.news.controller;

import com.truong.doan.news.crawler.ThoiDaiCrawler;
import com.truong.doan.news.service.CategoryService;
import com.truong.doan.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewsController {

    @Autowired
    NewsService newsService;

    @Autowired
    CategoryService categoryService;


    @RequestMapping("/test")
    public String test(Model model) {
        model.addAttribute("news", newsService.findAll());
        return "test";
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("listNews", newsService.getNewsForIndexPage(50));
        model.addAttribute("listCategory",categoryService.getAll());
        return "index";
    }

    @RequestMapping("/post/{postId}")
    public String getPost(@PathVariable Integer postId, Model model){
        model.addAttribute("article", newsService.getPost(postId));
        return "post";
    }

    @RequestMapping("/category/{catId}")
    public String getPostListCategory(@PathVariable Integer catId, Model model){
        model.addAttribute("listNews", newsService.getPostInCategory(catId));
        model.addAttribute("categoryInfo", categoryService.getOne(catId));
        return "category";
    }
}
