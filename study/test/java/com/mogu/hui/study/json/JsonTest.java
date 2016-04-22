package com.mogu.hui.study.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by yihui on 16/4/22.
 */
public class JsonTest {
    private static Logger logger = LoggerFactory.getLogger(JsonTest.class);


    @Test
    public void innerClassTest() {
        try {
            JsonHello<Hello> jsonHello = new JsonHello<>();
            jsonHello.setName("hello");

            Hello hello = new Hello();
            hello.setHello("hello1");
            hello.setUser(Arrays.asList("user1", "user2"));

            Hello hello2 = new Hello();
            hello2.setHello("hello2");
            hello2.setUser(Arrays.asList("world1", "world2"));

            jsonHello.setList(Arrays.asList(hello, hello2));


            String str = JSON.toJSONString(jsonHello);
            logger.info("Str: {}", str);

            Object obj = JSON.parseObject(str, JsonHello.class);
            logger.info("Obj: {}", obj);

            Object obj2 = JSON.parseObject(str, new TypeReference<JsonHello<Hello>>() {
            });
            logger.info("obj2: {}", obj2);

        } catch (Exception e) {
            logger.info("error: {}", e);
        }
    }
}
