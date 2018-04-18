package com.truong.doan.news.service;

import com.truong.doan.news.module.News;

import java.util.List;

public interface NewsService {

    Iterable<News> findAll();
    News save(News news);
    List<News> getNewsForIndexPage(int numberNews);
    News getPost(Integer id);
}
