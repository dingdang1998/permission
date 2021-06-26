import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * @program: microservice
 * @description:
 * @author: dzp
 * @create: 2021-06-03 14:19
 **/
public class JsoupDemo01 {
    public static void main(String[] args) throws IOException {
        //将html文件的内容读取到内存    commons-io
        String lanqiaoHtml = FileUtils.readFileToString(new File("F:/test/aa.txt"), "UTF-8");
        //解析(lanqiao.txt -> String ->Document)
        Document document = Jsoup.parse(lanqiaoHtml);
        //标签选择器
        Element title = document.getElementsByTag("title").first();
        System.out.println(title.text());
        //<p></p>的读取
        Elements elements = document.getElementsByTag("p");
        for (Element element : elements) {
            System.out.println(element.text());
        }
        //id选择器
        Element dasaiId = document.getElementById("dasaiId");
        System.out.println(dasaiId.text());
        //类选择器class--news-msg
        Element last = document.getElementsByClass("news-msg").last();
        System.out.println(last.text());
        // 属性选择器
        Element hello1 = document.getElementsByAttribute("hello").first();
        System.out.println(hello1);
        System.out.println(hello1.text());
        //attr可以拿到一切属性
        System.out.println(hello1.attr("hello"));
        Attributes attributes = hello1.attributes();
        Iterator<Attribute> iterator = attributes.iterator();
        while (iterator.hasNext()) {
            Attribute next = iterator.next();
            System.out.println("++" + next.getKey());
            System.out.println("++" + next.getValue());
        }
        Element hello2 = document.getElementsByAttributeValue("hello", "word2").first();
        System.out.println(hello2.text());

        System.out.println("-----jsoup选择器,select--------");
        //标签
        Elements metas = document.select("meta");
        for (Element element : metas) {
            System.out.println(element.attr("name"));
        }
        //#id
        Elements selectId = document.select("#dasaiId");
        System.out.println(selectId.text());
        //.class
        Elements selectClasses = document.select(".news-msg");
        for (Element element : selectClasses) {
            System.out.println(element.text());
        }
        System.out.println("******");
        //属性[]
        Elements selectAttrs = document.select("[myname]");
        for (Element element : selectAttrs) {
            System.out.println(element.text());
        }
        //复合选择器
        //交集选择器：直接拼起来写
        Elements selectJJ = document.select(".news-msg[myname=mynews2]");
        System.out.println(selectJJ.text());
        System.out.println("***---");
        //并集选择器: , 。特殊：个别选择器 和jquery用法不一致，例如jsoup中不支持，并集
        Elements selectBJ = document.select("[myname=mynews],news-msg2");
        System.out.println(selectBJ.text() + "特殊情况：jsoup中不能像jq那样使用,实现并集");
        //后代:空格
        Elements elementsHD = document.select("#myulID li");
        System.out.println(elementsHD.toString());
        System.out.println("////2222");
        Elements elementsHD2 = document.select("#myulID a");
        System.out.println(elementsHD2.toString());
        //子代:>
        System.out.println("////333");
        Elements elementsZD = document.select("#myulID>li");
        System.out.println(elementsZD.toString());
        System.out.println("////444");

        Elements elementsZD2 = document.select("#myulID>a");//a是#myulID的孙子代，不是直接子代，因此无法通过子代选择器选中
        System.out.println(elementsZD2.toString());

        System.out.println("////555");
    }
}
