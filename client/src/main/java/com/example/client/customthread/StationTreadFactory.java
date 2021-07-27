package com.example.client.customthread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: client
 * @description: 油站线程工厂
 * @author: dzp
 * @create: 2021-07-23 08:52
 **/
public class StationTreadFactory implements ThreadFactory {

    private static final Logger logger = LoggerFactory.getLogger(StationTreadFactory.class);
    /**
     * 线程编号
     */
    private final AtomicInteger threadNum = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        threadNum.incrementAndGet();
        Thread t = new Thread(r, "sendMessage-thread-" + threadNum.getAndIncrement());
        logger.info(t.getName() + " has been created");
        return t;
    }
}
