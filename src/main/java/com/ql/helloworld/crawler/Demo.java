package com.ql.helloworld.crawler;

import com.ql.helloworld.util.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 爬虫
 */
public class Demo {
    public static void main(String[] args) {
        //要爬取的网站
        String url = "https://www.qidian.com/search?kw=完美世界";
        String html = HttpUtil.doGet(url);
        if (html == null) {
            return;
        }
        //获得该网站的Document对象
        Document document = Jsoup.parse(html);
        //我们可以通过对Document对象的select方法获得具体的文本内容
        //下面的意思是获得.bool-img-text这个类下的 ul 下的 li
        Elements rootselect = document.select(".book-img-text ul li");
        for (Element ele : rootselect) {
            //然后获得a标签里面具体的内容
            Elements novelname = ele.select(".book-mid-info h4 a");
            String name = novelname.text();

            Elements author = ele.select(".book-mid-info p a");
            System.out.println(author);
            String authorname = author.first().text();

            Elements sumadvice = ele.select(".total p");
            System.out.println(sumadvice);
            String sum = sumadvice.last().text();

            System.out.println("书名:" + name + " 作者:" + authorname + " 推荐量:" + sum);
        }
    }
}
