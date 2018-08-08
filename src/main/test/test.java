/**
 * Created by mxp on 2017/6/30.
 */

import com.mxp.mapper.DeptMapper;
import com.mxp.mapper.EmpMapper;
import com.mxp.model.Dept;
import com.mxp.model.Emp;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

//    @Test
//    public void ss() throws IOException {
//        ByteArrayOutputStream out1 = new ByteArrayOutputStream();
//        InputStream is = null;
//        try {
//
//            File filePath = new File("D:\\cssp_resource\\世界经济年鉴2015年\\pdf\\世界经济年鉴2015_l.pdf");
//            is = new FileInputStream(filePath);
//
//            byte[] line = new byte[102400]; // 用来保存每行读取的内容
//            int length = 0;// 读取第一行
//            while ((length = is.read(line)) > 0) {
//                out1.write(line, 0, length);
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            out1.flush();
//            out1.close();
//            is.close();
//        }
//
//    }
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
    @Test
    public void ttt2(){
        BigDecimal bigDecimal = new BigDecimal(56566656);
        //bigDecimal.setScale(4545);
        System.out.println(bigDecimal.abs());
    }

    @Test
    public void ttts(){
        String date = "2018-02-23";
        if (!"".equals(date)) {
            String d = "";
            String[] split = date.split("-");
            for (String s : split) {
                if (s.length() == 1) {
                    s = "0" + s;
                }
                d += s+"-";
            }
            d = d.substring(0,d.length()-1);
            date = d;
            System.out.println(date);
        }
    }

    @Test
    public void tttss(){
        String ss ="　　北京产权交易所于2018年 4月24日至5月2日通过动态报价方式处置收藏品一批，包括翡翠、和田玉、书画等。\n" +
                "\n" +
                "　　有意者请登录北交所网站（www.cbex.com)或北交互联网站<a href=https://otc.cbex.com/page/s/zc_prjs/index?id=274 target=_blank>（https://otc.cbex.com/page/s/zc_prjs/index?id=274）</a>了解活动及标的详情。\n" +
                "\n" +
                "　　预展时间： 2018年4月24日至4月28日\n" +
                "\n" +
                "　　(工作日9:30-17:00)\n" +
                "\n" +
                "　　预展地点：北京市西城区金融大街甲17号北京产权交易所一层展厅及东城区和平里东街民旺园31号强佑大厦东侧底商。\n" +
                "\n" +
                "<div align=right>北京产权交易所\n" +
                "2018年4月24日</div>　　   ";
        
    }



}
