package com.truong.doan.news.module;


import com.truong.doan.news.common.StringUtility;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "news")
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "link")
    private String link;

    @Column(name = "description")
    private String description;

    @Column(name = "publishDate")
    private String publishDate;

    @Column(name = "rss_guid")
    private String rssGuid;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRssGuid() {
        return rssGuid;
    }

    public void setRssGuid(String rssGuid) {
        this.rssGuid = rssGuid;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public News() {
    }

    public News(String title,
                String image,
                String link,
                String description,
                String publishDate,
                String rssGuid) {
        setTitle(title);
        setImage(image);
        setLink(link);
        setDescription(description);
        setPublishDate(publishDate);
        setRssGuid(rssGuid);
    }
}
