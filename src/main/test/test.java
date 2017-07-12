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
}
