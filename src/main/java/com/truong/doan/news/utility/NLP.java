package com.truong.doan.news.utility;

import java.util.ArrayList;
import java.util.List;

public final class NLP {

    public static List<String> normalizeData(List<String> listDocuments) {

        for (String document : listDocuments) {
            if (document.equals("")){
                listDocuments.remove(document);
                continue;
            }
        }

        listDocuments.replaceAll(String::toLowerCase);


        return listDocuments;
    }

    public static void main(String[] args) {
        List<String> s=new ArrayList<>();
        s.add("Hello");
        s.add("SSSSSS");
        s=NLP.normalizeData(s);
        s.forEach(s1 -> System.out.println(s1));
    }
}
