package com.truong.doan.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HandleErrorController implements ErrorController {

    private static final String PATH="/error";

    @Autowired
    ErrorAttributes errorAttributes;

    @RequestMapping(value = PATH)
    public String getError(Model model){
        return "error";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
