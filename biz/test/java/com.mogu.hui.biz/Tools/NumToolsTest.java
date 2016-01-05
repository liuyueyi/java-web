package com.mogu.hui.biz.Tools;

import org.junit.Test;

/**
 * Created by yihui on 16/1/5.
 */
public class NumToolsTest {

    @Test
    public void numFormatTest() {
        float f1 = 12.301f;
        float ans = NumTools.getInstance().formatNum(f1, 2);
        System.out.println(ans);

        f1 = 12.0f;
        ans = NumTools.getInstance().formatNum(f1, 2);
        System.out.println(ans);

        f1 = 12;
        ans = NumTools.getInstance().formatNum(f1, 2);
        System.out.println(ans);

        f1 = 12.455f;
        ans = NumTools.getInstance().formatNum(f1, 2);
        System.out.println(ans);
    }

}
