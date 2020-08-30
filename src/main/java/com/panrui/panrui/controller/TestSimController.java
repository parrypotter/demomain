package com.panrui.panrui.controller;

import com.panrui.panrui.service.sim.simService.AnalysisPositionTags;
import com.panrui.panrui.service.sim.simService.SimilarityAlgorithmService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class TestSimController {

    @Resource(name = "similarityAlgorithmService")
    private SimilarityAlgorithmService similarityAlgorithmService;

    @Resource(name = "analysisPositionTags")
    private AnalysisPositionTags analysisPositionTags;

    protected File file;

    @PostMapping("/simTest")
    private void doSimTesting(String filename) throws IOException {
        file = new File(filename);
        String[] args = new String[]{".findElement"};//
        for (String arg : args) {
            analysisPositionTags.findFile(file, arg);
            analysisPositionTags.print(arg);
        }
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        //PrintStream output ;
       /* for(int sq=0;sq<similarity_strString.length;sq++){
            HSSFRow row = sheet.createRow(sq);
            for(int sm=sq+1;sm<similarity_strString.length;sm++) {
                if((similarity_strString[sq]!=null)&&(similarity_strString[sm]!=null)) {
                    SimilarityAlgorithm = new SimilarityAlgorithm(similarity_strString[sq],similarity_strString[sm]);
                    HSSFCell cell=row.createCell(sm);
                    cell.setCellValue(SimilarityAlgorithm.sim()+"%");
                }
            }
        }*/
        FileOutputStream output1=new FileOutputStream("workbook.xls");
        wb.write(output1);
        output1.flush();
    }



}
