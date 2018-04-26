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
     *
     * @param
     * @return
     */
    public final static String replaceString(String source, String regex, String replaceBy) {
        return source.replaceAll(regex, replaceBy);
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
    public final static String getOneTagElement(String strAsHTML,
                                                String tagName,
                                                String selector) {
        String result = "";
        strAsHTML = getFromRegex(strAsHTML, "<" + tagName + "*.*/>");
        Document doc = Jsoup.parse(strAsHTML, "", Parser.xmlParser());
        result = doc.select(tagName).attr(selector);
        return result;
    }

    public final static String getDoubleTagElement(String strAsHTML,
                                                   String tagName,
                                                   String selector) {
        String result = "";
        strAsHTML = getFromRegex(strAsHTML, "<" + tagName + "[^>]*>");
        Document doc = Jsoup.parse(strAsHTML, "", Parser.xmlParser());
        result = doc.select(tagName).attr(selector);
        return result;
    }

    public final static String getTextOfTag(String strAsHTML) {
        String result = "";
        String htmlText = "<div>" + strAsHTML + "</div>";
        Document doc = Jsoup.parse(htmlText,"",Parser.xmlParser());
        result=doc.select("div").text();
        return result;
    }


    public static void main(String[] args) {
//        String text = "<a href=\"https://tuoitre.vn/chua-ap-dung-bien-phap-hinh-su-doi-voi-chu-co-so-ca-phe-pin-2018041918242532.html\"><img src=\"https://cdn.tuoitre.vn/zoom/80_50/2018/4/19/photo1524136850096-15241368500961943044250.jpg\" /></a>TTO - Đại tá Lê Vinh Quy, phó giám đốc Công an tỉnh Đắk Nông cho biết vẫn tiếp tục lấy lời khai bà Loan và những người liên quan, chưa áp dụng các biện pháp hình sự.\n" +
//                "http*.*\\.(htm)|(html)";
//        System.out.println(getFromRegex(text, "<img*.*/>"));
//        System.out.println(getOneTagElement(text, "img", "src"));
//
//        System.out.println("Test get description");
//        System.out.println(getTextOfTag("<a href='https://baotintuc.vn/chinh-tri/day-manh-khai-thac-co-hieu-qua-cac-diem-sinh-hoat-van-hoa-cong-dong-20180421133256329.htm'><img src='https://mediacms.baotintuc.vn/2018/04/21/13/30/th1.jpg' width='100' border='0' style=\"margin-right:10px;float:left; width:100px\" /></a>Ngày 21/4, đến dự và phát biểu tại hội nghị Ban Chấp hành Đảng bộ thị trấn Đông Anh (huyện Đông Anh, Hà Nội) mở rộng, đồng chí Hoàng Trung Hải, Ủy viên Bộ Chính trị, Bí thư Thành ủy, Trưởng đoàn đại biểu Quốc hội thành phố Hà Nội đã ghi nhận và biểu dương những kết quả mà Đảng bộ, nhân dân thị trấn Đông Anh đạt được trong thời gian vừa qua."));

        String str="Hello";
        str=str.replaceAll("","");
        System.out.println(str);
    }
}
