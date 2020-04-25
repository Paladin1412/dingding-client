package com.alibaba.hj.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


/**
 * Created by issuser on 2020/04/20.
 */

@RestController
@RequestMapping("/rpc")
public class EventController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody String param) {
        logger.warn("add event execute [{}]", param);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        jsonObject.put("errcode", "0");
        jsonObject.put("errmsg", "成功");
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody String param) {
        logger.warn("update event execute [{}]", param);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        jsonObject.put("errcode", "0");
        jsonObject.put("errmsg", "成功");
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestBody String param) {
        logger.warn("delete event execute [{}]", param);
        return "{\"errcode\":\"0\",\"errmsg\":\"成功\"}";
    }
}
