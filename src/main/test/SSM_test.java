//import com.github.pagehelper.PageInfo;
//import com.mxp.model.Emp;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.List;
//
///**
// * Created by mxp on 2017/7/4.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(locations = {"classpath:spring_config.xml","file:src/main/resources/spring_mvc.xml"})
//public class SSM_test {
//
//    //创建WebApplicationContext
//    @Autowired
//    private WebApplicationContext context;
//
//    //创建虚拟的MVC
//    private MockMvc mockmvc;
//
//
//    //初始化MVC方法
//    @Before
//    public void initMvc(){
//        mockmvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
//
//    @Test
//    public void testPage() throws Exception {
//        MvcResult  mvcResult = mockmvc.perform(MockMvcRequestBuilders.get("/emp").param("pageNumber","1")).andReturn();
//        MockHttpServletRequest mockHttpServletRequest = mvcResult.getRequest();
//        PageInfo pg = (PageInfo)mockHttpServletRequest.getAttribute("pageInfo");
//        System.out.println("当前页码"+pg.getPageNum());
//        System.out.println("总页数"+pg.getPages());
//        System.out.println("总记录数"+pg.getTotal());
//        int[]  num = pg.getNavigatepageNums();
//        for (int i:num) {
//            System.out.printf(" "+i);
//        }
//        List<Emp> em = pg.getList();
//        for (Emp e:em) {
//            System.out.println(e.getdId()+e.getEmpName()+e.getDept().getDeptName());
//        }
//    }
//}
