package com.example.client.runner;

import com.example.client.customthread.Client;
import com.example.client.customthread.StationTreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: client
 * @description: 自定义启动后执行
 * @author: dzp
 * @create: 2021-07-23 09:22
 **/
@Component
public class CustomApplicationRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(CustomApplicationRunner.class);

    private static final String NULL_STR = "";

    @Value("${ip}")
    private String ip;
    @Value("${port}")
    private Integer port;
    /**
     * 是否发送罐存数据
     */
    @Value("${isSendInventory}")
    private Boolean isSendInventory;
    /**
     * 是否发送罐存数据
     */
    @Value("${isSendAlarm}")
    private Boolean isSendAlarm;
    /**
     * 是否发送罐存数据
     */
    @Value("${isSendInventory}")
    private Boolean isSendSensor;
    /**
     * 是否发送罐存数据
     */
    @Value("${isSendDelivery}")
    private Boolean isSendDelivery;
    /**
     * 根路径
     */
    @Value("${rootPath}")
    private String rootPath;
    /**
     * 数据发往哪个服务端
     */
    @Value("${serverName}")
    private String serverName;
    /**
     * 模拟油站数
     */
    @Value("${stationNumber}")
    private Integer stationNumber;
    /**
     * 执行周期
     */
    @Value("${cycle}")
    private Integer cycle;

    /**
     * 模仿CachedThreadPool，每有一个站，就开一个线程去取数
     */
    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            0,
            Integer.MAX_VALUE,
            60L,
            TimeUnit.SECONDS,
            new SynchronousQueue<>(),
            new StationTreadFactory()
    );

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (check()) {
            for (int i = 0; i < stationNumber; i++) {
                Client client = new Client(ip, port, isSendInventory, isSendAlarm, isSendSensor, isSendDelivery, i, rootPath, serverName, cycle);
                threadPoolExecutor.execute(client);
            }
        } else {
            logger.error("配置数据有误，请检查");
            throw new RuntimeException("配置数据有误，请检查");
        }
    }

    /**
     * 校验数据完整性
     *
     * @return
     */
    private boolean check() {
        return this.ip != null && !NULL_STR.equals(this.ip)
                && this.port != null
                && this.isSendInventory != null
                && this.rootPath != null && !NULL_STR.equals(this.rootPath)
                && this.serverName != null && !NULL_STR.equals(this.serverName)
                && this.stationNumber != null
                && this.cycle != null;
    }
}
