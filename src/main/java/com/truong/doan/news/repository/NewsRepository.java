package com.truong.doan.news.repository;

import com.truong.doan.news.module.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {

    @Query(value = "SELECT * FROM News n Limit 0,:numberNews",
            nativeQuery = true)
    List<News> getNewsForIndexPage(@Param("numberNews") int numberNews);
}