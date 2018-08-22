package com.mxp.until;


import java.io.File;

public class DeleteFile {
    public static void deleteAll(File file){

        if(file.isFile() || file.list().length ==0)
        {
            file.delete();
        }else{
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteAll(files[i]);
                files[i].delete();
            }
            if(file.exists())         //如果文件本身就是目录 ，就要删除目录
                file.delete();
        }
    }

    public static void test(String s)
    {
        File f = new File(s) ;
        String str[] = null ;
        if(f.isDirectory())//判断是不是目录
        {
            str = f.list();
            for(int i=0;i<str.length;i++)
            {
                System.out.println(s+"\\"+str[i]);
            }
        }
        else
        {
            System.out.println(s);
        }

    }

    public static void main(String[] args) {
      //  test("H:\\文件\\Working\\机械工业出版社\\程序\\17.7.10给托尔斯的排版文件（共10个）\\bookfies");
    }

}
