package team.AI.utils;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import team.SensitiveWord.entity.UrlInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

public class Excel {
    public void Excel(int row, int col, ArrayList<UrlInfo> arrayList){
        int j = 0;
        // 创建HSSF工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建一个Sheet页
        XSSFSheet sheet = workbook.createSheet();

        sheet.setColumnWidth(0, 400 * 15);
        sheet.setColumnWidth(1, 400 * 20);

        Iterator<UrlInfo> iterator = arrayList.iterator();

            for(int x=0;x<row;x++){
                UrlInfo urlInfo=null;
                if(iterator.hasNext()){
                    urlInfo = (UrlInfo)iterator.next();
                }
                XSSFRow row1 = sheet.createRow(x);
                for (int i = 0; i < col; i++) {
                    XSSFCell cell1 = row1.createCell(i);
                    if(i==0){
                        cell1.setCellValue(urlInfo.getUrl());
                    }
                    if(i==1){
                        cell1.setCellValue(urlInfo.getHits());
                    }
                }
                j++;
            }

        InputStream iStream = Excel.class.getClassLoader().getResourceAsStream("downloadAddr.properties");
        Properties properties = new Properties();
        try {
            properties.load(iStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String address = properties.getProperty("address");
        SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_MM_SS");
        // 保存到本地
        File file = new File(address+"'" + df.format(new Date()) + "'.xlsx");
        FileOutputStream outputStream;
        try {
            // 将Excel写入输出流中
            outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Excel excel=new Excel();
        UrlInfo urlInfo=new UrlInfo();
        urlInfo.setUrl("http://www.chzu.edu.cn/login/htm");
        urlInfo.setHits("各种敏感词长度应该还好");

        UrlInfo urlInfo1=new UrlInfo();
        urlInfo1.setUrl("http://www.chzu.edu.cn/login/htm");
        urlInfo1.setHits("各敏感");


        ArrayList<UrlInfo> arrayList=new ArrayList<UrlInfo>();
        arrayList.add(urlInfo);
        arrayList.add(urlInfo1);
        excel.Excel(2,2,arrayList);
        System.out.println("结束");

    }
}
