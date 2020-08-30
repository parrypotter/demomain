package com.panrui.panrui.service.jena.interimpl;


import com.panrui.panrui.service.jena.inter.WordSeparation;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author 作者
* @version 创建时间：2020年5月25日 下午5:45:28
* 类说明
*/

@Service("wordSeparation")
public class WordSeparationImpl implements WordSeparation {

	@Override
	public String HandleContainNumberOrNot(String compoundWord) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < compoundWord.length(); i++) {
			int chr = compoundWord.charAt(i);
			if(chr>=65&&chr<=90){
				str.append(AddLineInterval()).append(compoundWord.charAt(i));
			}
			else if (chr>=48&&chr<=57) {
				if(i==0){str.append(compoundWord.charAt(i));}
				if(i>0&&i<compoundWord.length()-1){
					if(compoundWord.charAt(i-1)>=48&&compoundWord.charAt(i-1)<=57){
						if(compoundWord.charAt(i+1)>=97&&compoundWord.charAt(i+1)<=122){str.append(compoundWord.charAt(i)).append(AddLineInterval());}
						else{str.append(compoundWord.charAt(i));}
					}
					if(compoundWord.charAt(i-1)>=97&&compoundWord.charAt(i-1)<=122){
						if(compoundWord.charAt(i+1)>=48&&compoundWord.charAt(i+1)<=57){str.append(AddLineInterval()).append(compoundWord.charAt(i));}
						else{str.append(AddLineInterval()).append(compoundWord.charAt(i)).append(AddLineInterval());}
					}
				}
				if(i==compoundWord.length()-1){
					if (compoundWord.charAt(i - 1) >= 48)
						if (compoundWord.charAt(i - 1) <= 57) {
							str.append(compoundWord.charAt(i));
						}
					if (compoundWord.charAt(i - 1) <= 122)
						if (compoundWord.charAt(i - 1) >= 97) {
							str.append(AddLineInterval()).append(compoundWord.charAt(i));
						}
				}
			}
			else{
				str.append(compoundWord.charAt(i));
			}
		}

		return str.toString();
	}


	@Override
	public String AddSpaceInterval() {
		//空格区分
		return " ";
	}

	@Override
	public String AddUnderlineInterval() {
		//下划线区分
		return "_";
	}

	@Override
	public String AddLineInterval() {
		//横线区分
		return "-";
	}


	@Override
	public String updateStateByEmId(String inputString) {
		if(inputString.trim()!=null){
			String regEx = "[`~!@#$%^&*()\\-+={}':;,\\[\\].<>/?￥%…（）_+|【】‘；：”“’。，、？\\s]";
		     Pattern pattern = Pattern.compile(regEx);
		     Matcher matter = pattern.matcher(inputString);
		     return matter.replaceAll(" ");
		}
		return inputString;
	}

}
