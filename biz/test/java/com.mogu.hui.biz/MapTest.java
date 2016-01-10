package com.mogu.hui.biz;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by yihui on 16/1/4.
 */
public class MapTest {
    private static Logger logger = LoggerFactory.getLogger(MapTest.class);
    @Test
    public void mapTest() {
        List<Map<String,String>> result = this.getPropertyMap();
        System.out.println(result.toString());
    }

    private List<Map<String, String>> getPropertyMap() {
        String properties = "鞋跟形状:平跟;闭合方式:系带;流行元素:花朵;流行元素:纯色;流行元素:碎花;流行元素:镂空;跟高:低跟(小于3cm);鞋头:圆头;内里材质:人造革/PU;鞋底材质:牛筋底;开口深度:深口;鞋面材质:人造革/PU;皮质特征:软面皮;适合场合:日常休闲";

        try {
            String[] propertyList = properties.split(";");
            if (propertyList.length == 0) {
                return new ArrayList<>();
            }

            Map<String, Map<String, String>> tempResult = new HashMap<>(propertyList.length);
            for (String property : propertyList) {
                Map<String, String> propertyCell = new HashMap<>(2);
                String[] values = property.split(":");
                if (values.length != 2) {
                    continue;
                }
                if (tempResult.containsKey(values[0])) {
                    Map<String, String> map = tempResult.get(values[0]);
                    String value = map.get("VALUE") + "," + values[1];
                    map.put("VALUE", value);
                    tempResult.put(values[0], map);
                } else {
                    propertyCell.put("KEY", values[0]);
                    propertyCell.put("VALUE", values[1]);
                    tempResult.put(values[0], propertyCell);
                }
            }

            return new ArrayList<>(tempResult.values());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


    @Test
    public void jsonTest() {
        String text = "{\"height\":null,\"weight\":null,\"shoeSize\":null,\"suit\":1,\"colorShade\":null,\"platform\":1,\"tag\":1}";
        Gson gson = new Gson();
        Map<String, String> obj = gson.fromJson(text, Map.class);
        System.out.println(obj.toString());
    }


    @Test
    public void base64Test() throws IOException {
        String temp = "eyJwYWdlIjoyLCJ0cmFkZUl0ZW1JZCI6MjM3NDkwNDE3fQ==";
        byte[] obj = new BASE64Decoder().decodeBuffer(temp);

        String result = new String(obj, "utf-8");
        System.out.println(result);

        Map<String, Integer> map = JSON.parseObject(result, Map.class);
        System.out.println(map.toString());
    }

    @Test
    public void strTest() {
        String iid = "123sdf2_1111";
        System.out.println(iid.replace("_1111", ""));
    }

    @Test
    public void floatTest() {
        double f1 = 123.0;
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        String ans = format.format(f1);
        System.out.println(ans);

        String price = String.format("￥%.2f", f1);
        System.out.println(price);


        BigDecimal b = new BigDecimal(f1);
        String bans = b.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        System.out.println(bans);
    }

    @Test
    public void basicTest() {
        String imgUrl = "/path/01.jpg,safd/02.jpg,,,";
        String temp = StringUtils.strip(imgUrl, ",");
        logger.info("Teh temp is {}", temp);
        System.out.println("The temp is : " + temp);

        List<String> str = new ArrayList<>(10);
        str.add("haer");
        str.add("asfddas");
        System.out.println(str.size());
    }
}
