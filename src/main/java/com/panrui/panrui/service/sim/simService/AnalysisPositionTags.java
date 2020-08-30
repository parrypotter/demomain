package com.panrui.panrui.service.sim.simService;

import java.io.File;
import java.io.IOException;

public interface AnalysisPositionTags {
    boolean isTrueFile(File file);
    float pattern(String text,String content);
    void findFile(File file, String word);
    String[] CatchFormExcel(String filename_path,String excel_path) throws IOException;
    String[] search(File file, String word);
    void print(String word);

}
