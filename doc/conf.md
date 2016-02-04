## 配置文件动态读取

> 配置文件的动态读取，当配置文件修改后动态获取最新的数据
> 

### 1. native版
> 在jar包，or本地保存一个配置文件，从其中读取最新的配置信息
> 

这种还是比较简单的，思路如下:

- 每次读取配置文件时，判断是否更新
- `lastUpdateTime == new File(xxx).lastModified()`
- 若相同，直接返回上次的结果；否则重新加载

代码实例如下：

```java
package com.mogu.hui.biz.tools.conf;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;

/**
 * properties文件信息动态加载
 * Created by yihui on 16/2/4.
 */
public class PropertyConfLoader {
    private static String path = "rate.properties";
    private static long lastUpdated = 0L;
    private static Map<Object, Object> confs;
    private static String absolutePath ;

    public static Map<Object, Object> getConf() throws Exception{
        boolean isUpdated = isConfUpdated();
        if (!isUpdated) {
            return confs;
        }

        Properties properties = new Properties();

        FileInputStream fileInputStream = new FileInputStream(absolutePath);
        // 编码格式指定，否则中文乱码
        properties.load(new InputStreamReader(fileInputStream, "UTF-8"));
        confs = properties;
        return properties;
    }


    /**
     * 判断文件是否有过更新
     * @return
     */
    private static boolean isConfUpdated() throws Exception {
        if (StringUtils.isEmpty(absolutePath)) {
            if (path.startsWith("/")) {
                // 绝对路径
                absolutePath = path;
            } else {
                // 相对路径
                ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                absolutePath = classloader.getResource(path).getPath();
            }
        }

        File f = new File(absolutePath);
        long last = f.lastModified();
        if(last != lastUpdated) {
            lastUpdated = last;
            return true;
        } else {
            return false;
        }
    }
}
```

#### 说明

读取jar包中的配置文件的时候，需要注意下，用的是`ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                absolutePath = classloader.getResource(path).getPath();`

