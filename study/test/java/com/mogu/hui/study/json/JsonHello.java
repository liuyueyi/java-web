package com.mogu.hui.study.json;

import java.util.List;

/**
 * 用于测试json序列化
 * Created by yihui on 16/4/22.
 */
public class JsonHello<T> {
    private String name;

    private List<T> list;

    public JsonHello () {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "JsonHello{" +
                "name='" + name + '\'' +
                ", list=" + list +
                '}';
    }
}

class Hello {
    String hello;
    List<String> user;

    public Hello(){

    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public List<String> getUser() {
        return user;
    }

    public void setUser(List<String> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Hello{" +
                "hello='" + hello + '\'' +
                ", user=" + user +
                '}';
    }
}
