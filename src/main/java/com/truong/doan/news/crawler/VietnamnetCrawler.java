package com.truong.doan.news.crawler;

import com.truong.doan.news.service.NewsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VietnamnetCrawler implements ICrawler {

    public static final String DOMAIN_NAME = "http://vietnamnet.vn";
    public static final String CATEGORY_LINK_LIST = "#vnnfooter > ul";

    private static final String TITLE_SELECTOR = "#dnn_ctr519_Main_UserNewsDetail_up > div.newdetailscontent > div>h1";
    private static final String IMAGE_SELECTOR = "#dnn_ctr519_Main_UserNewsDetail_up > div.xcontents";
    private static final String DES_SELECTOR = "#dnn_ctr519_Main_UserNewsDetail_up > div.newdetailscontent > h2";
    private static final String CONTENT_SELECTOR = "#dnn_ctr519_Main_UserNewsDetail_up > div.xcontents";
    private static final String DATE_SELECTOR = "#dnn_ctr519_Main_UserNewsDetail_up > div.newdetailscontent > div > div > span";

    private List<String> linkPostList;


    @Override
    public void getLinkList() {
        try {

            List<String> categoryLinkList = new ArrayList<>();

            Document doc = Jsoup.connect(DOMAIN_NAME).get();

            Elements list=doc.select(CATEGORY_LINK_LIST);
            for(Element e:list){
                System.out.println(e);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getTitle(Document doc, String element) {
        return null;
    }

    @Override
    public String getImage(Document doc, String element) {
        return null;
    }

    @Override
    public String getDescription(Document doc, String element) {
        return null;
    }

    @Override
    public String getContent(Document doc, String element) {
        return null;
    }

    @Override
    public String getDate(Document doc, String element) {
        return null;
    }

    @Override
    public String getSource(Document doc) {
        return null;
    }

    @Override
    public void getData(/*NewsService newsService*/) {

    }


    public static void main(String[] args) {
        VietnamnetCrawler vietnamnetCrawler=new VietnamnetCrawler();
        vietnamnetCrawler.getLinkList();
    }
}
