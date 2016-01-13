package com.mogu.hui.biz.Tools.Db;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by yihui on 16/1/11.
 */
public class DBTools {
    private static DBUtil dbUtil;

    static {
        dbUtil = new DBUtil();
        DBUtil.init("root", "");
    }


    public static List<Map<String, Object>> select(String sql, Class clz) throws SQLException {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        List<Map<String, Object>> result = new ArrayList<>();

        try {
            conn = dbUtil.getConn();
            if (conn == null) {
                return Collections.emptyList();
            }
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            Field[] fields = clz.getDeclaredFields();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                for (Field field: fields) {
                    String name = field.getName();
                    map.put(field.getName(), rs.getObject(field.getName()));
                }
                result.add(map);
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (stat != null) {
                    stat.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            dbUtil.close(conn);
        }
    }
}
