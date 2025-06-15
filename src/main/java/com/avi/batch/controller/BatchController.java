package com.avi.batch.controller;


import com.avi.batch.aop.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/batch")
public class BatchController {

    @Autowired
    ApplicationContext applicationContext;



    @GetMapping("/entity")
    @RateLimiter(concurrentRequest = 10, timeInterval = 2000)
    public String entity(){

       // departmentService.updateDepartment();
        return "Hello  Batch Entity";
    }


    @GetMapping("/load")
    public String load(){

        return "Hello  Batch Load";
    }

    @GetMapping("/test")
    public void getEnvironment(){
    System.out.println("Hi");

    }
}
