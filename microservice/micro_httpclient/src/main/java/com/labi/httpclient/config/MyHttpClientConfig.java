package com.labi.httpclient.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: microservice
 * @description:
 * @author: dzp
 * @create: 2021-06-03 10:10
 **/
@Configuration
public class MyHttpClientConfig {
    /**
     * 连接池中最大连接数
     */
    @Value("${http.maxTotal}")
    private Integer maxTotal;
    /**
     * 平均访问每个网站的连接数
     */
    @Value("${http.defaultMaxPerRoute}")
    private Integer defaultMaxPerRoute;
    /**
     * 创建链接的时间
     */
    @Value("${http.connectTimeout}")
    private Integer connectTimeout;
    /**
     * 链接服务器的时间
     */
    @Value("${http.connectRequestTimeout}")
    private Integer connectRequestTimeout;
    /**
     * 数据传输的时间
     */
    @Value("${http.socketTimeout}")
    private Integer socketTimeout;

    /**
     * 实例化连接池管理对象
     *
     * @return
     */
    @Bean(name = "httpClientConnectionManager")
    public PoolingHttpClientConnectionManager getHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setMaxTotal(maxTotal);
        manager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        return manager;
    }

    /**
     * 将HttpClientBuilder对象放入Ioc容器
     *
     * @param manager
     * @return
     * @Qualifier 将ioc容器中的bean取出来
     */
    @Bean(name = "httpClientBuilder")
    public HttpClientBuilder getHttpClientBuilder(@Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager manager) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(manager);
        return httpClientBuilder;
    }

    /**
     * manager manager->builder  builder ->HttpClient
     * 将HttpClient对象放入Ioc容器
     *
     * @param builder
     * @return
     */
    @Bean
    public CloseableHttpClient getCloseableHttpClient(@Qualifier("httpClientBuilder") HttpClientBuilder builder) {
        return builder.build();
    }

    @Bean(name = "builder")
    public RequestConfig.Builder getBuilder() {
        RequestConfig.Builder builder = RequestConfig.custom();
        //链式写法
        return builder.setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectRequestTimeout).setSocketTimeout(socketTimeout);
    }

    @Bean(name = "requestConfig")
    public RequestConfig getRequestConfig(@Qualifier("builder") RequestConfig.Builder builder) {
        return builder.build();
    }
}
