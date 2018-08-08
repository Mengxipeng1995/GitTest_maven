package com.mxp.until;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class vvv {
    public static void main(String[] args) throws IOException {

        exportExcel();
    }

    public static void exportExcel() throws IOException {
//        CmActivities activity = new C
//        List<MdItems> listItem
        HSSFWorkbook workbook = new HSSFWorkbook ();// 创建一个Excel文件
        HSSFSheet sheet = workbook.createSheet("测试表");// 创建一个Excel的Sheet
        sheet.setColumnWidth(0, 40 * 256);
        sheet.setColumnWidth(1, 40 * 256);
        sheet.setColumnWidth(2, 20 * 256);
        //row 1 - activitySublect    -----------------------start
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(0);
        //活动标题
        cell1.setCellValue("测试标题");
        //合并列
        CellRangeAddress region1= new CellRangeAddress(0, 0, 0, 2);
        sheet.addMergedRegion( region1);
        row1.setHeightInPoints(40);
        //style
        HSSFCellStyle style1= workbook.createCellStyle();
        style1.setAlignment(HSSFCellStyle. ALIGN_CENTER);//水平居中
        style1.setVerticalAlignment(HSSFCellStyle. VERTICAL_CENTER);//垂直居中
        HSSFFont font1 = workbook.createFont();
//        font.setFontName("华文行楷");//设置字体名称
        font1.setFontHeightInPoints(( short)28);//设置字号
        font1.setColor(HSSFColor.RED. index);//设置字体颜色
        style1.setFont( font1);
        cell1.setCellStyle( style1);

        //row 1 - activitySublect    -----------------------end

        //row 2  - activity Date      -----------------------start
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell2 = row2.createCell(1);
//        HSSFCell cell2_0 = row2.createCell(0);
        //img    --start
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        // 将图片写入流中
        BufferedImage bufferImg = ImageIO.read(new File("D:\\英雄时刻\\1.jpg"));
        ImageIO. write(bufferImg, "PNG", outStream);
        // 利用HSSFPatriarch将图片写入EXCEL
        HSSFPatriarch patri = sheet.createDrawingPatriarch();
        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,
                ( short) 0, 1, (short ) 1,3);
        patri.createPicture( anchor, workbook.addPicture(
                outStream.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
        //img   --  end

        cell2.setCellValue( "活动时间：");
        //合并列
        CellRangeAddress region2= new CellRangeAddress(1, 1, 1, 2);
        sheet.addMergedRegion( region2);

        // 合并列
        CellRangeAddress region2_1 = new CellRangeAddress(1, 2, 0, 0);
        sheet.addMergedRegion( region2_1);
        row2.setHeightInPoints(40);
        //style
        HSSFCellStyle style2= workbook.createCellStyle();
        style2.setAlignment(HSSFCellStyle. ALIGN_CENTER);//水平居中
        style2.setVerticalAlignment(HSSFCellStyle. VERTICAL_CENTER);//垂直居中
        cell2.setCellStyle( style2);

        //row 2  - activity Date      -----------------------end

        //row 3   - activityContent       -----------------------start
        HSSFRow row3 = sheet.createRow(2);
        HSSFCell cell3 = row3.createCell(1);
        cell3.setCellValue( "11asbidbasbdasbdbasbdibiwqndnu");
        // 合并列
        CellRangeAddress region3 = new CellRangeAddress(2, 2, 1, 2);
        sheet.addMergedRegion( region3);
//        sheet.setColumnWidth(1, 31 * 256);//设置第一列的宽度是31个字符宽度
        row3.setHeightInPoints(60); //设置行的高度是50个点
        HSSFCellStyle style3= workbook.createCellStyle();
        style3.setAlignment(HSSFCellStyle. ALIGN_CENTER);//水平居中
        style3.setVerticalAlignment(HSSFCellStyle. VERTICAL_CENTER);//垂直居中
        style3.setWrapText( true);//自动换行
        cell3.setCellStyle( style3);

        //row 3   - activityContent       -----------------------end

        //row 4   - items List header  -------------------------start
        HSSFRow row4 = sheet.createRow(3);
        HSSFCell cell4_1 = row4.createCell(0);
        HSSFCell cell4_2 = row4.createCell(1);
        HSSFCell cell4_3 = row4.createCell(2);
        cell4_1.setCellValue( "商品图片" );
        cell4_2.setCellValue( "商品名称" );
        cell4_3.setCellValue( "商品价格" );

        HSSFCellStyle style4= workbook.createCellStyle();
        style4.setAlignment(HSSFCellStyle. ALIGN_CENTER);//水平居中
        style4.setVerticalAlignment(HSSFCellStyle. VERTICAL_CENTER);//垂直居中
        style4.setWrapText( true);//自动换行
        style4.setFillForegroundColor(IndexedColors. SKY_BLUE.getIndex());
        style4.setFillPattern(CellStyle. SOLID_FOREGROUND);
        HSSFFont font4 = workbook.createFont();
//        font.setFontName("华文行楷");//设置字体名称
        font4.setFontHeightInPoints(( short)15);//设置字号
        font4.setBold( true);
        style4.setFont( font4);

//        cell4.setCellStyle(style4);
        cell4_1.setCellStyle( style4);
        cell4_2.setCellStyle( style4);
        cell4_3.setCellStyle( style4);
        //row 4   - items List header  -------------------------end

        //row 5    - items List   -------------------------start
        for (int i = 0; i < 5; i++) {
            //MdItems items = listItem.get( i);
            HSSFRow row5 = sheet.createRow( i+4);
            HSSFCell cell5_1 = row5 .createCell(0);
            HSSFCell cell5_2 = row5.createCell(1);
            HSSFCell cell5_3 = row5.createCell(2);

            row5.setHeightInPoints(80);
            //商品名称
            cell5_2.setCellValue( "名字");
            //商品价格
            cell5_3.setCellValue( 25);

            HSSFCellStyle style5=workbook .createCellStyle();
            style5.setAlignment(HSSFCellStyle. ALIGN_CENTER);//水平居中
            style5.setVerticalAlignment(HSSFCellStyle. VERTICAL_CENTER);//垂直居中
            style5.setWrapText( true);//自动换行

            cell5_2.setCellStyle( style5);
            cell5_3.setCellStyle( style5);
            // img --start
            ByteArrayOutputStream outStream_item = new ByteArrayOutputStream();
            // 将图片写入流中
            BufferedImage bufferImg_item = ImageIO.read(new File("D:\\英雄时刻\\1.jpg"));
            ImageIO. write(bufferImg_item, "PNG", outStream_item);
            // 利用HSSFPatriarch将图片写入EXCEL
            HSSFPatriarch patri_item = sheet.createDrawingPatriarch();
            HSSFClientAnchor anchor_item = new HSSFClientAnchor(0, 0, 0, 0, (short ) 0, i +4, (short) 1, i+5);
            patri_item.createPicture(anchor_item , workbook.addPicture(outStream_item .toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
            // img -- end
        }
        //row 5    - items List   -------------------------end

        // 第六步，将文件存到指定位置
        try
        {
          //  String excelName="E:\\1.xls";

            FileOutputStream fout = new FileOutputStream("E:\\1.xls");
            workbook.write(fout);
            fout.close();
//            response.addHeader( "Content-Disposition", "attachment;filename="
//                    + new String(excelName.getBytes("gb2312" ), "ISO-8859-1" ));
////            response.addHeader("Content-Length", "" + 1024);
//            response.setContentType("application/vnd.ms-excel;charset=utf-8" );
            //OutputStream out = response.getOutputStream();
//            workbook.write( out);
//            out.close();
        }
        catch (Exception e )
        {
            e.printStackTrace();
        }
    }
    public static InputStream getInputStream(String path){
        InputStream inputStream=null ;
        HttpURLConnection httpURLConnection=null ;
        try{
            URL url= new URL(path );
            if(url !=null){
                httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(3000);
                httpURLConnection.setRequestMethod("GET" );
                int responseCode=httpURLConnection .getResponseCode();
                if(responseCode ==200){
                    inputStream=httpURLConnection .getInputStream();
                }
            }


        } catch(Exception e ){
            e.printStackTrace();
        }

        return inputStream ;

    }
}
