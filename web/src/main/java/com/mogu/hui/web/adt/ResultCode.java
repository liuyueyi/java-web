package com.mogu.hui.web.adt;

/**
 * Created by yihui on 16/1/18.
 */
public enum ResultCode {
    SUCCESS(1001, "SUCCESS"),
    FAIL(4004, "FAIL"),
    PARAMETER_ERROR(5001, "传入参数有误! %s : %s");

    int code;
    String msg;

    private ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 重新设置返回的数据结构
     * @param result
     * @param args 用于填充返回提示文案的文本
     */
    public void mixin(RetData result, Object ...args) {
        if (args.length > 0){
            msg = String.format(msg, args);
        }

        if (result.getStatus() != null) {
            result.getStatus().setCode(this.code);
            result.getStatus().setMsg(this.msg);
        } else {
            Status status = new Status(code, msg);
            result.setStatus(status);
        }
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
