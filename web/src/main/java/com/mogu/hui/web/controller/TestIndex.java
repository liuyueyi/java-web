package com.mogu.hui.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yihui on 15/11/30.
 */
@Controller
@RequestMapping("/test")
public class TestIndex {

    /**
     * 返回json字符串
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/id/{itemId}", method = RequestMethod.GET, produces="text/html;charset=UTF-8")
    @ResponseBody
    public String queryItemById(@PathVariable String itemId){
        String result = "The item Id is: " + itemId;
        return result;
    }


    /**
     * 返回模板 view
     * @param request
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        view.addObject("tt", "hello Kitty!");
        return view;
    }
}
