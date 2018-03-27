package com.truong.doan.news.crawler;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.LinkedList;

public class TestCrawler implements ICrawler {

    LinkedList<Element> listDocuments;

    public TestCrawler() {
        listDocuments = new LinkedList<>();
    }

    @Override
    public void getElement(String url, String element) {
        try {
            Document doc = Jsoup.connect(url).get();
            doc.select(element).forEach(el -> listDocuments.add(el));

            for(Element e:listDocuments)
                System.out.println(e.getAllElements());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new TestCrawler().getElement("http://www.baeldung" +
                ".com/java-with-jsoup","p.a");
    }
}
