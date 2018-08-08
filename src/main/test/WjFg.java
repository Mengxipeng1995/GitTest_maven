import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WjFg {

    public static void main(String[] args) throws IOException {
        splitDemo();
    }

    public static void splitDemo() throws IOException, IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\mxp\\Desktop\\Apache日志访问量统计\\Apache日志访问量统计\\合并后Apache日志\\access_log_20180717");
        FileOutputStream fos = null;//要在循环内部创建FileOutputStream对象
        byte[] buf = new byte[1024*1024];//将文件分割成1M大小的碎片
        int len,count = 0;

        while((len=fis.read(buf))!=-1)
        {
            fos = new FileOutputStream("D:\\testNio\\tt\\"+(count++)+".log");
            fos.write(buf,0,len);
            fos.flush();
            fos.close();
        }
        fis.close();
    }
}
