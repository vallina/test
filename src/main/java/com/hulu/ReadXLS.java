package com.hulu;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by likg on 2017-03-22.
 */
public class ReadXLS {


    public static void main(String args[]) {
        //getExcelData();

        writeExcel(new File("D:/a.xls"));

    }


    public static  Object[][] getExcelData() {
        try {
            Workbook book = Workbook.getWorkbook(new File("D:/a.xls"));
            //获得第一个工作表对象
            Sheet sheet = book.getSheet(0);

            int rows = sheet.getRows();

            Object[][] datas = new Object[rows][3];

            for (int i=0; i<rows; i++) {
                Cell cell1 = sheet.getCell(0, i);
                Cell cell2 = sheet.getCell(1, i);
                Cell cell3 = sheet.getCell(2, i);

                Object[] data = new Object[3];
                data[0] = cell1.getContents();
                data[1] = cell2.getContents();
                data[2] = cell3.getContents();

                datas[i] = data;

            }

            System.out.println(Arrays.deepToString(datas));

            book.close();

            return datas;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }



    // 写文件
    public static void writeExcel(File file) {
        try {

            Workbook wb = Workbook.getWorkbook(file);

            // 创建文件
            WritableWorkbook book = Workbook.createWorkbook(file, wb);

            String[] sheetNames = book.getSheetNames();
            System.out.println("sheetNames===="+ Arrays.toString(sheetNames));

            WritableSheet sheet = book.getSheet(0);

            sheet.addCell(new Label(0, 0, "中文"));


            book.write();
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
