package com.labi.crawler;

import com.labi.crawler.entity.Statement;
import com.labi.crawler.util.JsonUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @program: microservice
 * @description:
 * @author: dzp
 * @create: 2021-06-04 15:04
 **/
public class CrawlerDemo {

    private static Jedis jedis;

    static {
        jedis = new Jedis("192.168.2.128", 6379);
    }

    public static void main(String[] args) {
        //获取数据
        String html = getData();
        //解析数据
        List<Statement> statementList = parseData2Statement(html);
        //存储数据
        writeRedis(statementList);
    }

    /**
     * 获取数据
     *
     * @return
     */
    public static String getData() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.yipinjuzi.com/xiehouyu/1358.html");
        //在分配不同的值之前从不使用它，所以null在idea中为灰色
        HttpEntity entity = null;
        CloseableHttpResponse response = null;
        try {
            //发送http请求 （1.请求类型 get\post    2.网站）
            response = httpClient.execute(httpGet);
            //返回数据
            String html = null;
            if (response.getStatusLine().getStatusCode() == 200) {
                //获取响应数据
                entity = response.getEntity();//响应数据
                //将响应数据以Html源码形式显示
                html = EntityUtils.toString(entity, "UTF-8");
            }
            return html;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Statement> parseData2Statement(String html) {
        List<Statement> statementList = new ArrayList<>();
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByTag("p");
        for (Element element : elements) {
            statementList.add(new Statement(String.valueOf(UUID.randomUUID()), element.text()));
        }
        return statementList;
    }

    public static void writeRedis(List<Statement> statementList) {
        for (Statement statement : statementList) {
            jedis.hset("statement", statement.getId(), JsonUtil.object2json(statement));
        }
    }
}
