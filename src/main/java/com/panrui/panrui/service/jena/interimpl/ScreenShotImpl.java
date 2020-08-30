package com.panrui.panrui.service.jena.interimpl;

import com.panrui.panrui.service.jena.inter.ScreenShot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;

@Service("screenShot")
public class ScreenShotImpl implements ScreenShot {


	public File dir;
	public boolean mkdirs;

	@Override
	public void doScreenshot(WebDriver driver,String pictureName) {

	        dir = new File("picture");
	        if (!dir.exists()) {
	        	mkdirs = dir.mkdirs();
			}
	        //保存截图文件
			if(mkdirs){
				try {
					File shot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(shot, new File( dir.getAbsolutePath() + "/" + pictureName+".jpg"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

	 }

	}


