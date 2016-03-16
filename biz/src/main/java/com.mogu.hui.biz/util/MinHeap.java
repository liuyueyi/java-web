package com.mogu.hui.biz.util;


import java.util.*;

/**
 * Created by yihui on 16/3/14.
 */
public class MinHeap <T extends Comparable> {
    private PriorityQueue<T> priorityQueue;
    private int size;

    public MinHeap(int size) {
        this.size = size;
        priorityQueue = new PriorityQueue<>(size, new Comparator<T>() {

            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        });
    }

    public MinHeap(int size, Comparator<T> comparator) {
        this.size = size;
        priorityQueue = new PriorityQueue<>(size, comparator);
    }


    public void add(T t) {
        if (priorityQueue.size() < size) {
            priorityQueue.add(t);
        } else {
            T top = priorityQueue.peek();
            if (t.compareTo(top) > 0) {
                priorityQueue.poll();
                priorityQueue.add(t);
            }
        }
    }

    public List<T> listData() {
        List list = new ArrayList<>(priorityQueue);
        Collections.sort(list);
        Collections.reverse(list);
        return list;
    }

}
