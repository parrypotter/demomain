package com.panrui.panrui.service.sim.simServiceImpl;

import com.panrui.panrui.service.sim.simService.SimilarityAlgorithmService;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.*;


@Service("similarityAlgorithmService")
public class SimilarityAlgorithmServiceImpl implements SimilarityAlgorithmService {

    public Map<String, int[]> vectorMap = new HashMap<>();
    public int[] tempArray = null;

    @Override
    public void SimilarityAlgorithm(String string1, String string2) {
        List<String> words1 = segStr(string1);
        List<String> words2 = segStr(string2);
        for (String value : words1) {
            if (vectorMap.containsKey(value)) {
                vectorMap.get(value)[0]++;
            } else {
                tempArray = new int[2];
                tempArray[0] = 1;
                tempArray[1] = 0;
                vectorMap.put(value, tempArray);
            }
        }

        for (String s : words2) {
            if (vectorMap.containsKey(s)) {
                vectorMap.get(s)[1]++;
            } else {
                tempArray = new int[2];
                tempArray[0] = 0;
                tempArray[1] = 1;
                vectorMap.put(s, tempArray);
            }
        }
    }

    @Override
    public double sim() {
        return (pointMulti(vectorMap) / sqrtMulti(vectorMap))*100.0;
    }

    @Override
    public double sqrtMulti(Map<String, int[]> vectorMap2) {
        return Math.sqrt(squares(vectorMap2));
    }

    @Override
    public double squares(Map<String, int[]> vectorMap2) {
        double result1 = 0;
        double result2 = 0;
        Set<String> keySet = vectorMap2.keySet();
        for (String character : keySet) {
            int[] temp = vectorMap2.get(character);
            result1 += (temp[0] * temp[0]);
            result2 += (temp[1] * temp[1]);
        }
        return result1 * result2;
    }

    @Override
    public List<String> segStr(String content) {
        // 分词
        Reader input = new StringReader(content);
        // 智能分词关闭（对分词的精度影响很大）
        IKSegmenter iks = new IKSegmenter(input, true);
        Lexeme lexeme = null;
        List<String> list = new ArrayList<String>();

        try {
            while ((lexeme = iks.next()) != null) {
                list.add(lexeme.getLexemeText());
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public double pointMulti(Map<String, int[]> vectorMap2) {
        double result = 0;
        Set<String> keySet = vectorMap2.keySet();
        for (String character : keySet) {
            int[] temp = vectorMap2.get(character);
            result += (temp[0] * temp[1]);
        }
        return result;
    }
}
