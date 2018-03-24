package com.truong.doan.news.service;

import com.truong.doan.news.module.Document;

import java.util.List;

public interface DocumentService{

    Iterable<Document> findAll();
}
