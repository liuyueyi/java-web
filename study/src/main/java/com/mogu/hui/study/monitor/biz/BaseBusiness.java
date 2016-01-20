package com.mogu.hui.study.monitor.biz;

import com.mogu.hui.study.monitor.anno.RT;

/**
 * Created by yihui on 16/1/19.
 */
public class BaseBusiness {
    public BaseBusiness() {
    }

    /**
     * 用于测试RT注解的方法，一个很经典的
     *
     * @param n
     * @return
     */
    @RT
    public int add(Integer n) {
        if (n <= 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return add(n - 1) + add(n - 2);
        }
    }


    public int sub(Integer n) {
        if (n <= 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return sub(n - 1) - sub(n - 2);
        }
    }

    @RT
    public int mul(Integer n) {
        if (n <= 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return sub(n - 1) * sub(n - 2);
        }
    }
}
