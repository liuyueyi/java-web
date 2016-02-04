package com.mogu.hui.biz.tools;

import com.mogu.hui.biz.tools.conf.PropertyConfLoader;
import org.junit.Test;

import java.util.Map;

/**
 * Created by yihui on 16/2/4.
 */
public class ConfLoaderTest {
    @Test
    public void confTest() {
        int i = 0;
        while (i ++ < 10) {
            try {
                Map map = PropertyConfLoader.getInstance().getConf();
                System.out.println(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
