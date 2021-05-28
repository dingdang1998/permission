package com.oket.getdata.customthread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: getdata
 * @description: 自定义线程工厂
 * @author: dzp
 * @create: 2021-05-15 15:55
 **/
public class CustomTreadFactory implements ThreadFactory {

    private static final Logger logger = LoggerFactory.getLogger(OnStationRun.class);
    /**
     * 线程编号
     */
    private final AtomicInteger threadNum = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "oket-custom-thread-" + threadNum.getAndIncrement());
        logger.info(t.getName() + " has been created");
        return t;
    }
}
