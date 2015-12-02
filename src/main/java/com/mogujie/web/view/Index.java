package com.mogujie.web.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by yihui on 15/12/1.
 */
@Controller
@RequestMapping("/index")
public class Index {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView queryItemById(){
        return new ModelAndView("vm/index", "test", "一灰");
    }
}
