package com.mogu.hui.biz.util;

import java.util.*;

/**
 * 大顶堆，将sort值高的放在最前面，用于获取最小的n个数
 * Created by yihui on 16/3/14.
 */
public class MaxHeap<E extends Comparable> {
    // 优先队列
    private PriorityQueue<E> priorityQueue;
    private int maxSize; // 最大的数量

    public MaxHeap(int maxSize) {
        this.maxSize = maxSize;
        this.priorityQueue = new PriorityQueue<>(maxSize, new Comparator<E>() {

            @Override
            public int compare(E o1, E o2) {
                return o2.compareTo(o1);
            }
        });
    }

    public void add(E e) {
        if (priorityQueue.size() < maxSize) {
            priorityQueue.add(e);
        } else {
            E top = priorityQueue.peek();
            if (e.compareTo(top) < 0) { // 若 e 比最大的小，则丢掉最大的，并插入
                priorityQueue.poll();
                priorityQueue.add(e);
            }
        }
    }

    /**
     * 获取所有的值
     * @return
     */
    public List<E> listData() {
        List<E> list = new ArrayList<>(priorityQueue);
        Collections.sort(list);
        return list;
    }
}
