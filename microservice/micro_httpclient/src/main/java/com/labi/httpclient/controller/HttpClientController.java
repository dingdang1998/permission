package com.labi.httpclient.controller;

import com.labi.httpclient.service.HttpClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author admin
 */
@RestController
@RequestMapping("/http")
public class HttpClientController {

    @Resource
    HttpClientService httpClientService;

    @GetMapping("/doGetHttpClient")
    public String doGetHttpClient() throws Exception {
        return httpClientService.doGet("https://www.baidu.com/s?wd=aa%20bb%E5%BC%8F%E6%88%90%E8%AF%AD&rsv_spt=1&rsv_iqid=0xd93f1c9a0009bec6&issp=1&f=3&rsv_bp=1&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_dl=ts_1&rsv_sug3=4&rsv_sug1=3&rsv_sug7=100&rsv_sug2=1&rsv_btype=i&prefixsug=aa%2520&rsp=1&inputT=2432&rsv_sug4=3740");
    }

    @PostMapping("/doPostHttpClient")
    public String doPostHttpClient() throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("name", "YANQUN");
        return httpClientService.doPost("https://www.baidu.com/", params);
    }
}
