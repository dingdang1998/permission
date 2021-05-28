package com.oket.getdata.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * @program: getdata
 * @description: 自定义操作数据库工具
 * @author: dzp
 * @create: 2021-05-13 10:51
 **/
public class CustomJdbcUtil {

    private static final Logger logger = LoggerFactory.getLogger(CustomFileUtil.class);

    /**
     * 数据库驱动
     */
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";


    /**
     * 获取一个链接mysql的Connection对象
     *
     * @param driver
     * @param url
     * @param user
     * @param pwd
     * @return
     */
    public static Connection getConnection(String driver, String url, String user, String pwd) {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            logger.error("获取mysql链接失败---" + "url:" + url + "---user:" + user + "---pwd:" + pwd + e);
            return null;
        }
    }


    /**
     * 返回url
     * 注意：这里的数据库名是直接写死的！！！
     * 例如：jdbc:mysql://localhost:3306/students?characterEcoding=utf-8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true
     *
     * @param ipAndPort
     * @return
     */
    public static String getUrl(String ipAndPort) {
        StringBuffer stringBuffer = new StringBuffer("jdbc:mysql://")
                .append(ipAndPort)
                .append("/smkenter?characterEcoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true");
        return stringBuffer.toString();
    }

    /**
     * 释放Mysql资源
     *
     * @param resultSet
     * @param statement
     * @param connection
     */
    public static void closeResource(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("释放Mysql资源失败" + e);
        }
    }

}
