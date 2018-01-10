import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTest {
    public static void main(String[] args) {
        try {
            File file = new File("D:\\美景.jpg");
            HttpURLConnection connection = (HttpURLConnection) new URL("http://p4.so.qhmsg.com/bdr/326__/t01f1f761ee09efc574.jpg").openConnection();
            connection.setRequestMethod("GET");
            long sum = 0;
            if (file.exists()){
                sum = file.length();
                connection.setRequestProperty("Range","bytes=" + file.length() + "-");
            }

            int code = connection.getResponseCode();
            System.out.println("code = " + code);
            if (code == 200 || code == 206) {
                int contentLength = connection.getContentLength();
                System.out.println("contentLength = " + contentLength);
                InputStream is = connection.getInputStream();
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[102400];
                long startTime = System.currentTimeMillis();
                int length;
                while ((length = is.read(buffer)) != -1) {
                    fos.write(buffer);
                    sum = sum + length;
                    float parent = sum * 100.0f / contentLength;
                    System.out.print("\r[");
                    int p = (int)parent/2;
                    for (int i = 0; i < 50; i++) {
                        if (i < p){
                            System.out.print('=');
                        }else if (i == p){
                            System.out.print('>');
                        }else {
                            System.out.print(' ');
                        }
                    }
                    System.out.print(']');
                    System.out.printf("\t%.2f%%",parent);
                    long speed = sum * 1000/(System.currentTimeMillis() - startTime);
                    if (speed > (1 << 20)){
                        System.out.printf("\t%d MB/s", speed >> 20);
                    }else if (speed > (1 << 10)){
                        System.out.printf("\t%d KB/s", speed >> 10);
                    }else {
                        System.out.printf("\t%d B/s", speed);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}