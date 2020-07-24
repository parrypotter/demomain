package com.panrui.panrui.bean;
import java.util.HashMap;

public class ArticleSimilarity {
    private short sid;
    private String simUid;
    private HashMap<Integer,Integer> articleSequence;
    private float similarityResult;

    public short getSid() {
        return sid;
    }

    public void setSid(short sid) {
        this.sid = sid;
    }

    public String getSimUid() {
        return simUid;
    }

    public void setSimUid(String simUid) {
        this.simUid = simUid;
    }

    public HashMap<Integer, Integer> getArticleSequence() {
        return articleSequence;
    }

    public void setArticleSequence(HashMap<Integer, Integer> articleSequence) {
        this.articleSequence = articleSequence;
    }

    public float getSimilarityResult() {
        return similarityResult;
    }

    public void setSimilarityResult(float similarityResult) {
        this.similarityResult = similarityResult;
    }
}
