package com.truong.doan.news.service.Impl;

import com.truong.doan.news.module.Category;
import com.truong.doan.news.repository.CategoryRepository;
import com.truong.doan.news.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Iterable<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getOne(Integer id) {
        return categoryRepository.getOne(id);
    }
}
