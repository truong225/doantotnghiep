package com.truong.doan.news.service;

import com.truong.doan.news.module.Category;

import java.util.List;

public interface CategoryService {
    Iterable<Category> findAll();
    Iterable<Category> getAll();
    Category getOne(Integer id);
}
