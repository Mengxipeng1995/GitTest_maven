package com.mxp.until;

import java.io.*;

public class CreateTxt {
    public static void main(String[] args) {
        File f = new File("D:\\temp.txt");
        OutputStream out = null;
        try{
            out = new FileOutputStream(f);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        String ss = "cName\t\tcCode\t\tcreateDate\n";
        ss += "dbc券\t\t111188\t\t2017-05-14\n";
        ss += "zc券\t\t111199\t\t2017-05-14\n";
        // 将字符串转成字节数组
        byte b[] = ss.getBytes();
        try
        {
            // 将byte数组写入到文件之中
            out.write(b);
            out.close();
        }catch (IOException e1){
            e1.printStackTrace();
        }


        System.out.println("write end>>>>>>>>>>>");
        // 以下为读文件操作
//        InputStream in = null;
//        try{
//            in = new FileInputStream(f);
//        }catch (FileNotFoundException e3){
//            e3.printStackTrace();
//        }
//
//        // 开辟一个空间用于接收文件读进来的数据
//        byte b1[] = new byte[1024];
//        int i = 0;
//        try{
//            // 将b1的引用传递到read()方法之中，同时此方法返回读入数据的个数
//            i = in.read(b1);
//            in.close();
//        }catch (IOException e4){
//            e4.printStackTrace();
//        }
//
//        // 将byte数组转换为字符串输出
//        System.out.println(new String(b1, 0, i));
//        System.out.println("read end>>>>>>>>>>>");

    }
}
