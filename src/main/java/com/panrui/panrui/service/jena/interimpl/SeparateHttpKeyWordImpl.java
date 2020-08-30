package com.panrui.panrui.service.jena.interimpl;



import com.panrui.panrui.service.jena.inter.SeparateHttpKeyWord;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author 作者
* @version 创建时间：2020年4月28日 上午12:25:21
* 类说明
*/

@Service("separateHttpKeyWord")
public class SeparateHttpKeyWordImpl implements SeparateHttpKeyWord {


	public BufferedReader reader;
	public File writeName;
	public BufferedWriter out;

	@Override
	public String getElement(String filePath) throws IOException {
		    String fileDataName = filePath.substring(0, filePath.lastIndexOf("."))+"__Data.txt";
		    String tempString;
		    long  line = 0;
		    File file = new File(filePath);
		    double file_length = (double) file.length();
		    writeName = new File(fileDataName);
		    writeName.createNewFile();
			out = new BufferedWriter(new FileWriter(writeName));
            reader = new BufferedReader(new FileReader(file));
            System.out.println("***************************开始写入TXT文件***************************");
	        while ((tempString = reader.readLine()) != null) {
	        	    line+=tempString.length();
	                try {
	                	String[] tString = tempString.split("\"");
		        	    String httpString = matcherHttp(tString[0]);//第一个HTTP链接
		        		String nextHttpString = tString[0].substring(httpString.length()+1);//第二个HTTP连接，但可能包含两个HTTP
		        		if(countHttp(nextHttpString)>1){
		        			String toNextHttpString = matcherHttp(nextHttpString);
		        			String lastHttpString = nextHttpString.substring(toNextHttpString.length()+1);
		        			out.write(lastHttpWord(httpString.trim())+" "+lastHttpWord(toNextHttpString.trim())+" "+lastHttpWord(lastHttpString.trim())+"\r\n");
		        			toNextHttpString = null;
		        			lastHttpString = null;
		        		}
		        		/*非三连组HTTP请求 例如：以下就是三连组链接
		        		 * <http://dbpedia.org/resource/Autism> <http://dbpedia.org/ontology/diseasesdb1> <http://dbpedia.org/ontology/diseasesdb>\"1142\" .
		        		 * */
		        		else if(countHttp(nextHttpString)==1){
		        			out.write(lastHttpWord(httpString.trim())+" "+lastHttpWord(nextHttpString.trim())+" "+tString[1]+"\r\n");
		        		     }
		        		httpString = null;
		        		nextHttpString = null;
		            	tString=null;
					} catch (Exception e) {
						e.printStackTrace();
					}	        	
	        	   
	            	System.out.println("****************"+"当前写入进度:  "+((double)line/file_length)*100+" %"+"****************");
	             }  
            if (reader != null) {
            	    out.close();
                    reader.close();
                    System.out.println("*****************************写入完成*****************************");
            }
	       
			return fileDataName;
	       
	    

	}

	@Override
	public String matcherHttp(String patternString) {
		String pString = null;
		Pattern pattern = Pattern.compile("(http://|ftp://|https://|www){0,1}[^\u4e00-\u9fa5\\s]*");
		Matcher matcher = pattern.matcher(patternString);
		if(matcher.find()){
			pString = matcher.group();
		}
		return pString;
	}

	@Override
	public int countHttp(String nextPatternString) {
		int count=0;
		Pattern pattern = Pattern.compile("http");
		Matcher matcher = pattern.matcher(nextPatternString);
		while(matcher.find()){
			count++;
		}
		return count;
	}

	@Override
	public String lastHttpWord(String httpString) {
		String httpLastWord;
		if(httpString.lastIndexOf("/")+1==httpString.length()-1){
			httpLastWord = "null";
		}else {
			httpLastWord = httpString.substring(httpString.lastIndexOf("/")+1, httpString.length()-1);
			if(isExistQuotationMask(httpLastWord)){
				httpLastWord = httpLastWord.trim().substring(0, httpLastWord.trim().length()-1);
			}
		}
		return httpLastWord;
	}

	@Override
	public boolean isExistQuotationMask(String sentence) {
		Pattern pattern = Pattern.compile("\\>");
		Matcher matcher = pattern.matcher(sentence);
		return matcher.find();
	}

	
	
	
}



