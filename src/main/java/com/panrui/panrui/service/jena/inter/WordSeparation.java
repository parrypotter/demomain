package com.panrui.panrui.service.jena.inter;
/**
* @author 作者
* @version 创建时间：2020年5月25日 下午3:58:31
* 类说明
*/
public interface WordSeparation {
	
	String HandleContainNumberOrNot(String compoundWord);
	String updateStateByEmId(String inputString);
	String AddSpaceInterval();
	String AddUnderlineInterval();
	String AddLineInterval();
}
