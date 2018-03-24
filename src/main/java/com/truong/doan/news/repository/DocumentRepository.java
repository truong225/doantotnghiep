package com.truong.doan.news.repository;

import com.truong.doan.news.module.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<Document, Integer>{


}