/**
 * Created by mxp on 2017/6/30.
 */

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class test {



   /* private static ApplicationContext ctx = null;

    static{
        ctx = new ClassPathXmlApplicationContext("classpath:spring_config.xml");
    }


    @Test
    public  void testdata() {
        DataSource data = (DataSource) ctx.getBean("dataSource");
        try {
            System.out.println(data.getConnection());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public  void testinsert(){
        userServices = (UserServices)ctx.getBean("userServicesImpl");
        User user = new User(-1,"aaa","bbb","ccc");
        userServices.insertUser(user);
    }*/

    @Test
    public void testMBG() throws Exception{
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        File configFile = new File("D:/idea_work/GitTest_maven/src/main/resources/MBG.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,callback,warnings);
        myBatisGenerator.generate(null);
    }
}
