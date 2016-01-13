package com.mogu.hui.biz.Tools.Db;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yihui on 16/1/11.
 */
public class DBUtil {
    private static DataSource dataSource;

    public static void init(String uname, String passwd) {
        try {
            Map<String, Object> druidMapTo = new HashMap<String, Object>();
            druidMapTo.put(DruidDataSourceFactory.PROP_USERNAME, uname);
            druidMapTo.put(DruidDataSourceFactory.PROP_PASSWORD,
                    passwd);
            druidMapTo
                    .put(DruidDataSourceFactory.PROP_URL,
                            "jdbc:mysql://127.0.0.1:3306/samp_db?useUnicode=true&characterEncoding=utf8");
            druidMapTo.put(DruidDataSourceFactory.PROP_INITIALSIZE, "3");
            druidMapTo.put(DruidDataSourceFactory.PROP_MINIDLE, "3");
            druidMapTo.put(DruidDataSourceFactory.PROP_MAXACTIVE, "5");
            druidMapTo.put(DruidDataSourceFactory.PROP_MAXWAIT, "1000");
            dataSource = DruidDataSourceFactory.createDataSource(druidMapTo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public Connection getConn() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (Exception ex) {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            ex.printStackTrace();
        }
        return conn;
    }

    public void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
