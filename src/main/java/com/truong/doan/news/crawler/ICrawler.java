package com.truong.doan.news.crawler;

import com.truong.doan.news.service.NewsService;
import org.jsoup.nodes.Document;

public interface ICrawler {

    void getLinkList();

    String getTitle(Document doc, String element);

    String getImage(Document doc, String element);

    String getDescription(Document doc, String element);

    String getContent(Document doc, String element);

    String getDate(Document doc, String element);

    String getSource(Document doc);

    void getData(/*NewsService newsService*/);
}
