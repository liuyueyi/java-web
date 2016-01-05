package com.mogu.hui.biz.Tools;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 本类实现了一个线程安全的单例模式
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
    public float formatNum(float f, int decimal) {
        return numFormat2(f, decimal);
    }

    /**
     * 保留量为小数的方法，
     * 缺陷是12.000  --->  12.00 而不能变成12
     * 变成了string
     * @param f
     * @return
     */
    private String numFormat1(float f) {
        return String.format("%.2f", f);
    }

    /**
     * 保留2位小数
     * @param f
     * @return
     */
    private float numFormat2(float f, int decimal) {
        BigDecimal b = new BigDecimal(f);
        return b.setScale(decimal, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    private String numFormat3(float f) {
        DecimalFormat format = new DecimalFormat("#.##");
        return format.format(f);
    }

    /**
     * 保留2位小数，且后缀的0会省略掉
     * @param f
     * @return
     */
    private float numFormat4(float f, int decimal) {
        // 与numFormat2方法效果一致
        float base = (float) Math.pow(10, decimal);
        return Math.round(f * base) / base;
    }
}
