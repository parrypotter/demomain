package com.panrui.panrui.service.sim.simService;

import java.util.List;
import java.util.Map;

public interface SimilarityAlgorithmService {
    void SimilarityAlgorithm(String string1, String string2);
    double sim();
    double sqrtMulti(Map<String, int[]> vectorMap2);
    double squares(Map<String, int[]> vectorMap2);
    List<String> segStr(String content);
    double pointMulti(Map<String, int[]> vectorMap2);
}
