package com.mogu.hui.biz;

import com.mogu.hui.biz.Tools.EmojTools;
import com.mogu.hui.biz.monitor.annotation.RT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yihui on 16/1/19.
 */
@Service("calculateService")
public class CalculateService {
    @Autowired
    ReadService readService;

    public int tt(Integer n) {
        int ans = this.add(n);

        String hha = "😄不错吧，我也觉得呢hoho😉么么哒";
        String encoder = EmojTools.encode(hha);
        String decoder = EmojTools.decode(encoder);
        System.out.println(encoder + "===>" + decoder);

        ans = this.sub(n);
        System.out.println("sub : " + ans );

        readService.read();
        return ans;
    }

    @RT
    public int add(Integer n) {
        if (n <= 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return add(n - 1) + add(n - 2);
        }
    }

    public int sub(Integer n) {
        if (n <= 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return add(n - 1) - add(n - 2);
        }
    }
}
