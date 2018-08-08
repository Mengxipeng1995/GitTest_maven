import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: LiangYong
 * Date: 2018/3/19
 * Time: 22:42
 */
public class ConnectionJDBC {

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

   
    public void test() throws SQLException, IOException {
        Map<String, String> map = new HashMap<>();
        List<Map<String, String>> ss = selectData("select * from yunnan", "省", "市","县", "乡","村","12位代码");


        for (Map<String, String> sss : ss) {
            System.out.println(ss);
//            UUID UID = UUID.randomUUID();
//            map.put("code",sss.get("code").trim());
//            map.put("name",sss.get("name").trim());
//            map.put("_id", UID.toString().replaceAll("-", ""));
//            JSONObject jsonObject = JSONObject.fromObject(map);
//            FileWriter fw = new FileWriter(new File("D:\\t.json"), true);
//            fw.write(jsonObject.toString() + "\r\n");
//            fw.close();
//            System.out.println();
        }


//        for (Map<String,String> sss : ss){
//            if (sss.get("小类") != null){
//
//                String sy = sss.get("小类");
//
//                String cl = sy.substring(0,sy.lastIndexOf("."));
//
//                String str = "";
//
//                if (cl.length() <= 3){
//                    str = "0"+cl;
//                }else {
//                    str = cl;
//                }
//                UUID SS =UUID.randomUUID();
//                map.put("code",str);
//                map.put("name",sss.get("名称").trim());
//                map.put("_id",SS.toString().replaceAll("-","").substring(0,16));
//                JSONObject jsonObject = JSONObject.fromObject(map);
//                FileWriter fw = new FileWriter(new File("D:\\t.json"), true);
//                fw.write(jsonObject.toString()+"\r\n");
//                fw.close();
//                System.out.println();
//            }
//        }

    }


    public void ttt() {
        //AXaIOjgMxZ6QxF9O

        System.out.println();
    }

}
