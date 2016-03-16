package com.mogu.hui.biz.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yihui on 16/3/14.
 */
public class HeapTest {

    class Item implements Comparable<Item> {
        public int sort;
        public int created;

        public Item(int sort, int created) {
            this.sort = sort;
            this.created = created;
        }

        @Override
        public int compareTo(Item item) {
            if (this.sort > item.sort) {
                return 3;
            }

            if (this.sort == item.sort && this.created > item.created) {
                return 2;
            }

            if (this.sort == item.sort && this.created <= item.created) {
                return -1;
            }

            else {
                return -2;
            }
        }

        @Override
        public String toString() {
            return "Item{" +
                    "sort=" + sort +
                    ", created=" + created +
                    '}';
        }
    }

    private List<Item> genItem(int size) {
        List<Item> items = new ArrayList<>(size);
        for (int i = 0; i < size; i ++) {
            int sort = (int) (Math.random() * 4);
            int created = (int) (Math.random() * 1231243 + 1000);
            items.add(new Item(sort, created));
        }

        return items;
    }

    @Test
    public void test() {
        List<Item> items = this.genItem(7);
        MaxHeap<Item> heap = new MaxHeap<>(4);
        for (Item item: items) {
            heap.add(item);
        }

        System.out.println(items);

        List<Item> result = heap.listData();
        System.out.println(result);
    }


    private List<Integer> genInt(int size) {
        List<Integer> ids = new ArrayList<>(size);
        for(int i = 0; i < size; i ++ ) {
            ids.add((int) (Math.random() * 100));
        }
        return ids;
    }

    private void print(List<Integer> ids) {
        for (int id: ids) {
            System.out.print(id + ",");
        }
        System.out.println();
    }


    @Test
    public void test2() {
        int size = 10;
        List<Integer> ids = this.genInt(size);
        print(ids);
        MinHeap<Integer> heap = new MinHeap<Integer>(5);
        for(Integer i: ids) {
            heap.add(i);
        }

        List<Integer> result = heap.listData();
        print(result);
    }

}
