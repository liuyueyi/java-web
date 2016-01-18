# Web 应用之统一返回参数

<!-- create time: 2016-01-18 15:23:07  -->

> web返回json串时，一般返回的数据中需要一个状态码，表示返回的正文是否为需要的东西
> 
> 

先看代码,在分析思路:

**Status.java**

```java
package com.mushroom.hui.adt;

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
```

**RetData.java**

```java
package com.mushroom.hui.adt;

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
```

**ResultCode**

```
package com.mushroom.hui.adt;

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
```

使用示例：

```
@RequestMapping(value = "/string")
@ResponseBody
public String resStr(HttpServletRequest request) {
    String id = request.getParameter("id");
    String ans = "The id is: " + id;
    if (StringUtils.isBlank(id)) {
        return this.buildResult(ResultCode.PARAMETER_ERROR, ans, "id", id);
    }

    return this.buildResult(ResultCode.SUCCESS, ans);
}
    
/**
 * 构建返回的结果
 * @param code  返回的状态
 * @param result 正文
 * @param msg 提示信息
 * @return
 */
private String buildResult(ResultCode code, Object result, String ...msg) {
    RetData retData = new RetData();
    code.mixin(retData, msg);
    retData.setResult(result);

    // 利用fastJson进行序列化
    String ans = JSON.toJSONString(retData);
    return ans;
}
```

## 分析

> 上面这样做的好处是什么？ 为什么这样设计？
 
返回的JSON格式如下，基本上都这么玩了，其中status表示请求的状态，是否成功，失败了应该提示什么东西； result 对应的就是返回的正文

```json
{
    "result": {
        ...
    }
    "status": {
        "code": 1001,
        "msg": "SUCCESS"
    }
}
```

上面的设计思路：

- 状态码，提示信息都放在了枚举类 `ResultCode` 中
- RetData 默认构造一个返回成功的对象
- 通过枚举类的 `mixin` 方法来修改错误的返回状态码

从使用来看，在buildResult方法基本上可以做到非常通用了，调用方直接选择一个枚举的值，就可以方便的获得返回的json串

而新增或修改不同的错误码，只需要在枚举类中进行即可，解耦度还是不错的

另外一点就是传入的可变参数：`msg`, 可以用来极大的丰富返回的提示信息
