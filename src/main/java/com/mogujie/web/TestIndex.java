package com.mogujie.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yihui on 15/11/30.
 */
@Controller
@RequestMapping("/test/")
public class TestIndex {

    @RequestMapping(value = "/id/{itemId}", method = RequestMethod.GET)
    @ResponseBody
    public String queryItemById(@PathVariable String itemId){
        return "The item Id is: " + itemId;
    }
}
