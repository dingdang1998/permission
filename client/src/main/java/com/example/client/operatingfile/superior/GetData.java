package com.example.client.operatingfile.superior;

/**
 * @program: client
 * @description:
 * @author: dzp
 * @create: 2021-07-22 16:14
 **/
public interface GetData {
    /**
     * 获取头信息
     *
     * @param path
     * @return
     */
    String[] getHeader(String path);

    /**
     * 获取信息域
     *
     * @param path
     * @return
     */
    String[] getContent(String path);
}
