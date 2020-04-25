package com.alibaba.hj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by issuser on 2020/4/1.
 */

@Controller
public class HomeController {

    @RequestMapping("/test")
    public String home(){
        return "test";
    }
}
