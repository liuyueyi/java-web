package com.mogu.hui.study.base;

/**
 * 简单的测试实体类
 * Created by yihui on 16/1/9.
 */
public class Cell {
    private final String PREFIX = "cell_prefix_";

    public Cell() {
    }

    public String getCell(int id) {
        return PREFIX + id;
    }

    /**
     * 性能测试 AOP
     * @param n
     * @return
     */
    public long nTimes(int n) {
        long sub = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sub += i * j;
            }
        }
        System.out.println("n + sub = " + n + " + " + sub);
        return sub;
    }

//
//    /**
//     * 权限测试 AOP
//     * @return
//     */
//    public boolean addUser(UserBeanImpl bean) {
//        bean.addUser();
//        return true;
//    }
}
