package com.labi.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @program: microservice
 * @description:
 * @author: dzp
 * @create: 2021-06-09 18:26
 **/
public class WebMagicDemo implements PageProcessor {

    private final Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {
        Selectable selectable = page.getHtml().xpath("//div[@class=PostContent]/p/text()");
        System.out.println("*****");
        List<String> all = selectable.all();
        for (String s:all){
            System.out.println(s);
        }
        System.out.println("*****");
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        //webmagic对于爬取https协议的网站 ，存在bug
        //run()会调用process()方法
        Spider.create(new WebMagicDemo())
                .addUrl("http://www.yipinjuzi.com/xiehouyu/1358.html")
                //流向控制台
                .addPipeline(new ConsolePipeline())
                .run();
    }
}
