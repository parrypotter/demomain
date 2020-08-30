package com.panrui.panrui.service.jena.inter;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
* @author 作者
* @version 创建时间：2020年4月28日 上午12:24:00
* 类说明
*/
public interface MatchSearch   {

	void doComparedSearch(WebDriver driver,String searchPathString,String fileDataName,String[] classElement,String []positionElement,String loggerText) throws IOException;
	boolean isSimilarity(String compareWord,String beComparedSentence);
	boolean isJudgingElement(WebDriver webDriver, String by);
}
