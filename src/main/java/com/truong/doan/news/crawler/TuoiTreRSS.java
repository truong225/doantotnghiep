package com.truong.doan.news.crawler;

import com.truong.doan.news.common.StringUtility;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TuoiTreRSS implements RSS {
    public final static String LINK_RSS = "https://baotintuc.vn/thoi-su.rss";//"https://tuoitre.vn/rss/thoi-su.rss";
    public final static String REGEX_FORMAT_DATETIME = "EEE, dd MMM yyyy HH:mm:ss 'GMT+7'";

    private String linkRSS;
    private String regexFormatDatetime;

    public TuoiTreRSS() {
        this.linkRSS = LINK_RSS;
        this.regexFormatDatetime = StringUtility.REGEX_FORMAT_DATETIME;
    }

    @Override
    public void crawl() {
        try {
            Document doc = Jsoup.connect(linkRSS).get();
            Elements items = doc.select("item");

            for (Element item : items) {
                String title = item.select("title").text();
                String link = item.select("link").text();
                String publishDate = item.select("pubDate").text();

                String descriptionElement = item.select("description")
                        .text();
                String image=StringUtility.getImageSource(descriptionElement);
                image=fixImageSource(image);
//                String description
//                String image=item.select("").text();

                System.out.println("Title: " + title + "\n"
                        + "Image: " + image + "\n"
                        + "Link: " + link + "\n" + publishDate
                        + "\n\n");
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Administrator can change this line of code inside this function
     * @param imageSource
     * @return
     */
    public String fixImageSource(String imageSource){
        return imageSource.replaceAll("zoom/80_50/","");
    }


    /**
     * Ignore, just for testing
     * @param args
     */
    public static void main(String[] args) {
        TuoiTreRSS tuoiTreRSS = new TuoiTreRSS();
        tuoiTreRSS.crawl();


//        String str = "<a href=\"https://tuoitre.vn/giao-vien-lui-xe-khien-1-hoc-sinh-tu-vong-chua-co-bang-lai-20180419200615573.htm\"><img src=\"https://cdn.tuoitre.vn/zoom/80_50/2018/4/19/photo1524143054028-15241430540281172872489.jpg\" /></a>TTO - Công an huyện Vân Hồ, Sơn La xác định cô giáo Nguyễn Thị Hương đã lái ôtô của anh Dương và gây tai nạn khiến một học sinh tử vong, một em gãy chân. Cô Hương chưa có bằng lái.";
//        System.out.println(StringUtility.getFromRegex(str, "<img[^>]*>"));
//        String time = "  Thu, 19 Apr 2018 21:54:22 GMT+7 ";
//        Date date = StringUtility.formatDate(time.trim(), TuoiTreRSS.REGEX_FORMAT_DATETIME);
//        System.out.println(date.toString());
    }
}
