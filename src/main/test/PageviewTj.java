import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageviewTj {

    private static List list = new ArrayList();
    private static Map<String, Integer> temp = new HashMap();

//    public static void main(String[] args) {
//        FileInputStream fis = null;
//        InputStreamReader isr = null;
//        BufferedReader br = null; //用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
//        try {
//            String str = "";
//            String str1 = "";
//            fis = new FileInputStream("E:\\环保部\\文档\\pageview\\2018-07-17.log");// FileInputStream
//            // 从文件系统中的某个文件中获取字节
//            isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
//            br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
//            while ((str = br.readLine()) != null) {
//                IsMs(str);
//            }
//            String txt = "";
//            for (Map.Entry<String, Integer> entry : temp.entrySet()) {
//                txt+=entry.getKey() + "===>" + entry.getValue() +"\r\n";
//
//                //System.out.println();
//            }
//            writeMethod3(txt);
//            // 当读取的一行不为空时,把读到的str的值赋给str1
//        } catch (FileNotFoundException e) {
//            System.out.println("找不到指定文件");
//        } catch (IOException e) {
//            System.out.println("读取文件失败");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                br.close();
//                isr.close();
//                fis.close();
//                // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    public static void writeMethod3(String str) {
//        String fileName = "D:\\pageviewTj.txt";
//        try {
//            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
//            out.newLine();  //注意\n不一定在各种计算机上都能产生换行的效果
//            out.write(str+"\n");
//            out.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

    /**
     * 切割出时间
     *
     * @param str
     * @return
     * @throws ParseException
     * @throws ParseException
     */
    //2018-07-17T00:00:06.554Z %{host} ::3087ae7352cd4f6ebbfddbb962be6af8:::null:::2018-07-17 08:00:06:::http://www.mep.gov.cn/home/rdq/hjyxpj/jsxmhjyxpj/nscxmgs/201605/t20160522_339639.shtml:::2:::339639:::关于《伊犁新天煤化工有限公司20亿立方米/年煤制天然气项目环境影响评价公众参与说明》的公示
    public static String GetTime(String str) throws ParseException, ParseException {
        //String str = "2018-07-17T00:00:06.554Z %{host} ::3087ae7352cd4f6ebbfddbb962be6af8:::null:::2018-07-17 08:00:06:::http://www.mep.gov.cn/home/rdq/hjyxpj/jsxmhjyxpj/nscxmgs/201605/t20160522_339639.shtml:::2:::339639:::关于《伊犁新天煤化工有限公司20亿立方米/年煤制天然气项目环境影响评价公众参与说明》的公示";
        String s1 = str.substring(str.indexOf(":::") + 3);
        String s2 = s1.substring(s1.indexOf(":::") + 3);
        String s3 = s2.substring(0, s2.indexOf(":::"));
        return s3;
    }

    /**
     * 切割出cookie
     *
     * @param str
     * @return
     */
    public static String GetCookie(String str) {
        //  String str = "2018-07-17T00:00:06.554Z %{host} ::3087ae7352cd4f6ebbfddbb962be6af8:::null:::2018-07-17 08:00:06:::http://www.mep.gov.cn/home/rdq/hjyxpj/jsxmhjyxpj/nscxmgs/201605/t20160522_339639.shtml:::2:::339639:::关于《伊犁新天煤化工有限公司20亿立方米/年煤制天然气项目环境影响评价公众参与说明》的公示";
        String s1 = str.substring(str.indexOf(" ::") + 3);
        String s2 = s1.substring(0, s1.indexOf(":::"));
        return s2;
    }


    public static void IsMs(String str) throws ParseException {
        String s1 = str.substring(str.indexOf("http://"));
        if (s1.indexOf("www.mep.gov.cn") != -1) {
            //将读取到的cookie放入list中，若存在则开始计数
            if (list.indexOf(GetCookie(str)) != -1) {
                //记录时间点
                Integer s = (Integer) temp.get(GetCookie(str));
                s++;
                temp.put(GetCookie(str), s);
            } else {
                int i = 1;
                //System.out.println(GetTime(str) + "===>" + GetCookie(str));
                list.add(GetCookie(str));
                temp.put(GetCookie(str), i);
            }

        }
    }

}
