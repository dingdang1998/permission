package com.labi.httpclient.service;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author admin
 */
@Component
public class HttpClientService {
    @Autowired
    CloseableHttpClient httpClient;
    @Autowired
    RequestConfig requestConfig;


    /**
     * 无参get
     *
     * @param url
     * @return
     * @throws Exception
     */
    public String doGet(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //相应正常，将响应体打印
        if (response.getStatusLine().getStatusCode() == 200) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        return null;
    }

    /**
     * 带参get
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public String doGet(String url, Map<String, Object> map) throws Exception {
        //localhost?name=zs
        URIBuilder uriBuilder = new URIBuilder(url);
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }
        return doGet(uriBuilder.build().toString());
    }

    /**
     * 无参post
     *
     * @param url
     * @return
     * @throws Exception
     */
    public String doPost(String url) throws Exception {
        return this.doPost(url, null);
    }

    /**
     * 带参post
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public String doPost(String url, Map<String, String> map) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        String result = null;
        //请求参数

        List<NameValuePair> list = new ArrayList<>();

        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        if (list.size() > 0) {
            UrlEncodedFormEntity entiry = new UrlEncodedFormEntity(list, "UTF-8");
            httpPost.setEntity(entiry);
        }

        CloseableHttpResponse response = httpClient.execute(httpPost);
        if (response != null) {
            HttpEntity responseEntity = response.getEntity();//得到响应体
            if (responseEntity != null) {
                result = EntityUtils.toString(responseEntity, "UTF-8");
            }
        }
        return result;
    }
}
