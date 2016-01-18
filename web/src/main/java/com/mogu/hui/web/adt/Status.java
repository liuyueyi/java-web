package com.mogu.hui.web.adt;

import java.io.Serializable;

/**
 * Created by yihui on 16/1/17.
 */
public class Status implements Serializable{
    private static final long serialVersionUID = -1470575617124127068L;

    private int code;
    private String msg;

    public Status() {
    }

    public Status(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Status{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
