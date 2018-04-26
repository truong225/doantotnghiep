package com.truong.doan.news.common;

import org.deeplearning4j.text.sentenceiterator.SentenceIterator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
//import org.

public class MyModel {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("D:\\Thuc tap\\TextMining\\clean_data.txt");
        Scanner scanner = new Scanner(fis);

        Logger log=Logger.getLogger(MyModel.class.getName());

        List<String> word = new ArrayList<>();
        List<double[]> vector = new ArrayList<>();

        System.out.print("Import model...");

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split("\\s");

            String tu="";
            for(int i=0;i<line.length-100;i++)
                tu=tu+line[i]+" ";
            word.add(tu.trim());

            double[] v = new double[100];
            int j=0;
            for (int i = line.length-100; i < line.length; i++)
                v[j++] = Double.parseDouble(line[i]);
            vector.add(v);
        }
        System.out.println(" Done");

        while(true){
            System.out.println("-----------------------------------------");
            System.out.print("Type word: ");
            scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if(!word.contains(input))
                continue;

            System.out.println("Word found:");

            for(int i=0;i<word.size();i++){
                double similarity=cosineSimilarity(vector.get(word.indexOf(input)),vector.get(i));
                if(similarity>0.9f) {
                    System.out.println("\t" + word.get(i) + ": " + similarity);
                }
            }
        }
    }


    public static double cosineSimilarity(double[] A, double[] B) {
        double AB = 0.0f;
        double A_B = 0.0f;

        double lengthA = 0f;
        double lengthB = 0f;

        for (int i = 0; i < A.length; i++) {
            AB += A[i] * B[i];

            lengthA += Math.pow(A[i], 2);
            lengthB += Math.pow(B[i], 2);
        }
        A_B = Math.sqrt(lengthA * lengthB);

        if (A_B == 0.0f)
            return 0.0f;
        return AB / A_B;
    }
}
