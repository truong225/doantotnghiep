package com.truong.doan.news.common;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtility {
    // final variable
    public final static String REGEX_HTML_TAG = "\\\\<[^>]*>";
    public final static String REGEX_FORMAT_DATETIME = "EEE, dd MMM yyyy HH:mm:ss 'GMT+7'";

    /* String processing function*/
    // Get a String from a String
    public final static String getFromRegex(String text, String regex /*"<a*.*a>"*/) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            text = matcher.group(0);
        }

        return text;
    }

    /**
     * Function replace some string from a String
     * @param
     * @return
     */
    public final static String replaceString(String source, String regex, String replaceBy){
        return source.replaceAll(regex,replaceBy);
    }

    // Convert String to Datetime
    public final static Date formatDate(String datetime, String formatString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatString);
        Date pubDate = null;
        try {
            pubDate = simpleDateFormat.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return pubDate;
    }

    /**
     * Get image source from String as HTML tag
     *
     * @param strAsHTML: format like "<img src="https://link-to-image.jpg" />"
     * @return
     */
    public final static String getImageSource(String strAsHTML) {
        String link = "";
        strAsHTML = getFromRegex(strAsHTML, "<img*.*/>");
        Document doc = Jsoup.parse(strAsHTML, "", Parser.xmlParser());
        link = doc.select("img").attr("src");
        return link;
    }


    public static void main(String[] args) {
        String text="<a href=\"https://tuoitre.vn/chua-ap-dung-bien-phap-hinh-su-doi-voi-chu-co-so-ca-phe-pin-2018041918242532.html\"><img src=\"https://cdn.tuoitre.vn/zoom/80_50/2018/4/19/photo1524136850096-15241368500961943044250.jpg\" /></a>TTO - Đại tá Lê Vinh Quy, phó giám đốc Công an tỉnh Đắk Nông cho biết vẫn tiếp tục lấy lời khai bà Loan và những người liên quan, chưa áp dụng các biện pháp hình sự.\n" +
                "http*.*\\.(htm)|(html)";
        System.out.println(getFromRegex(text,"<img*.*/>"));
        System.out.println(getImageSource(text));
    }
}
