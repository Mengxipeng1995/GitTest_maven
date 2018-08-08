import org.apache.http.ParseException;
import org.apache.http.util.TextUtils;
import org.junit.Test;

import java.io.*;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ApacheRzTj {

    private static List list = new ArrayList();
    private static Map<String, Integer> temp = new TreeMap();
    private static int yh = 0;
    private static int didd = 0;
    private static List docidList = new ArrayList();

    public ApacheRzTj() throws java.text.ParseException {
    }


    public static void main(String[] args) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null; //用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
        try {
            String str = "";
            String str1 = "";
            fis = new FileInputStream("D:\\testNio\\access_log_20180731");// FileInputStream
            // 从文件系统中的某个文件中获取字节
            isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
            br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
            String txt = "";

            String fileName = "D:\\testNio\\fwl\\2018-07-31.log";
            try {
              BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
                while ((str = br.readLine()) != null) {



                    if (str != "" && str != null) {
                        //Status(str);
                        if(Status(str) != null || Status(str) != ""){
                            out.write(Status(str));
                        }
                    }

                }

//                List<Map.Entry<String, Integer>> map1 = sortHashMapByValues(temp);
//
//                for (Map.Entry<String, Integer> mapping : map1) {
//                    yh++;
//                    System.out.println(mapping.getKey() + ":" + mapping.getValue());
//                    out.write( mapping.getKey() + "===>" + mapping.getValue() + "\r\n");
//                    //txt += mapping.getKey() + "===>" + mapping.getValue() + "\r\n";
//                }
//                System.out.println("用户数：======》"+yh);
                //out.newLine();  //注意\n不一定在各种计算机上都能产生换行的效果
                //out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            System.out.println("总访问量:======>" + yms);



//            for (Map.Entry<String, Integer> entry : temp.entrySet()) {
//
//                System.out.println(entry.getKey() + "===>" + entry.getValue());
//            }
            //txt+="总访问量:======>"+yms;
            writeMethod3(txt);
            // 当读取的一行不为空时,把读到的str的值赋给str1
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");
        } catch (IOException e) {
            System.out.println("读取文件失败");
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        catch (java.text.ParseException e) {
//            e.printStackTrace();
//        }
        catch (java.text.ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
                // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String GetUrl(String str) {
        //String str = "202.106.156.246 - - [17/Jul/2018:05:37:01 +0800] \"GET /gzfw/spk/hbbvideo/ztbd/hjj/200909/t20090924_161493.html HTTP/1.0\" 200 6761\n";
        String s1 = str.substring(str.indexOf("\"GET") + 4);
        String s2 = s1.substring(0, s1.lastIndexOf(" HTTP/1.0"));
        return s2;
    }


    public static List<Map.Entry<String, Integer>> sortHashMapByValues(Map<String, Integer> map) {

//        Map<String, Integer> map = new TreeMap<String, Integer>();
//        map.put("a", 2);
//        map.put("c", 3);
//        map.put("d", 6);
//        map.put("b", 7);

        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            //升序排序
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() > o2.getValue()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });


        return list;
    }

    private static int yms = 0;
    //17/Jul/2018:00:23:09
    private static String date1 = "2018-07-23 20:30:09";
    private static String date2;

    /**
     * 写出文件
     *
     * @param str
     */
    public static void writeMethod3(String str) {

    }


    static {
        try {
            date2 = s(date1);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取访问状态码
     *
     * @param str
     * @return
     */
    public static String Status(String str) throws java.text.ParseException {
        //String str = "106.8.215.7 - - [17/Jul/2018:08:37:32 +0800] \"GET /images/201603235.png HTTP/1.0\" 200 1259";
        String s1 = "";
        String s2 = "";
        String t = "";
        try {
            s1 = str.substring(str.lastIndexOf("HTTP/1.0\"") + 9);
            s2 = s1.substring(0, s1.lastIndexOf(" "));
        } catch (Exception e) {
            System.out.println(str);
        }
        String date3 = GetTime(str);
        System.out.println(GetIp(str) + "--------->" + date3);

        //if (compare_date(date3, date2) == -1 && compare_date(date3, date1) == 1) {

            if (str.indexOf("index") == -1) {
                if (str.indexOf(".htm") != -1 || str.indexOf(".shtml") != -1) {
                    if ((s2.trim()).equals("200")) {
//                        yms++;
//                        if (list.indexOf(GetIp(str)) != -1) {
//                            Integer s = temp.get(GetIp(str));
//                            s++;
//                            temp.put(GetIp(str), s);
                        String uuid = UUID.randomUUID().toString();
                           // 2018-07-17T00:00:06.554Z %{host} ::3087ae7352cd4f6ebbfddbb962be6af8:::null:::2018-07-17 08:00:06:::http://www.mep.gov.cn/home/rdq/hjyxpj/jsxmhjyxpj/nscxmgs/201605/t20160522_339639.shtml:::2:::339639:::关于《伊犁新天煤化工有限公司20亿立方米/年煤制天然气项目环境影响评价公众参与说明》的公示
                            String UTCdate = GetTimeT(str);

//                            String docid = getRandom();

                          
                           //docidList.add(docid);
//                            while (docid.equals("ll")){
//
//                                docid = getRandom();
//
//                            }

                            if(list.indexOf(GetUrl(str)) != -1){

                                Integer did = temp.get(GetUrl(str));
                                t = UTCdate + " " + "%{host}" + " ::" + GetIp(str) + ":::null:::" + GetTime(str) + ":::" + "http://www.mep.gov.cn" + GetUrl(str).trim() + ":::" + 2 + ":::" + did + ":::" + "测试数据" + "\n";

                                return t;
                            }else {

                                t = UTCdate + " " + "%{host}" + " ::" + GetIp(str) + ":::null:::" + GetTime(str) + ":::" + "http://www.mep.gov.cn" + GetUrl(str).trim() + ":::" + 2 + ":::" + didd + ":::" + "测试数据" + "\n";
                                temp.put(GetUrl(str),Integer.valueOf(didd));
                                list.add(GetUrl(str));
                                didd++;
                                return t;
                            }



   //                         t = UTCdate + " " + "%{host}" + " ::" + GetIp(str) + ":::null:::" + GetTime(str) + ":::" + "http://www.mep.gov.cn" + GetUrl(str).trim() + ":::" + 2 + ":::" + uuid + ":::" + "测试数据" + "\n";
//                        } else {
//                            int i = 1;
//                            list.add(GetIp(str));
//                            temp.put(GetIp(str), i);
//                        }
                    }
                }

            }
     //   }
        return "";
    }

    public static String getRandom() {
//        String result = "";
//        // 下面的6改成8就是8位随机数字
//        while (result.length() < 6) {
//            String str = String.valueOf((int) (Math.random() * 10));
//            if (result.indexOf(str) == -1) {
//                result += str;
//            }
//        }
//
//        if (docidList.indexOf(result) == -1){
//            docidList.add(result);
//            return result;
//        }

       return "ll";
    }

    public static String s(String str) throws java.text.ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // String str = "2018-07-27 16:50:32";
        Date dt = sdf.parse(str);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.MINUTE, 5);//5分钟
        //rightNow.add(Calendar.HOUR, 1);//1小时
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        //System.out.println(reStr);
        return reStr;
    }

    /**
     * 2018-07-17 08:37:32
     * 获取日期
     *
     * @param str
     * @return
     * @throws java.text.ParseException
     */
    public static String GetTimeT(String str) throws java.text.ParseException {
        String s1 = str.substring(str.indexOf("[") + 1);

        String s2 = s1.substring(0, s1.indexOf("]"));

        SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss z", Locale.ENGLISH);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        String date = df.format(format.parse(s2));


        Calendar c1 = Calendar.getInstance();
        c1.setTime(df.parse(date));


        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String sss = df1.format(c1.getTime());
        //Date  sj = df1.parse(String.valueOf(date));

        //System.out.println(sss);
        return sss;

    }

    /**
     * 2018-07-17 08:37:32
     * 获取日期
     *
     * @param str
     * @return
     * @throws java.text.ParseException
     */
    public static String GetTime(String str) throws java.text.ParseException {
        //String str = "106.8.215.7 - - [17/Jul/2018:08:37:32 +0800] \"GET /images/201603235.png HTTP/1.0\" 200 1259";
        String s1 = str.substring(str.indexOf("[") + 1);

        String s2 = s1.substring(0, s1.indexOf("]"));

        SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss z", Locale.ENGLISH);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String date = df.format(format.parse(s2)).toString();

        //System.out.println(date);

        return date;
    }


//
//    @Test
//    public  void tt() throws java.text.ParseException {
//        String str = "106.8.215.7 - - [17/Jul/2018:08:37:32 +0800] \"GET /images/201603235.png HTTP/1.0\" 200 1259";
//        String s = GetTimeT(str);
//        System.out.println(s);
//    }

    /**
     * 获取ip
     *
     * @param str
     * @return
     */
    public static String GetIp(String str) {
        //String str = "106.8.215.7 - - [17/Jul/2018:08:37:32 +0800] \"GET /images/201603235.png HTTP/1.0\" 200 1259";

        String s1 = str.substring(0, str.indexOf("- -"));


        // System.out.println(s1.trim());

        return s1.trim();
    }

    /**
     * 比较时间
     *
     * @param DATE1
     * @param DATE2
     * @return
     */
    public static int compare_date(String DATE1, String DATE2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                // System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                // System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }


    @Test
    public void testHbF() {
        String[] s = new String[8];
        s[0] = "E:\\环保部\\文档\\pageview\\5\\access_log_20180730";
        s[1] = "E:\\环保部\\文档\\pageview\\6\\access_log_20180730";
        s[2] = "E:\\环保部\\文档\\pageview\\7\\access_log_20180730";
        s[3] = "E:\\环保部\\文档\\pageview\\8\\access_log_20180730";

        s[4] = "E:\\环保部\\文档\\pageview\\5\\access_log_mee_20180730";
        s[5] = "E:\\环保部\\文档\\pageview\\6\\access_log_mee_20180730";
        s[6] = "E:\\环保部\\文档\\pageview\\7\\access_log_mee_20180730";
        s[7] = "E:\\环保部\\文档\\pageview\\8\\access_log_mee_20180730";
        mergeFiles(s, "D:\\testNio\\access_log_20180730");
    }

    /**
     * 合并文件
     *
     * @param fpaths
     * @param resultPath
     * @return
     */
    public boolean mergeFiles(String[] fpaths, String resultPath) {
        if (fpaths == null || fpaths.length < 1 || TextUtils.isEmpty(resultPath)) {
            return false;
        }
        if (fpaths.length == 1) {
            return new File(fpaths[0]).renameTo(new File(resultPath));
        }

        File[] files = new File[fpaths.length];
        for (int i = 0; i < fpaths.length; i++) {
            files[i] = new File(fpaths[i]);
            if (TextUtils.isEmpty(fpaths[i]) || !files[i].exists() || !files[i].isFile()) {
                return false;
            }
        }

        File resultFile = new File(resultPath);

        try {
            FileChannel resultFileChannel = new FileOutputStream(resultFile, true).getChannel();
            for (int i = 0; i < fpaths.length; i++) {
                FileChannel blk = new FileInputStream(files[i]).getChannel();
                resultFileChannel.transferFrom(blk, resultFileChannel.size(), blk.size());
                blk.close();
            }
            resultFileChannel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
