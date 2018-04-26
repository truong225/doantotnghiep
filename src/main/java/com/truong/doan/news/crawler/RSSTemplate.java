package com.truong.doan.news.crawler;

import com.truong.doan.news.common.StringUtility;
import com.truong.doan.news.module.News;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RSSTemplate {
    public final static String REGEX_HTML_TAG = "\\\\<[^>]*>";

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


    public RSSTemplate(String linkRSS) {
        this.linkRSS = linkRSS;
    }

    public List<News> getData() {
        List<News> listNews = null;

        try {
            listNews = new ArrayList<>();
            Document doc = Jsoup.connect(linkRSS).get();
            Elements items = doc.select("item");

            for (Element item : items)
                crawlItem(listNews, item);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return listNews;
    }

    public void crawlItem(List<News> listNews, Element item) {
        String title = item.select("title").text(); //
        String link = item.select("link").text();   //
        String pubDate = item.select("pubDate").text(); //
        String guid = item.select("guid").text(); //

        String descriptionElement = item.select("description").text();
        String description = StringUtility.getTextOfTag(descriptionElement);    //

        String image = StringUtility.getOneTagElement(descriptionElement, "img", "src");

        /* Check if guid has exists in database
         */
        title = cleanData(title, titleRegex, titleReplaceBy);

        News news = new News(title, image, link, description, pubDate, guid);
        listNews.add(news);
    }

    /**
     * Clean data function
     */
    public String cleanData(String source, String regexStr, String replaceStr) {
        return source.replaceAll(regexStr, replaceStr);
    }


    public static void main(String[] args) {
        RSSTemplate rssTemplate = new RSSTemplate("https://tuoitre.vn/rss/thoi-su.rss");
        List<News> listNews = rssTemplate.getData();

        System.out.println("List crawled:");
        listNews.forEach(news -> System.out.println(news.getLink()));
    }
}