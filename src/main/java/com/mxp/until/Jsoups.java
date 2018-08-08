package com.mxp.until;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jsoups {
//http://www.open-open.com/jsoup/selector-syntax.htm


    private static String url = "jdbc:mysql://localhost:3306/temp?serverTimezone=UTC";
    private static String username = "root";
    private static String password = "root";
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            System.out.println("驱动加载异常");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("连接异常");
            e.printStackTrace();
        }
    }


    public static void Insert(String title,String contentHtml,String code,String wz,String cgal,String lxfs,String name) throws SQLException {
        Statement statement = connection.createStatement();    //调用 DBconnection 类的 conn() 方法连接数据库
        String sql = "INSERT INTO zwby (title,contentHtml,code,wz,cgal,lxfs,name) VALUES(?,?,?,?,?,?,?)";        //插入sql语句
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            /**
             * 调用实体StuInfo类,获取需要插入的各个字段的值
             * 注意参数占位的位置
             * 通过set方法设置参数的位置
             * 通过get方法取参数的值
             */
            ps.setString(1, title);
            ps.setString(2, contentHtml);
            ps.setString(3, code);
            ps.setString(4, wz);
            ps.setString(5, cgal);
            ps.setString(6, lxfs);
            ps.setString(7, name);
            ps.executeUpdate();         //执行sql语句

            System.out.println("插入成功(*￣︶￣)");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            statement.close();
        }
    }


    public List<Map<String, String>> selectData(String sql, String... value) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<Map<String, String>> mapList = new ArrayList<>();
        while (resultSet.next()) {
            Map<String, String> data = new HashMap<>(11);
            for (int i = 0; i < value.length; i++) {
                data.put(value[i], resultSet.getString(value[i]));
            }
            mapList.add(data);
        }
        resultSet.close();
        statement.close();
        //connection.close();
        return mapList;
    }
    
    public void main(String[] args) throws SQLException, IOException {
    }

    @Test
    public void getZd() throws SQLException {
        List<Map<String, String>> ss = selectData("select * from zw", "title", "contentHtml","name","code");
        int i = 0;
        int zj = 0;
        for (Map map : ss){
            //System.out.println("title:"+map.get("title"));
            //System.out.println("contentHtml:"+map.get("contentHtml"));
            //System.out.println("name:"+ map.get("name"));
            //System.out.println("code:"+ map.get("code"));
            String content = (String) map.get("contentHtml");
            Document doc = Jsoup.parse(content);

            Elements hywzs = doc.select("table > tbody > tr > td > b");

//            for(Element hywz : hywzs){
//               // System.out.println(hywz.text());
//                if(hywz.text().equals("会员网站：")){
//                    Elements wzs = doc.select("table > tbody > tr > td >  a");
//                    for (Element wz : wzs){
//                        //获取href
//                        String Linkhref = wz.attr("href");
//                        if (!Linkhref.equals("#")){
//                            System.out.println(map.get("title")+ "会员网站===>" + Linkhref);
//                        }else {
//                            System.out.println(map.get("title")+ "会员网站===>" + "");
//                        }
//                        i++;
//                    }
//                    zj++;
//                }
//            }


            Elements contHtml = doc.getElementsByClass("content");

            for (Element con : contHtml){
                String kk = "";

                String h3 = con.getElementsByTag("h3").text().trim();

                System.out.println(h3);
                System.out.println(h3.equals("公司简介"));
                if (h3.equals("公司简介")){
                    System.out.println("666");
                    System.out.println(con.text());
                }


//                List<TextNode> nodes = con.textNodes();
//                List<DataNode> datanodes = con.dataNodes();
//                 for (TextNode node : nodes) {
//                     String stringNode = node.toString().trim();
//                     kk+=stringNode+"----";
//                     //String[] date = stringNode.split(" 联系地址：");
//                     //String cons = stringNode.substring(stringNode.lastIndexOf("邮编"));
//                 }

//                System.out.println(con.ownText());

                 return;

               // System.out.println(kk);

               // String lxfs = kk.substring(kk.lastIndexOf("------------------------"));

                //String cons = kk.substring(0,kk.lastIndexOf("联系地址："));

                //Insert((String) map.get("title"),kk, (String) map.get("code"),"","",cons,(String) map.get("name"));
                //System.out.println(lxfs);
            }
        }
        System.out.println("总计数量====>"+zj);
        System.out.println("处理数量====>"+i);
    }

    public static void getht() throws IOException, SQLException {

         int x = 1;
        Document doc = Jsoup.connect("http://www.cbex.com.cn/article/hyzq/hyml/fwhy/").get();
        Elements body = doc.select(".text > table > tbody > tr > td > table > tbody > tr > td > table > tbody > tr > td > table > tbody > tr > td > a");
        for (Element s : body) {
            String relHref = s.attr("href");
            //  System.out.println(relHref);
            Document doc1 = Jsoup.connect("http://www.cbex.com.cn/article/hyzq/hyml/fwhy/" + relHref).get();
            Elements body1 = doc1.select(".aarticle");
            for (Element s1 : body1) {
               String q1 = s1.text().substring(0,s1.text().indexOf(" "));
               String q2 = q1.substring(q1.lastIndexOf("：")+1);
               // System.out.println(q2);
             //  System.out.println(x++);

                //Insert(s1.toString(),s.text(),"中介服务会员名录/法律服务类",q2);
                if (Integer.valueOf(q2.replaceAll("Z","")) >= 5001 && Integer.valueOf(q2.replaceAll("Z","")) <= 5002){
                    //Insert(s1.toString(),s.text(),"中介服务会员名录/综合服务类",q2);
                }
               //Insert(s1.toString(),s.text(),"经纪会员名录",q2);
            }

        }
        
    }


    public static void gethta() throws IOException, SQLException {

        int x = 1;

        Document doc = Jsoup.connect("http://www.cbex.com.cn/article//hyzq/jgml/201401/20140100049181.shtml").get();

        Elements body = doc.select(".aarticle > tbody > tr > td > table > tbody > tr > td > p > a");

        
        for (Element s : body) {
            
            String relHref = s.attr("href");

            //Insert(relHref.toString(),s.text(),"","");
            System.out.println(relHref +"======>"+s.text());
            x++;
        }
        System.out.println(x);


    }
}
