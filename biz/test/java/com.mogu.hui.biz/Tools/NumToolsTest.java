package com.mogu.hui.biz.Tools;

import org.junit.Test;

/**
 * Created by yihui on 16/1/5.
 */
public class NumToolsTest {

    @Test
    public void numFormatTest() {
        double f1 = 12.301;
        double ans = NumTools.getInstance().formatNum(f1, 2);
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


    @Test
    public void stringSubTest() {
        String num = "12.00";
        char[] ans = num.toCharArray();
        for (int index = ans.length - 1; index >=0 ; index--) {
            if ('0' == ans[index]) {
                ans[index] = '\0';
            } else if ('.' == ans[index]){
                ans[index] = '\0';
                break;
            } else {
                break;
            }
        }
        char[] tt = new char[20];

        String result = String.valueOf(ans);
        System.out.println(result + " len: " + result.length());


        result = formatNum(12.30);
        System.out.println(result);
        result = formatNum(12.001);
        System.out.println(result);
        result = formatNum(12.123);
        System.out.println(result);
        result = formatNum(12.22);
        System.out.println(result);
    }


    public String formatNum(double num) {
        long price = Math.round(num * 100);
        if (price % 100 == 0) {
            return String.valueOf(price / 100);
        } else if( price % 10 == 0) {
            return String.format("%.1f", price / 100F);
        } else {
            return String.format("%.2f", price / 100F);
        }
    }

}
