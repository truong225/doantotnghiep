package com.truong.doan.news.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public final class LSH {

    public static final int NUM_HASH = 100;
    public static final long PRIME_NUMBER = 4294967311L;


    public static void findDuplicate(List<String> listDocuments) {

        long[][] paramHashFunction = getParamHashFunction();

        ArrayList<long[]> signatureList=new ArrayList<>();

        for (String line : listDocuments) {
            List<String> hashedShingle = hashing(line);

            long[] signatureShingle = getSingature(hashedShingle, paramHashFunction);

            signatureList.add(signatureShingle);
        }

        boolean[] checkList=check(signatureList);
        for(int i=0;i<checkList.length;i++)
            if(checkList[i])
                System.out.println(listDocuments.get(i));
    }


    public static List<String> hashing(String source) {
        String[] listWords = source.split(" ");
        List<String> shingle = new ArrayList<>();

        for (int i = 0; i < listWords.length - 2; i++) {
            shingle.add((long) (listWords[i] + " "
                    + listWords[i + 1] + " "
                    + listWords[i + 2])
                    .hashCode() + "");
        }

        return shingle;
    }

    public static long[][] getParamHashFunction() {

        long[][] listParamHashFunction = new long[2][];

        listParamHashFunction[0] = new long[NUM_HASH];
        listParamHashFunction[1] = new long[NUM_HASH];

        Random rand = new Random();
        for (int i = 0; i < NUM_HASH; i++) {
            listParamHashFunction[0][i] = rand.nextInt((int) (Math.pow(2, 32) - 1));
            listParamHashFunction[1][i] = rand.nextInt((int) (Math.pow(2, 32) - 1));
        }

        return listParamHashFunction;
    }


    /**
     * Get singatures of each shingle from Shingle List
     */
    public static long[] getSingature(List<String> shingle,
                                      long[][] paramHashFunction) {

//        ArrayList<long[]> signatures = new ArrayList<>();

        long[] biasA = paramHashFunction[0];
        long[] biasB = paramHashFunction[1];


        long[] signature = new long[NUM_HASH];
        for (int i = 0; i < NUM_HASH; i++) {
            long minHash = PRIME_NUMBER + 1;
            for (String shingleCode : shingle) {
                long hashCode = (biasA[i] * Long.parseLong(shingleCode) + biasB[i]) % PRIME_NUMBER;
                if (hashCode < minHash)
                    minHash = hashCode;
            }
            signature[i] = minHash;
        }

        return signature;
    }


    public static boolean[] check(ArrayList<long[]> signatureList) {

        boolean[] indexDuplicate=new boolean[signatureList.size()];

        for (int i = 0; i < signatureList.size() - 1; i++) {
            for (int j = i + 1; j < signatureList.size(); j++) {

                int count = 0;
                for (int t = 0; t < NUM_HASH; t++) {
                    if (signatureList.get(i)[t] == signatureList.get(j)[t])
                        count++;
                }

                if ((double) (count / NUM_HASH) > 0.5f)
                    indexDuplicate[i]=true;
            }
        }

        return indexDuplicate;
    }


    public static void main(String[] args) throws FileNotFoundException {

        String input = "D:\\Thuc tap\\TextMining\\clean_data.txt";
        ArrayList<String> documents = new ArrayList<>();

        FileInputStream fis = new FileInputStream(input);
        Scanner scan = new Scanner(fis);
        for (int i = 0; i < 10000; i++) {
            if (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.equals("") || line.split("\t").length==1)
                    continue;
                else
                    documents.add(line);
            }
        }
        scan.close();
        findDuplicate(documents);
    }
}
