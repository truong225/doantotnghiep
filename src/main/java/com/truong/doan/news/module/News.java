package com.truong.doan.news.module;


import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "news")
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "releash_date")
    private String releashDate;

    @Column(name = "source", nullable = false)
    private String source;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReleashDate() {
        return releashDate;
    }

    public void setReleashDate(String releashDate) {
        this.releashDate = releashDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


    public News() {
    }

    public News(String title, String image, String description,
                String content, String releashDate, String source) {
        setTitle(title);
        setImage(image);
        setDescription(description);
        setContent(content);
        setReleashDate(releashDate);
        setSource(source);
    }
}
