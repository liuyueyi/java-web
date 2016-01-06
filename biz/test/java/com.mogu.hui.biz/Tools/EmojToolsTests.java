package com.mogu.hui.biz.Tools;

import org.junit.Test;

/**
 * Created by yihui on 16/1/6.
 */
public class EmojToolsTests {
    @Test
    public void emojTest() {
        String emoj = "😄嘿嘿(*^__^*)😢哭";
        String ans = EmojTools.encode(emoj);
        System.out.println(ans);
        String recover = EmojTools.decode(ans);
        System.out.println(recover);

        System.out.println("is same? " + emoj.equals(recover));
    }
}
