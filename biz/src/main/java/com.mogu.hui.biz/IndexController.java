package com.mogu.hui.biz;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yihui on 16/1/4.
 */
@Controller
public class IndexController {
    @RequestMapping(value = "/index", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String index() {
        return "hello boy!";
    }
}
