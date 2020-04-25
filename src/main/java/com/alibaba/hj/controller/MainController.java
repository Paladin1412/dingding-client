package com.alibaba.hj.controller;

import com.alibaba.hj.util.MainClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by issuser on 2020/3/26.
 */

@RestController
public class MainController {
    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "Hello World!";
    }

    @RequestMapping("/getUserInfo")
    public String showUser(String code) {
        logger.warn("/getUserInfo?auth_code=" + code);
        String info = MainClient.getUserInfo(code);
        logger.warn("info=" + info);
        return info;
    }
}
