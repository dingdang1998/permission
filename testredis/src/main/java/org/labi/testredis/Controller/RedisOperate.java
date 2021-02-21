package org.labi.testredis.Controller;

import com.oarage.lsdc.commons.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Range;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @program: testredis
 * @description: 操作类
 * @author: dzp
 * @create: 2021-01-05 19:31
 **/
@Component
public class RedisOperate implements ApplicationRunner {

    private RedisTemplate redisTemplate;

    private StreamOperations streamOperations;

    @Value("${my.getValueKey}")
    private String getValueKey;

    @Value("${my.putValueKey}")
    private String putValueKey;

    @Value("${my.start}")
    private String start;

    @Value("${my.end}")
    private String end;

    @Value("${my.dataCount}")
    private int dataCount;

    @Value("${my.entityNum}")
    private int entityNum;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        test();
    }

    public void test() {
        List<ObjectRecord> errDataList = configToEntity(entityNum);

        if (errDataList.size() > 0) {
            for (ObjectRecord objectRecord : errDataList) {
                Object value = objectRecord.getValue();
                addData(putValueKey, value);
            }
            System.out.println(new Date() +
                    "--取值的key:" + getValueKey +
                    "--放值的key:" + putValueKey +
                    "--开始id:" + start +
                    "--结束id:" + end +
                    "--取多少条:" + dataCount +
                    "--对应实体:" + entityNum +
                    "--实际取值条数" + errDataList.size());
        } else {
            System.out.println("null");
        }
    }

    /**
     * 往redis键存放数据
     *
     * @param key
     * @param obj
     */
    public void addData(String key, Object obj) {
        if (streamOperations == null) {
            streamOperations = redisTemplate.opsForStream();
        }
        streamOperations.add(ObjectRecord.create(key, obj));
    }

    /**
     * 从redis键获取数据
     *
     * @param key
     * @param classType
     * @return
     */
    public List<ObjectRecord> getData(String key, Class classType) {
        if (streamOperations == null) {
            streamOperations = redisTemplate.opsForStream();
        }
        return streamOperations.range(classType, key, Range.closed(start, end), RedisZSetCommands.Limit.limit().count(dataCount));
    }

    /**
     * redisTemplate 配置
     *
     * @param redisTemplate
     */
    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    /**
     * 根据配置文件转成对应的实体
     */
    private List<ObjectRecord> configToEntity(int entityNum) {
        //跟据配置找到对应实体
        switch (entityNum) {
            case 1:
                return getData(getValueKey, StartUpData.class);
            case 2:
                return getData(getValueKey, Delivery.class);
            case 3:
                return getData(getValueKey, Alarm.class);
            case 4:
                return getData(getValueKey, LeakResult.class);
            case 5:
                return getData(getValueKey, Sales.class);
            case 6:
                return getData(getValueKey, UploadResponseData.class);
            case 7:
                return getData(getValueKey, ClientParam.class);
            case 8:
                return getData(getValueKey, OperateData.class);
            case 9:
                return getData(getValueKey, Inventory.class);
            case 10:
                return getData(getValueKey, LeakSensor.class);
            default:
                return null;
        }
    }
}
