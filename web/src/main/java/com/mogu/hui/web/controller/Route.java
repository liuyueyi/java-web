package com.mogu.hui.web.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yihui on 15/11/30.
 */
public class Route {
    /**
     * index跳转
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        return "index";
    }

    /**
     * 测试页面
     *
     * @return
     */
    @RequestMapping(value = "/test")
    public String test() {
        return "test";
    }


    /**
     * 监控检测
     *
     * @return
     */
    @RequestMapping(value = "/status")
    public String status() {
        return "status";
    }

}
