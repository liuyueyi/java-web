package com.mogu.hui.biz.tools.conf;

import org.springframework.util.StringUtils;

import java.io.File;

/**
 * 动态读取配置文件
 * Created by yihui on 16/2/4.
 */
public class ConfLoader {
    protected String path = "rate.properties";
    protected long lastUpdated = 0L;
    protected String absolutePath ;

    protected ConfLoader() {

    }

    /**
     * 判断文件是否有过更新
     * @return
     */
    protected  boolean isConfUpdated() throws Exception {
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
