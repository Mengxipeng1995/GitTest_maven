package com.mxp.contoller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.Callable;

@Controller
public class TestController {

    @RequestMapping("/testCallable")
    public Callable<String> testCallable() {
        System.out.println("主线程开始");
        Callable<String> result = () ->{
            System.out.println("子线程开始");
            Thread.sleep(5000);
            System.out.println("子线程结束");
            return "success";
        };
        System.out.println("主线程结束");
        return result;
    }
}
