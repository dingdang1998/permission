package com.oket.getdata.timer;

import com.oket.getdata.config.DataBaseConfigInfo;
import com.oket.getdata.customthread.CustomTreadFactory;
import com.oket.getdata.customthread.OnStationRun;
import com.oket.getdata.database.OperatingDatabaseImpl;
import com.oket.getdata.entity.DataBaseConfigInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: getdata
 * @description: 定时器
 * @author: dzp
 * @create: 2021-05-13 13:44
 **/
@Configuration
@EnableScheduling
public class Timer {

    private static final Logger logger = LoggerFactory.getLogger(Timer.class);

    /**
     * 模仿CachedThreadPool，每有一个站，就开一个线程去取数
     */
    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            0,
            Integer.MAX_VALUE,
            60L,
            TimeUnit.SECONDS,
            new SynchronousQueue<>(),
            new CustomTreadFactory()
    );

    @Autowired
    private DataBaseConfigInfo dataBaseConfigInfo;
    /**
     * 文件保存的根路径
     */
    @Value("${rootPath}")
    private String rootPath;

    /**
     * 1、如果没有配置文件保存的根路径，直接抛出异常
     * 2、每个站开启一个线程，去进行操作
     */
    @Async
    @Scheduled(fixedDelayString = "${time.fixedDelay}")
    public void exportDataTask() {
        if (rootPath != null && !"".equals(rootPath)) {
            //拿到配置文件中的数据库配置
            List<DataBaseConfigInfoEntity> configInfoEntities = dataBaseConfigInfo.getConfigInfoEntities();

            if (configInfoEntities != null && configInfoEntities.size() > 0) {
                for (DataBaseConfigInfoEntity dataBaseConfigInfoEntity : configInfoEntities) {
                    //基础数据完整，往下进行
                    if (dataBaseConfigInfoEntity.checkBaseData()) {
                        OnStationRun onStationRun = new OnStationRun(new OperatingDatabaseImpl(), dataBaseConfigInfoEntity, rootPath);
                        threadPoolExecutor.execute(onStationRun);
                    } else {
                        //基础数据不完整
                        logger.error("基础信息不完整---" + dataBaseConfigInfoEntity.toString());
                    }
                }
            }
        } else {
            throw new RuntimeException("未配置根路径");
        }
    }
}
