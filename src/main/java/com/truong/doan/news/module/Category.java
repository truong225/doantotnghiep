package com.truong.doan.news.module;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name",nullable = false)
    private String name;


    public Category(String name){
        setName(name);
    }

    public Category(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
