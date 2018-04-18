package com.truong.doan.news.controller;

import com.truong.doan.news.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CrawlerController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/crawl")
    public String crawl(Model model) {
        model.addAttribute("listSource", "Báo Thời Đại");
        model.addAttribute("listCategory", categoryService.findAll());
        return "crawler";
    }
}
