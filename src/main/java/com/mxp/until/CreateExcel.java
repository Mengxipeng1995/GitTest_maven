package com.mxp.until;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.formula.functions.T;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateExcel {


    /**
     * @功能：手工构建一个简单格式的Excel
     */
    private static List<T> getStudent() throws Exception {
        List list = new ArrayList();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");

//        Student user1 = new Student(1, "张三", 16, df.parse("1997-03-12"));
//        Student user2 = new Student(2, "李四", 17, df.parse("1996-08-12"));
//        Student user3 = new Student(3, "王五", 26, df.parse("1985-11-12"));
//        list.add(user1);
//        list.add(user2);
//        list.add(user3);

        return list;
    }

    public static void main(String[] args) throws Exception {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("学生表一");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式


        HSSFCell cell = row.createCell((short) 0);





        cell.setCellValue("学号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("年龄");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("生日");
        cell.setCellStyle(style);



        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        List list = Arrays.asList("","");

        for (int i = 0; i < 2; i++) {
            row = sheet.createRow((int) i + 1);
            row.setHeight((short)(10*100));

            //Student stu = (Student) list.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue("6666666");

            row.createCell((short) 1).setCellValue("777"+new HSSFRichTextString("\r\n"));
            //img    --start
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            // 将图片写入流中
            BufferedImage bufferImg = ImageIO.read(new File("D:\\英雄时刻\\"+ (i+1) +".jpg"));
            ImageIO. write(bufferImg, "JPG", outStream);
            // 利用HSSFPatriarch将图片写入EXCEL
            HSSFPatriarch patri = sheet.createDrawingPatriarch();
        /*
        *   dx1：起始单元格的x偏移量，如例子中的255表示直线起始位置距A1单元格左侧的距离；
            dy1：起始单元格的y偏移量，如例子中的125表示直线起始位置距A1单元格上侧的距离；
            dx2：终止单元格的x偏移量，如例子中的1023表示直线起始位置距C3单元格左侧的距离；
            dy2：终止单元格的y偏移量，如例子中的150表示直线起始位置距C3单元格上侧的距离；
            col1：起始单元格列序号，从0开始计算；
            row1：起始单元格行序号，从0开始计算，如例子中col1=0,row1=0就表示起始单元格为A1；
            col2：终止单元格列序号，从0开始计算；
            row2：终止单元格行序号，从0开始计算，如例子中col2=2,row2=2就表示起始单元格为C3；
        * */
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 50, 0, ( short) 2, 2, (short ) 1,1);
            patri.createPicture( anchor, wb.addPicture(outStream.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
            //img   --  end


            row.createCell((short) 2).setCellValue("888");


            cell = row.createCell((short) 3);
            cell.setCellValue(String.valueOf(LocalDateTime.now()));
        }
        // 第六步，将文件存到指定位置
        try {
            FileOutputStream fout = new FileOutputStream("E:/students.xls");
            wb.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
