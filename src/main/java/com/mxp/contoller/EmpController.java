package com.mxp.contoller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mxp.model.Emp;
import com.mxp.model.Msg;
import com.mxp.services.EmpServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import java.util.List;
import java.util.Map;

/**
 * Created by mxp on 2017/7/4.
 */
@Controller
public class EmpController {

    @Autowired
    private EmpServices empServices;


    @RequestMapping("/emp")
    @ResponseBody
    public Msg getJson(@RequestParam(name = "pageNumber",defaultValue = "1")Integer pageNumber, Map<String,Object> map){
        //使用分页先调用PageHelper方法
        PageHelper.startPage(pageNumber,10);
        List<Emp> emp = empServices.getAllEmp();
        //将数据加入到pageInfo中,连续显示的页数
        PageInfo pageInfo = new PageInfo(emp,5);

        HttpServletResponse response;
        PageContext pageContext = null;


        return Msg.success().add("pageInfo",pageInfo);

    }


    @RequestMapping("/index")
    public String index(){
        return "list1";
    }

  //  @RequestMapping("/emp")
    public String getALL(@RequestParam(name = "pageNumber",defaultValue = "1")Integer pageNumber, Map<String,Object> map){
        //使用分页先调用PageHelper方法
        PageHelper.startPage(pageNumber,10);
       List<Emp> emp = empServices.getAllEmp();
       //将数据加入到pageInfo中,连续显示的页数
        PageInfo pageInfo = new PageInfo(emp,5);
        map.put("pageInfo",pageInfo);
       return "list";
    }

    @RequestMapping("/testSession/{index}")
    public void testsession(HttpSession session, @PathVariable("index") String index){
        session.setAttribute("user",index);
        Object sy = session.getAttribute("user");
        System.out.println(sy);
    }

    @RequestMapping("/getSession")
    public void getsession(HttpSession session){
        Object s = session.getAttribute("user");
        System.out.println("sssssss=>"+s);
    }
}
