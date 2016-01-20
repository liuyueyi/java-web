package com.mogu.hui.web.modal;

import com.mogu.hui.biz.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yihui on 16/1/19.
 */
@Service("calculate")
public class Calculate {
    @Autowired
    CalculateService calculateService;

    public Calculate() {
    }

    public int tt(int n) {
        int ans = calculateService.add(n);
        return ans;
    }
}
