package com.labi.crawler.util;

import com.labi.crawler.entity.Statement;
import org.codehaus.jackson.map.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: microservice
 * @description: json工具类
 * @author: dzp
 * @create: 2021-06-10 09:22
 **/
public class JsonUtil {
    /**
     * json对象-对象（一行记录）
     */
    static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * json对象(字符串形式) -> 对象
     *
     * @param json
     * @param valueType
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T json2Object(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对象 -> json对象(字符串形式)
     *
     * @param value
     * @return
     * @throws Exception
     */
    public static String object2json(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.2.128", 6379);
        Map<String, String> map = jedis.hgetAll("statement");

        List<Statement> statementList = new ArrayList<>();
        for (String statementStr : map.values()) {
            Statement statement = json2Object(statementStr, Statement.class);
            statementList.add(statement);
        }
        for (Statement statement : statementList) {
            System.out.println(statement.getMeaning());
        }
    }
}
