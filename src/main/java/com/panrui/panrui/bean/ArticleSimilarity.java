package com.panrui.panrui.bean;
import lombok.Data;
import java.util.HashMap;


@Data
public class ArticleSimilarity {
    private short sid;
    private String simUid;
    private HashMap<Integer,Integer> articleSequence;
    private float similarityResult;

}
