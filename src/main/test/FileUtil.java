import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 实现思路：将文件分成 n块，每块用不同线程去下载，
 * <p>
 * <p>
 * 文件下载工具 by sam on 2015/11/5.
 */
final class FileUtil {

    /**
     * 单线程下载文件
     *
     * @param url       文件的网络地址
     * @param path      保存的文件路径
     * @param threadNum 想要开启的线程数
     */
    public static void dowanload(String url, String path, int threadNum)
            throws IOException {
        System.out.println("下载中...");
        InputStream inputStream = null;
        RandomAccessFile randomAccessFile = null;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(10 * 1000);
            File file = new File(path);
            if (!file.getParentFile().exists())
                file.getParentFile().mkdir();
            if (file.exists())
                file.delete();
            file.createNewFile();
            int responseCode = urlConnection.getResponseCode();
            if (responseCode >= 200 && responseCode < 300) {
                int fileSize = urlConnection.getContentLength();//获取文件大小
                randomAccessFile = new RandomAccessFile(file, "rwd");
                randomAccessFile.setLength(fileSize);//设置文件大小
                randomAccessFile.close();//设置文件大小后关闭

                //获取线程要处理的块数
                int value = fileSize % threadNum;
                int unit = fileSize / threadNum;//文件平均每块大小
                int block = 0 == value ? threadNum : threadNum + 1; //如果不整除，分多一块处理
                for (int i = 0; i < block; i++) {
                    int startMark = i * unit;//开始下载位置
                    int endMark = (i + 1) * unit - 1;//下载末端位置
                    DownloadFileThread thread = new DownloadFileThread(url, file, startMark, endMark);
                    thread.start();
                }

            } else {
                System.out.println("服务器异常...");
            }
        } finally {
            if (null != inputStream) {
                inputStream.close();
            }
            if (null != randomAccessFile) {
                randomAccessFile.close();
            }
        }
    }

    /***
     * 下载文件块线程
     */
    private static class DownloadFileThread extends Thread {
        private String url;
        private File file;
        private int startMark;
        private int endMark;

        public DownloadFileThread(String url, File file, int startMark, int endMark) {
            this.url = url;
            this.file = file;
            this.startMark = startMark;
            this.endMark = endMark;
        }


        @Override
        public void run() {
            InputStream inputStream = null;
            RandomAccessFile randomAccessFile = null;
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setConnectTimeout(10 * 1000);
                //请求指定文件的位置，表明当前的一块获取的开始下载位置到末端位置
                urlConnection.setRequestProperty("Range", "bytes=" + startMark + "-" + endMark);
                int responseCode = urlConnection.getResponseCode();
                if (responseCode >= 200 && responseCode < 300) {
                    inputStream = urlConnection.getInputStream();
                    int len = 0;
                    byte[] data = new byte[4096];
                    randomAccessFile = new RandomAccessFile(file, "rwd");
                    randomAccessFile.seek(startMark);//设置往文件写入部分
                    while (-1 != (len = inputStream.read(data))) {
                        randomAccessFile.write(data, 0, len);
                    }
                    System.out.println("下载完成..." + getName());
                } else {
                    System.out.println("服务器异常...");
                }
            } catch (Exception ex) {

            } finally {
                if (null != inputStream) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (null != randomAccessFile) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    @Test
    public static byte[] mains() {
        try {
            File file = new File("E:\\2.txt");
            if (file.isFile() && file.exists()) {
//读取的时指定GBK编码格式，若中文出现乱码请尝试utf-8，window默认编码格式为GBK
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));
                String s = "";
                String lineTxt = null;
                while ((lineTxt = br.readLine()) != null) {
                    s+=lineTxt;
                }
                System.out.println(s);
                br.close();
                byte[] b = s.getBytes();     
                System.out.println(b);
                return b;
            } else {
                System.out.println("文件不存在!");
            }
        } catch (Exception e) {
            System.out.println("文件读取错误!");
        }
        return null;
    }



    public static void main(String[] args) {
      mains();
    }


//
//    public static void main(String[] args) throws IOException {
//        String path = "d:1.pdf";
//        String url = "E:\\社科年鉴\\cssp_resource\\世界经济年鉴2015年\\pdf\\世界经济年鉴2015_l.pdf";
//        FileUtil.dowanload(url, path, 4);
//    }
}