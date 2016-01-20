package com.mogu.hui.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yihui on 15/11/30.
 */
@Controller
public class Index {
    /**
     * index跳转
     *
     * @param model
     * Inex
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
    @RequestMapping(value = "/index")
    public String test() {
        return "index";
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
