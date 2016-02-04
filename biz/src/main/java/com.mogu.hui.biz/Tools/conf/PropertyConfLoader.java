package com.mogu.hui.biz.tools.conf;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;

/**
 * properties文件信息动态加载
 * Created by yihui on 16/2/4.
 */
public class PropertyConfLoader extends ConfLoader{
    private  Map<Object, Object> confs;

    private PropertyConfLoader() {
    }

    private static class Single {
        private static PropertyConfLoader instance = new PropertyConfLoader();
    }

    public static PropertyConfLoader getInstance() {
        return Single.instance;
    }


    public Map<Object, Object> getConf() throws Exception{
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
}
