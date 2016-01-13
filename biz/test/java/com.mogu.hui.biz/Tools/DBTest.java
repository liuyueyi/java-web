package com.mogu.hui.biz.Tools;

import com.mogu.hui.biz.Tools.Db.DBTools;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by yihui on 16/1/11.
 */
public class DBTest {
    private final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(DBTest.class);

    private class User {
        protected int id;
        protected String username;
        protected String password;
        protected String address;
    }

    @Test
    public void dbTest() throws SQLException {
        String sql = "select * from user limit 2";
        Object ans = DBTools.select(sql, User.class);
        System.out.println(ans);
    }
}
