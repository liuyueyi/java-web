package com.mogu.hui.biz;

import com.mogu.hui.biz.monitor.annotation.RT;
import org.springframework.stereotype.Service;

/**
 * Created by yihui on 16/1/19.
 */
@Service("readService")
public class ReadService {
    public ReadService() {
    }

    @RT
    public void read() {
        try{
            System.out.println("read some!");
            Thread.sleep(10);
        }catch (Exception e) {
        }
    }
}
