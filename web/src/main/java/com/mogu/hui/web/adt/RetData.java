package com.mogu.hui.web.adt;

import java.io.Serializable;

/**
 * Created by yihui on 16/1/17.
 */
public class RetData<T> implements Serializable {

    private static final long serialVersionUID = -7658519418208586385L;

    private Status status;
    private T result;

    public RetData() {
        status = new Status();
    }

    public RetData(Status status, T result) {
        this.status = status;
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RetData{" +
                "status=" + status +
                ", result=" + result +
                '}';
    }
}
