package com.truong.doan.news.controller;

import com.truong.doan.news.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @GetMapping("/test")
    public String index(Model model){
        model.addAttribute("news",documentService.findAll());
        return "test";
    }
}
