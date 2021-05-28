package com.oket.getdata;

import com.oket.getdata.config.DataBaseConfigInfo;
import com.oket.getdata.customthread.CustomTreadFactory;
import com.oket.getdata.customthread.OnStationRun;
import com.oket.getdata.database.OperatingDatabaseImpl;
import com.oket.getdata.entity.DataBaseConfigInfoEntity;
import com.oket.getdata.timer.Timer;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class GetdataApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(GetdataApplicationTests.class);
    @Autowired
    private DataBaseConfigInfo dataBaseConfigInfoBO;
    @Autowired
    private Timer timer;

    @Value("${rootPath}")
    private String rootPath;

    /**
     * 模仿CachedThreadPool，每有一个站，就开一个线程去取数
     */
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            0,
            Integer.MAX_VALUE,
            60L,
            TimeUnit.SECONDS,
            new SynchronousQueue<>(),
            new CustomTreadFactory()
    );

    @Autowired
    private DataBaseConfigInfo dataBaseConfigInfo;

    @Test
    void contextLoads() {

    }

}
