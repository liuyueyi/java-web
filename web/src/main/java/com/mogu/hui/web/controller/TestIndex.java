package com.mogu.hui.web.controller;

import com.alibaba.fastjson.JSON;
import com.mogu.hui.web.adt.ResultCode;
import com.mogu.hui.web.adt.RetData;
import org.apache.commons.lang.StringUtils;
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
        if (StringUtils.isBlank(itemId)) {
            return buildResult(ResultCode.PARAMETER_ERROR, null, "id", "empty!");
        }

        return buildResult(ResultCode.SUCCESS, result);
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

    /**
     * 构建返回的结果
     * @param code  返回的状态
     * @param result 正文
     * @param msg 提示信息
     * @return
     */
    private String buildResult(ResultCode code, Object result, String ...msg) {
        RetData retData = new RetData();
        code.mixin(retData, msg);
        retData.setResult(result);

        // 利用fastJson进行序列化
        String ans = JSON.toJSONString(retData);
        return ans;
    }
}
