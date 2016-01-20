package com.mogu.hui.web.controller;

import com.mogu.hui.web.modal.Calculate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yihui on 16/1/19.
 */
@Controller
public class CalculateController {

    @Autowired
    private Calculate calculate;

    @RequestMapping(value = "/add/{num}")
    public String add(@PathVariable int num) {
        if (num <= 0) {
            System.out.println("The parameter is error!");
        }

        int result = calculate.tt(num);
        System.out.println(result);
        return "index";
    }

    @RequestMapping(value = "/sub/{num}")
    public String sub(@PathVariable int num, HttpServletRequest request) {
        if (num <= 0) {
            System.out.println("The parameter is error!");
        }

        int result = calculate.tt(num);
        System.out.println(result);
        request.setAttribute("tt", result);
        return "index";
    }
}
