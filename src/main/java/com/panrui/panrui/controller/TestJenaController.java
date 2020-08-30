package com.panrui.panrui.controller;

import com.panrui.panrui.service.jena.inter.MatchSearch;
import com.panrui.panrui.service.jena.inter.SeparateHttpKeyWord;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import javax.annotation.Resource;
import java.io.IOException;

@Controller
public class TestJenaController {


    private WebDriver driver;

    /*搜索地址*/
    public final String searchPathStringForBing = "https://www.bing.com/?ensearch=1&FORM=BEHPTB";
    public final String searchPathStringForGoogle = "https://www.google.com/";

    /*Google专用class*/
    private final String []classElementForGoogle = {"[class='Z0LcW XcVN5d']","[class='e24Kjd']","[class='FLP8od']",
            "[class='iKJnec']","[class='LGOjhe']","[class='HwtpBd gsrt PZPZlf']",
            "[class='vtnSJf TO2KC GQFnxe NcWIC LiCnEc']","[class='EDblX DAVP1']","[class='mod']",
            "[class='N6Sb2c i29hTd']","[class='PVhuid kno-mrg kno-swp']","[class='Z0LcW XcVN5d AZCkJd']",
            "[class='Z0LcW XcVN5d AZCkJd']","[class='ABd7Db']","[class='LwV4sf']","[class='kIXOkb']",
            "[class='vk_bk dDoNo XcVN5d']","[class='EquSJe']"};

    /*Bing专用class */
    private final String []classElementForBing = {"[class='b_focusTextLarge']","[class='b_focusTextMedium']","[class='b_focusTextMedium b_1linetrunc']",
            "[class='b_topTitle b_lBottom']","[class='rwrl rwrl_pri rwrl_padref']","[class='b_entityTitle']",
            "[class='b_topTitle b_lBottom']","[class='carousel-title']","[class='b_promtxt']",
            "[class='b_attribution']"
    };

    /*搜索框定位地址*/
    private final String [] positionElement ={
            "[class='b_searchbox']",
            "[class='b_searchbox b_softkey']",
            "[class='gLFyf gsfi']"
    };

    /*打印结果信息*/
    private final String loggerText = "logger.txt";

    @Resource(name = "separateHttpKeyWord")
    private SeparateHttpKeyWord separateHttpKeyWord;

    @Resource(name = "matchSearch")
    private MatchSearch matchSearch;

    private WebDriver getDriver(String locate) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=" + locate);
        return new ChromeDriver(options);
    }

    @PostMapping("/Bing/doTesting")
    private void doBingSearchTesting(String filePath){
        try {
            String fileDataName = separateHttpKeyWord.getElement(filePath);
            driver = getDriver("en-GB");
            matchSearch.doComparedSearch(driver, searchPathStringForBing, fileDataName,classElementForBing,positionElement,loggerText);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            driver.quit();
        }

    }

    @PostMapping("/Google/doTesting")
    private void doGoogleSearchTesting(String filePath){
        try {
            String fileDataName = separateHttpKeyWord.getElement(filePath);
            driver = getDriver("en-GB");
            matchSearch.doComparedSearch(driver, searchPathStringForGoogle, fileDataName,classElementForGoogle,positionElement,loggerText);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            driver.quit();
        }

    }
}
