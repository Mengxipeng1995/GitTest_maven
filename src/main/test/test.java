/**
 * Created by mxp on 2017/6/30.
 */

import com.mxp.mapper.DeptMapper;
import com.mxp.mapper.EmpMapper;
import com.mxp.model.Dept;
import com.mxp.model.Emp;
import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.io.*;
import java.sql.SQLException;
import java.util.UUID;

public class test {


    /*@Autowired
    private DeptMapper deptMapper;*/

    private static ApplicationContext ctx = null;

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
    public  void testinsertDept(){
        DeptMapper deptMapper = ctx.getBean("deptMapper",DeptMapper.class);
        System.out.println(deptMapper);
        deptMapper.insertSelective(new  Dept(null,"开发部"));
        deptMapper.insertSelective(new  Dept(null,"测试部"));
    }

    @Test
    public  void testinsertEmp(){
        SqlSessionTemplate mapper = ctx.getBean("BathSqlSession",SqlSessionTemplate.class);
        EmpMapper emp = mapper.getMapper(EmpMapper.class);
        for (int i=0 ;i<1000;i++) {
            String uuid = UUID.randomUUID().toString().substring(0, 4) + i;
            emp.insertSelective(new Emp(null, uuid, "M", uuid + "@mxp.com", 3));
        }

    }

/*    @Test
    public void testMBG() throws Exception{
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        File configFile = new File("D:/idea_work/GitTest_maven/src/main/resources/MBG.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,callback,warnings);
        myBatisGenerator.generate(null);
    }*/

    @Test
    public void ss() throws IOException {
        ByteArrayOutputStream out1 = new ByteArrayOutputStream();
        InputStream is = null;
        try {

            File filePath = new File("D:\\cssp_resource\\世界经济年鉴2015年\\pdf\\世界经济年鉴2015_l.pdf");
            is = new FileInputStream(filePath);

            byte[] line = new byte[102400]; // 用来保存每行读取的内容
            int length = 0;// 读取第一行
            while ((length = is.read(line)) > 0) {
                out1.write(line, 0, length);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out1.flush();
            out1.close();
            is.close();
        }

    }
    @Test
    public void ttt(){



        String author = ";汪信砚;;;;［英］埃里克·霍布斯鲍姆;吕增奎;;林绪武;;;;";

        String[] auList = author.split(";");
        if (author != null && !author.equals("")) {
            for (String a : auList) {
                System.out.print(a.trim());
            }
        }
    }

}
