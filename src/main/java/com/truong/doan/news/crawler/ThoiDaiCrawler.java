package com.truong.doan.news.crawler;

import com.truong.doan.news.module.News;
import com.truong.doan.news.service.NewsService;
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


            //Access all link and get all posts
            for (String link : categoryLinkList) {
                doc = Jsoup.connect(DOMAIN_NAME + link).get();
                Elements e = doc.select("#dnn_ctr530_ModuleContent > div.tncndemo_wrap > div.contentx > div > a");
                e.forEach(element -> {
//                    if (element.attr("style").contains("display:block;"))
//                        linkPostList.add(element.attr("href"));


                    System.out.println(element.parents().select("div>span.xdate").text());
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void isOutDate(Document doc, String element){
        Element date=doc.selectFirst("#dnn_ctr530_Main_UserNewsCategory_rptFocus_ctl00_Label1");
//        System.out.println(date);
    }

    @Override
    public String getTitle(Document doc, String element) {

        Element title = doc.selectFirst(element);
        return title.text();
    }

    @Override
    public String getImage(Document doc, String element) {

        Element image = doc.selectFirst(IMAGE_SELECTOR);
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
    public void getData(/*NewsService newsService*/) {

        getLinkList();

        System.out.println("Get all data of post...");

        for (String link : linkPostList) {
            try {
                Document doc = Jsoup.connect(DOMAIN_NAME + link).get();
                String title = getTitle(doc, TITLE_SELECTOR);
                String image = getImage(doc, IMAGE_SELECTOR);
                String description = getDescription(doc, DES_SELECTOR);
                String content = getContent(doc, CONTENT_SELECTOR);
                String date = getDate(doc, DATE_SELECTOR);
                String source = getSource(doc);

//                if (image.equals(null))
//                    continue;
//                else {
//
//                    News news = new News(title, image, description, content, date, source);
//                    newsService.save(news);
//                }

                // Demo psudo code
                System.out.println(date);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ThoiDaiCrawler thoiDaiCrawler=new ThoiDaiCrawler();
        thoiDaiCrawler.getLinkList();
    }
}
