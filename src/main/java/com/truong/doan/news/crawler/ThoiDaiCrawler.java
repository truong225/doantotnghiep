package com.truong.doan.news.crawler;

import com.truong.doan.news.module.News;
import com.truong.doan.news.service.NewsService;
import com.truong.doan.news.service.NewsServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ThoiDaiCrawler implements ICrawler {

    /**
     * Crawler tin tuc tren bao Thoi Dai
     * Danh sach selector:
     * Thoi su:
     * Kinh te: #dnn_ctr750_ModuleContent > div > ul > li:nth-child(3) > a
     * <p>
     * Selector
     */

    public static final String DOMAIN_NAME = "http://thoidai.com.vn/";
    public static final String CATEGORY_LINK_LIST = "#dnn_ctr750_ModuleContent > div > ul >li>a";

    private static final String TITLE_SELECTOR = "#dnn_ctr519_Main_UserNewsDetail_up > div.newdetailscontent > div>h1";
    private static final String IMAGE_SELECTOR = "#dnn_ctr519_Main_UserNewsDetail_up > div.xcontents";
    private static final String DES_SELECTOR = "#dnn_ctr519_Main_UserNewsDetail_up > div.newdetailscontent > h2";
    private static final String CONTENT_SELECTOR = "#dnn_ctr519_Main_UserNewsDetail_up > div.xcontents";
    private static final String DATE_SELECTOR = "#dnn_ctr519_Main_UserNewsDetail_up > div.newdetailscontent > div > div > span";

    private List<String> linkPostList;

    public ThoiDaiCrawler() {
        linkPostList = new ArrayList<>();
    }

    /**
     * Link list:
     * http://thoidai.com.vn/_t57c0
     * http://thoidai.com.vn/thoi-su_t113c3
     * http://thoidai.com.vn/kinh-te_t113c14
     * http://thoidai.com.vn/giao-duc_t113c20
     * http://thoidai.com.vn/suc-khoe_t113c23
     * http://thoidai.com.vn/ban-nam-chau_t113c34
     * http://thoidai.com.vn/tam-long-be-ban_t113c35
     * http://thoidai.com.vn/van-hoa-du-lich_t113c9
     * http://thoidai.com.vn/the-gioi_t113c6
     * http://thoidai.com.vn/van-hoa-du-lich/diem-den_t113c12
     * http://thoidai.com.vn/an-toan-giao-thong_t113c95
     */
    @Override
    public void getLinkList() {

        try {
            System.out.print("Get links of post...");

            List<String> categoryLinkList = new ArrayList<>();

            Document doc = Jsoup.connect(DOMAIN_NAME).get();
            doc.select(CATEGORY_LINK_LIST)
                    .forEach(e -> categoryLinkList.add(e.attr("href")));
            categoryLinkList.remove(0);

            System.out.println("Done");


            //Access all link and get data
            for (String link : categoryLinkList) {
                doc = Jsoup.connect(DOMAIN_NAME + link).get();
                Elements e = doc.select("#dnn_ctr530_ModuleContent > div.tncndemo_wrap > div.contentx > div > a");
                e.forEach(element -> {
                    if (element.attr("style").contains("display:block;"))
                        linkPostList.add(element.attr("href"));
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getTitle(Document doc, String element) {

        Element title = doc.selectFirst(element);
        return title.text();
    }

    @Override
    public String getImage(Document doc, String element) {

        Element image = doc.selectFirst("#dnn_ctr519_Main_UserNewsDetail_up >" +
                " div" +
                ".xcontents");
//        System.out.println(image.getElementsByClass("xitemn xphoto").attr("href"));
//        System.out.println(doc.baseUri());
        return image.getElementsByClass("xitemn xphoto").attr("href");
    }

    @Override
    public String getDescription(Document doc, String element) {
        Element des = doc.selectFirst(element);
        return des.text();
    }

    @Override
    public String getContent(Document doc, String element) {
        Element content = doc.selectFirst(element);
        return content.toString();
    }

    @Override
    public String getDate(Document doc, String element) {
        Element date = doc.selectFirst(element);
        return date.text();
    }

    @Override
    public String getSource(Document doc) {
        return doc.baseUri();
    }

    @Override
    public void getData(NewsService newsService) {

        getLinkList();

        System.out.println("Get all links of post...");

        for (String link : linkPostList) {
            try {
                Document doc = Jsoup.connect(DOMAIN_NAME + link).get();
                String title = getTitle(doc, TITLE_SELECTOR);
                String image = getImage(doc, IMAGE_SELECTOR);
                String description = getDescription(doc, DES_SELECTOR);
                String content = getContent(doc, CONTENT_SELECTOR);
                String date = getDate(doc, DATE_SELECTOR);
                String source = getSource(doc);

                if (image.equals(null))
                    continue;
                else {

                    News news = new News(title, image, description, content, date, source);
                    newsService.save(news);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        ThoiDaiCrawler crawler = new ThoiDaiCrawler();
//        crawler.getData();
    }
}
