package com.truong.doan.news.module;

import javax.persistence.*;

@Entity
@Table(name = "rss_template")
public class RSSReader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    private String name;

    private String linkRSS;

    private int categoryId;

    private String titleRegex;

    private String titleReplaceBy;

    private String imageRegex;

    private String imageReplaceBy;

    private String linkRegex;

    private String linkReplaceBy;

    private String desRegex;

    private String desReplaceBy;
}
