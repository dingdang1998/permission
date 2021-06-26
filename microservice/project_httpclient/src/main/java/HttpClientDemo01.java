import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @program: microservice
 * @description:
 * @author: dzp
 * @create: 2021-06-02 18:50
 **/
public class HttpClientDemo01 {
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.yipinjuzi.com/xiehouyu/1358.html");
        //在分配不同的值之前从不使用它，所以null在idea中为灰色
        HttpEntity entity = null;
        CloseableHttpResponse response = null;
        try {
            //发送http请求 （1.请求类型 get\post    2.网站）
            response = httpClient.execute(httpGet);
            //判断是否得到了 正常的影响
            if (response.getStatusLine().getStatusCode() == 200) {
                //获取响应数据
                entity = response.getEntity();//响应数据
                //将响应数据以Html源码形式显示
                String html = EntityUtils.toString(entity, "UTF-8");
                System.out.println(entity);
                System.out.println("********"+html);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
}

