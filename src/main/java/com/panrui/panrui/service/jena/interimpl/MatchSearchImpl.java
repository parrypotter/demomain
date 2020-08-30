package com.panrui.panrui.service.jena.interimpl;


import com.panrui.panrui.service.jena.inter.MatchSearch;
import com.panrui.panrui.service.jena.inter.ScreenShot;
import com.panrui.panrui.service.jena.inter.WordSeparation;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
* @author 作者
* @version 创建时间：2020年4月28日 下午11:19:27
* 类说明
* 1、先将数据文件转为txt，然后读取txt文件
* 2、根据行数拿取数据
*/
@Service("matchSearch")
public class MatchSearchImpl implements MatchSearch {


	@Resource(name = "screenShot")
	public ScreenShot screenShot;

	@Resource(name = "wordSeparation")
	public WordSeparation wordSeparation;

	public File filename;
	public File writeName;
	public BufferedWriter out;
	public InputStreamReader reader;
	public BufferedReader br;

	@Override
	public  void doComparedSearch(WebDriver driver,String searchPathString,String fileDataName,String []classElement,String []positionElement,String loggerText) throws IOException{

	 	System.out.println("***************即将开始搜索***************");
		driver.get(searchPathString);
		int lineCount = 0;
		int countClass = 0;
		filename = new File(fileDataName);
		try {
			writeName = new File(loggerText);
			if(writeName.createNewFile());
			out = new BufferedWriter(new FileWriter(writeName));
			reader = new InputStreamReader(new FileInputStream(filename));
			br = new BufferedReader(reader);
			String lineString = br.readLine();
			while(lineString!=null){
				String [] arraySearchWord = lineString.split("\\s+");
				StringBuilder elements = new StringBuilder();
				if(arraySearchWord.length<3){
					out.write("<"+lineString+"缺少关键词"+">"+"\r\n");
					lineString = br.readLine();
					arraySearchWord = null;
				}
				else{
					for (String searchId : positionElement) {
						if(isJudgingElement(driver, searchId)){
							WebElement textElement = driver.findElement(By.cssSelector(searchId));
							if(textElement.getText()!=null){
								textElement.clear();
							}
							textElement.sendKeys("the"+" "+wordSeparation.HandleContainNumberOrNot(arraySearchWord[1])+" "+
									"of"+" "+wordSeparation.updateStateByEmId(arraySearchWord[0]));
							textElement.sendKeys(Keys.ENTER);
							break;

						}
					}
					for (String classId : classElement) {
						if(isJudgingElement(driver, classId)){
							elements.append(driver.findElement(By.cssSelector(classId)).getText());
							if(isSimilarity(wordSeparation.updateStateByEmId(arraySearchWord[2]),elements.toString())){
								out.write("<"+wordSeparation.updateStateByEmId(arraySearchWord[0])+", "+wordSeparation.HandleContainNumberOrNot(arraySearchWord[1])+
										", "+wordSeparation.updateStateByEmId(arraySearchWord[2])+", "+wordSeparation.updateStateByEmId(elements.toString())+", "+"true"+">"+"\r\n");
								screenShot.doScreenshot(driver, ++lineCount +"."+arraySearchWord[0]+"_"+arraySearchWord[1]+"_"+"pass");
							}else{
								out.write("<"+wordSeparation.updateStateByEmId(arraySearchWord[0])+", "+wordSeparation.HandleContainNumberOrNot(arraySearchWord[1])+
										", "+wordSeparation.updateStateByEmId(arraySearchWord[2])+", "+elements.toString()+", "+"false"+">"+"\r\n");
								screenShot.doScreenshot(driver, ++lineCount +"."+arraySearchWord[0]+"_"+arraySearchWord[1]+"_"+"fail");
							}
							elements.delete(0,elements.length());
							break;
						}else{
							countClass=countClass+1;
						}

					}
					if(countClass>classElement.length-1){
						out.write("<"+wordSeparation.updateStateByEmId(arraySearchWord[0])+", "+wordSeparation.HandleContainNumberOrNot(arraySearchWord[1])
								+", "+wordSeparation.updateStateByEmId(arraySearchWord[2])+", "+"NULL"+", "+"NotExist"+">"+"\r\n");
						screenShot.doScreenshot(driver, ++lineCount +"."+arraySearchWord[0]+"_"+arraySearchWord[1]+"_"+"NotExist");
					}

					lineString = br.readLine();
					arraySearchWord = null;
					countClass = 0;
				}

			}
			br.close();
			reader.close();
			out.close();
			System.out.println("***************搜索结束***************");

		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
	}

	@Override
	public boolean isSimilarity(String compareWord, String beComparedSentence) {
		Pattern pattern =Pattern.compile(compareWord.trim());
		Matcher matcher = pattern.matcher(beComparedSentence.trim());
		return matcher.find();
	}
	
	@Override
	public boolean isJudgingElement(WebDriver webDriver, String by) {
        try {
            webDriver.findElement(By.cssSelector(by));
            return true;
        } catch (Exception e) {
            return false;
        }
    }



	

	
}
