package com.truong.doan.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MyComponent {
    @PostConstruct
    public static void startMethod() throws InterruptedException {
        while(true){
            System.out.println("Post construct code");
            Thread.sleep(5000);
        }
    }
}
