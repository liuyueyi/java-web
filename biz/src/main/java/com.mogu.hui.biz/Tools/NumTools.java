package com.mogu.hui.biz.Tools;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 本类实现了一个线程安全的单例模式
 * 背景: db中存的price一般是以分为单位的long型，因此取出时需要 / 100 转换为可见的单位
 *  这种情况下，希望  1230  转为  ￥12.3 而不是 ￥12.30
 * <p/>
 * Created by yihui on 16/1/5.
 */
public class NumTools {
    private static class SingleHolder {
        private final static NumTools instance = new NumTools();
    }

    private NumTools() {

    }

    public static NumTools getInstance() {
        return SingleHolder.instance;
    }

    /**
     * 保留浮点数f小数点后decimal位小数
     * TODO 若保留2位小数，若f为12.001, 则返回的结果为12.0, 而不是期望的12
     * @param f
     * @param decimal
     * @return
     */
    public double formatNum(double f, int decimal) {
        return numFormat2(f, decimal);
    }

    /**
     * 保留量为小数的方法，
     * 缺陷是12.000  --->  12.00 而不能变成12
     * 变成了string
     * @param f
     * @return
     */
    private String numFormat1(double f) {
        return String.format("%.2f", f);
    }

    /**
     * 保留2位小数
     * @param f
     * @return
     */
    private double numFormat2(double f, int decimal) {
        BigDecimal b = new BigDecimal(f);
        return b.setScale(decimal, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private String numFormat3(double f) {
        DecimalFormat format = new DecimalFormat("#.##");
        return format.format(f);
    }

    /**
     * 保留2位小数，且后缀的0会省略掉
     * @param f
     * @return
     */
    private double numFormat4(double f, int decimal) {
        // 与numFormat2方法效果一致
        double base = (double) Math.pow(10, decimal);
        return Math.round(f * base) / base;
    }


    /**
     * 蛋疼的方法了，手工判断是否最后为0
     * 下面演示的是保留2位小数的情况
     * @param num
     * @return
     */
    public String numFromat5(double num) {
        long price = Math.round(num * 100);
        if (price % 100 == 0) {
            return String.valueOf(price / 100);
        } else if( price % 10 == 0) {
            return String.format("%.1f", price / 100F);
        } else {
            return String.format("%.2f", price / 100F);
        }
    }


    /**
     * 思路是转为字符串，将字符串最后的0截取掉
     * TODO char数组转String时，当char数组后面的字符中存在'\0'时，也是会转入String中的，需要避免这种
     * @param num
     * @return
     */
    public String numFromat6(double num) {
        String temp = String.format("%.2f", num);
        char[] ans = temp.toCharArray();
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
        return String.valueOf(ans);
    }
}
