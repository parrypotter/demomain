package com.panrui.panrui.service.jena.inter;

import java.io.IOException;

public interface SeparateHttpKeyWord {
    String getElement(String fileName) throws IOException;
    String matcherHttp(String patternString);
    int countHttp(String nextPatternString);
    String lastHttpWord(String httpString);
    boolean isExistQuotationMask(String sentence);
}
