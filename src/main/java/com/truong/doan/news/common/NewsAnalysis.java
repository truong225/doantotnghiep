package com.truong.doan.news.common;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.LineSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentencePreProcessor;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class NewsAnalysis {

    public void buildModel(String dataFilename) throws IOException {
        SentenceIterator iter = new LineSentenceIterator(new File(dataFilename));
        iter.setPreProcessor(new SentencePreProcessor() {
            @Override
            public String preProcess(String sentence) {
                return sentence.toLowerCase();
            }
        });

        TokenizerFactory t = new DefaultTokenizerFactory();
        t.setTokenPreProcessor(new CommonPreprocessor());

        Word2Vec model = new Word2Vec.Builder()
                .minWordFrequency(5)
                .iterations(1)
                .layerSize(100)
                .seed(42)
                .windowSize(5)
                .iterate(iter)
                .tokenizerFactory(t)
                .build();

        model.fit();

        // Write word vectors
        WordVectorSerializer.writeWord2VecModel(model, "model_gpu.ml");
        WordVectorSerializer.writeWordVectors(model, "model_gpu.txt");

        Collection<String> lst = model.wordsNearest("an", 10);
        System.out.println("Closest: " + lst);

    }

    /**
     * @param sentenceA
     * @param sentenceB
     */
    public double useWord2Vec(String sentenceA, String sentenceB) {
        Word2Vec model = WordVectorSerializer.readWord2VecModel("model.ml");
        sentenceA = sentenceA.replaceAll("[^\\w\\n\\s\\r\\t]", "");
        sentenceB = sentenceB.replaceAll("[^\\w\\n\\s\\r\\t]", "");

        double[] sentenceAVector = getVectorSentence(sentenceA, model);
        double[] sentenceBVector = getVectorSentence(sentenceB, model);

        return cosineSimilarity(sentenceAVector, sentenceBVector);
    }

    public double[] getVector(String word, Word2Vec model) {
        double[] vector = new double[model.getLayerSize()];
        if (model.hasWord(word))
            vector = model.getWordVector(word);
        else
            Arrays.fill(vector, 0f);

        return vector;
    }

    public double[] getVectorSentence(String sentence, Word2Vec model) {
        double[] vectorSentence = new double[model.getLayerSize()];
        Arrays.fill(vectorSentence, 0f);

        for (String word : sentence.split("\\s")) {
            double[] wordVector = getVector(word, model);
            for (int i = 0; i < vectorSentence.length; i++) {
                vectorSentence[i] += wordVector[i];
            }
        }

        return vectorSentence;
    }

    public static double cosineSimilarity(double[] vectorA, double[] vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += Math.pow(vectorA[i], 2);
            normB += Math.pow(vectorB[i], 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }


    public static void main(String[] args) throws IOException {
        NewsAnalysis newsAnalysis = new NewsAnalysis();
        newsAnalysis.useWord2Vec("Mỹ, Hàn Quốc tập trận chung ngay trước hội nghị thượng đỉnh liên Triều",
                "Mỹ-Hàn tập trận bất chấp hội nghị thượng đỉnh liên Triều");
    }
}
