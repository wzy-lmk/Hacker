package team.AI.utils;


import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import team.SensitiveWord.entity.UrlInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class Excel {
    public void Excel(int row, int col, ArrayList<UrlInfo> arrayList,String filepath){
        // 创建HSSF工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建一个Sheet页
        XSSFSheet sheet = workbook.createSheet();

        sheet.setDefaultRowHeightInPoints(30);

        XSSFRow rowtitle = sheet.createRow(0);
        rowtitle.setHeight(new Short("50"));
        XSSFCell cell = rowtitle.createCell(0);
        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 19));
        cell.setCellValue("敏感词详细信息");

        XSSFCellStyle titleStyle = workbook.createCellStyle();        //标题样式
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        Font ztFont = workbook.createFont();
        ztFont.setItalic(false);                     // 设置字体为斜体字
        ztFont.setColor(Font.COLOR_NORMAL);            // 将字体设置为“红色”
        ztFont.setFontHeightInPoints((short)16);    // 将字体大小设置为18px
        ztFont.setFontName("宋体");             // 将“宋体”字体应用到当前单元格上
        ztFont.setBold(true);
        titleStyle.setFont(ztFont);

        cell.setCellStyle(titleStyle);


        int rownum=1;
        sheet.setColumnWidth(0, 400 * 15);
        sheet.setColumnWidth(1, 400 * 20);

        Iterator<UrlInfo> iterator = arrayList.iterator();

                while (iterator.hasNext()) {
                    UrlInfo urlInfo = null;
                    urlInfo = (UrlInfo) iterator.next();
                    if (urlInfo.getHits().equals("无敏感词")) {

                    } else {
                        XSSFRow row1 = sheet.createRow(rownum);
                        for (int i = 0; i < col; i++) {
                            XSSFCell cell1 = row1.createCell(i);
                            if (i == 0) {
                                cell1.setCellValue(urlInfo.getUrl());
                            }
                            if (i == 1) {
                                cell1.setCellValue(urlInfo.getHits());
                            }
                        }
                        rownum++;

                    }
            }


        // 保存到本地
        File file = new File(filepath + ".xlsx");
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
}
