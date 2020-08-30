package com.panrui.panrui.service.sim.simServiceImpl;

import com.panrui.panrui.service.sim.simService.AnalysisPositionTags;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;
import org.springframework.stereotype.Service;
import java.awt.*;
import java.io.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("analysisPositionTags")
public class AnalysisPositionTagsImpl implements AnalysisPositionTags {


    public static int index_page=1,les=0,combintion_state=0;
    public static int mount = 0;
    public DefaultCategoryDataset bar_chart_dataset = new DefaultCategoryDataset();
    public HSSFWorkbook wbs = new HSSFWorkbook();
    public HSSFSheet sheets = wbs.createSheet("sheet1");
    public HSSFCellStyle cellStyle1 = wbs.createCellStyle();
    public HSSFCellStyle cellStyle2 = wbs.createCellStyle();


    @Override
    public boolean isTrueFile(File file) {
        if(!file.exists() || !file.canRead())
            return false;
        if (file.getName().startsWith("."))
            return false;
        return !file.getName().endsWith(".");
    }

    @Override
    public float pattern(String text, String content) {
        Pattern pattern=Pattern.compile(content);
        Matcher matcher = pattern.matcher(text);
        float lines=0;
        while(matcher.find()){
            lines++;
        }
        return lines;
    }

    @Override
    public void findFile(File file, String word) {
        File[] listFiles = file.listFiles();
        //得到一个File数组，它默认是按文件最后修改日期排序的
        for (File listFile : Objects.requireNonNull(listFiles)) {
            if (listFile.isDirectory())
                findFile(listFile, word);
            else if (isTrueFile(listFile))
                search(listFile, word);
        }
    }

    @Override
    public String[] CatchFormExcel(String filename_path, String excel_path) throws IOException {
        String [] user_info = new String[3];
        File excelFile = new File(excel_path);
        InputStream is = new FileInputStream(excelFile);
        XSSFWorkbook wb = new XSSFWorkbook(is);
        XSSFSheet sheet = wb.getSheetAt(0);
        for(int i=0;i<sheet.getLastRowNum();i++){
            Row row=sheet.getRow(i);
            Pattern pattern=Pattern.compile(row.getCell(0).getStringCellValue());
            Matcher matcher = pattern.matcher(filename_path);
            if(matcher.find()) {
                user_info[0]=row.getCell(0).getStringCellValue();
                user_info[1]=String.valueOf(row.getCell(1).getNumericCellValue());
                user_info[2]=row.getCell(2).getStringCellValue();
            }

        }
        if(user_info[0]==null) {
            user_info[0]=user_info[2]="未填写真实信息，无法匹配！";
            user_info[1]="0";
        }

        return user_info;
    }

    @Override
    public String[] search(File file, String word) {
        String[] similarity_strString=new String[15000];
        try
        {
            int j = 0, ch = 0,k=0,add=0;
            String [] user_info = new String[3];
            boolean count=false;
            StringBuilder str = null;
            StringBuilder strargsString= new StringBuilder();
            Float[] qw_count=new Float[7];
            // float lines_args=0;
            FileReader in = new FileReader(file);
            File writename = new File("output.txt");
            final boolean newFile = writename.createNewFile();
            PrintStream out = new PrintStream(new FileOutputStream(writename,true));

            while ((ch = in.read()) != -1)
            {
                str.append((char) ch);
            }
            if (str != null)
            {
                while (str.indexOf(word, j) != -1)
                {
                    k++;
                    j = str.indexOf(word, j) + 1; // 返回第一次出现的指定子字符串在此字符串中的索引
                }
            }
            if (k > 0)
            {
                try {
                    out.println("（"+index_page+"） . "+"在文件" + file.getAbsolutePath() + "中：  ");
                    user_info=CatchFormExcel(file.getAbsolutePath(), "C:\\Users\\parry\\Desktop\\比赛数据\\2018夏季预选赛.xlsx");
                    out.println("选手姓名："+user_info[0]+"     所在学校："+user_info[2]);

                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                HSSFRow first_row = sheets.createRow(0);
                HSSFCell first_cell=first_row.createCell(0);
                first_cell.setCellValue("姓名");
                HSSFCell second_cell=first_row.createCell(1);
                second_cell.setCellValue("实际得分");
                HSSFCell third_cell=first_row.createCell(2);
                third_cell.setCellValue("评价");
                HSSFCell fourth_cell=first_row.createCell(3);
                fourth_cell.setCellValue("wait.unit等待时间方法");

                HSSFRow rows = sheets.createRow(index_page);
                HSSFCell cells=rows.createCell(0);
                cells.setCellValue(user_info[0]);
                HSSFCell cellss=rows.createCell(1);
                cellss.setCellValue(user_info[1]);
                index_page++;
                String[] args ={"Thread.sleep",".until","By.xpath","By.id","By.className","By.cssSelector","By.name","By.linkText","By.tagName"};
                HSSFCell cellsss=rows.createCell(3);
                for(int sw=0;sw<args.length;sw++){
                    float lines=pattern(str.toString(),args[sw]);
                    if((int)lines!=0){
                        count=true;
                        if(sw>1){//排除只有线程等待没有具体定位方法的情况
                            add++;
                            for(int m=0;m<(int)lines;m++){
                                strargsString.append(args[sw]).append(" ");
                            }
                        }

                    }
                    if(count){
                        try {

                            if(args[sw].equals(".until")) {
                                out.println(" 等待时间：" +"wait"+args[sw]+"有"+(int)lines+"个");
                                k=k+(int)lines;
                                cellsss.setCellValue(String.valueOf(lines));

                            }
                            else if((!args[sw].equals("Thread.sleep"))&&(!args[sw].equals(".until"))){
                                qw_count[sw-2]=(lines/(float)k);
                                bar_chart_dataset.addValue( qw_count[sw-2]*100 , args[sw] ,String.valueOf(index_page-1) );
                                out.println(" 关键字：" + args[sw]+"有"+(int)lines+"个, 占总体查找使用方法比重："+qw_count[sw-2]*100+"%");
                            }else{
                                out.println(" 等待时间："+args[sw]+"有"+(int)lines+"个");
                            }
                            if((cellsss.getStringCellValue()).equals("")) {
                                cellsss.setCellValue("0");
                            }
                        } catch (Exception e) {
                            // TODO: handle exception
                            e.printStackTrace();
                        }

                        count=false;
                    }
                }
                similarity_strString[les]= strargsString.toString();
                les++;
                HSSFCell cellssss=rows.createCell(2);
                if(add>0){
                    mount++;
                    for (Float aFloat : qw_count) {
                        if (aFloat != null) {
                            if (((add == 3)) && ((aFloat <= 0.6) && (aFloat >= 0.04))) {
                                combintion_state = 3;
                                //out.println("代码结构相对多元，书写较为优异！");
                            }
                            if ((add >= 4) && ((aFloat <= 0.5) && (aFloat >= 0.06))) {
                                combintion_state = 2;
                                //out.println("代码结构多元，书写优异！");
                            } else {
                                combintion_state = 1;
                                //out.println("代码结构相对简单，应用定位方法较少！");
                            }
                        }


                    }
                    qw_count=null;
                    if(combintion_state==3){
                        out.println("代码结构相对多元，书写较为优异！");
                        cellssss.setCellValue("良");
                        if((Float.parseFloat(user_info[1].trim())>=75.0)) {
                            cellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);  //填充单元格
                            cellStyle1.setFillForegroundColor(IndexedColors.BLUE.index);    //填蓝色
                            cells.setCellStyle(cellStyle1);
                            cellss.setCellStyle(cellStyle1);
                            cellssss.setCellStyle(cellStyle1);

                        }
                    }
                    if(combintion_state==2){
                        out.println("代码结构多元，书写优异！");
                        cellssss.setCellValue("优");
                        if(Float.parseFloat(user_info[1].trim())>=75.0) {
                            cellStyle2.setFillPattern(FillPatternType.SOLID_FOREGROUND);  //填充单元格
                            cellStyle2.setFillForegroundColor(IndexedColors.RED.index);    //填红色
                            cells.setCellStyle(cellStyle2);
                            cellss.setCellStyle(cellStyle2);
                            cellssss.setCellStyle(cellStyle2);
                        }

                    }
                    else if(combintion_state==1){
                        out.println("代码结构相对简单，应用定位方法较少！");
                        cellssss.setCellValue("中");

                        if((Float.parseFloat(user_info[1].trim())>=75.0)) {
                            cellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);  //填充单元格
                            cellStyle1.setFillForegroundColor(IndexedColors.BLUE.index);    //填蓝色
                            cells.setCellStyle(cellStyle1);
                            cellss.setCellStyle(cellStyle1);
                            cellssss.setCellStyle(cellStyle1);
                        }
                    }
                    out.println("得分：   "+user_info[1]);

                }
                System.out.println(index_page-1+"："+file.getName()+"已完成");

            }
            FileOutputStream output1s=new FileOutputStream("设计打分相关性比较.xls");
            wbs.write(output1s);
            output1s.flush();
            JFreeChart barChartObject = ChartFactory.createStackedBarChart(
                    "定位方法占比","提交学生编号",
                    "单位：%",
                    bar_chart_dataset, PlotOrientation.VERTICAL,
                    true,true,false);
            CategoryPlot plot = (CategoryPlot)barChartObject.getPlot();
            CategoryAxis domainAxis=plot.getDomainAxis();
            ValueAxis numberaxis=plot.getRangeAxis();
            TextTitle textTitle= barChartObject.getTitle();
            textTitle.setFont(new Font("宋体",Font.BOLD,22));
            domainAxis.setLabelFont(new Font("宋体", Font.BOLD,20));
            domainAxis.setTickLabelFont(new Font("黑体", Font.BOLD, 10));
            domainAxis.setAxisLineStroke(new BasicStroke(1.6f));
            numberaxis.setUpperMargin(0.1);
            barChartObject.getLegend().setItemFont(new Font("黑体", Font.BOLD, 12));
            barChartObject.getLegend().setPosition(RectangleEdge.RIGHT);
            barChartObject.getLegend().setBackgroundPaint(Color.gray);
            barChartObject.setBorderStroke(new BasicStroke(1));
            barChartObject.setBackgroundPaint( new Color(253,231,243));
            StackedBarRenderer renderer =  new StackedBarRenderer();
            renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
            renderer.setBaseItemLabelsVisible(true);
            renderer.setItemMargin(-0.01);
            renderer.setMaximumBarWidth(0.05);
            plot.setRenderer(renderer);
            plot.setBackgroundPaint(Color.white);
            plot.setDomainGridlinePaint(Color.white);
            plot.setDomainGridlinesVisible(true);
            plot.setRangeGridlinePaint(Color.white);
            plot.setNoDataMessage("No data available");
            plot.setBackgroundPaint(Color.lightGray);
            int width = 3800;    /* Width of the image */
            int height = 800;   /* Height of the image */
            File lineChart = new File( file.getParentFile().getParentFile().getParentFile().getName()+".jpeg" );
            ChartUtilities.saveChartAsJPEG(lineChart ,barChartObject, width ,height);
            in.close();
            out.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return similarity_strString;
    }

    @Override
    public void print(String word) {
        if (mount != 0)
        {
            System.out.println("一共找到" + mount + " 个文件包含有效查找关键字" + "! \n");
            mount=0;
        }
        else
        {
            System.out.println("没有找到相应的文件");
        }
    }
}
