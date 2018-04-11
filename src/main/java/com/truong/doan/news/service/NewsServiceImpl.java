package com.truong.doan.news.service;

import com.truong.doan.news.module.News;
import com.truong.doan.news.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public Iterable<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public News save(News news) {
        return newsRepository.save(news);
    }

    @Override
    public List<News> getNewsForIndexPage(int numberNews){

        return newsRepository.getNewsForIndexPage(numberNews);
    }
}
