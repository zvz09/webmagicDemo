import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by zvz on 2017/4/9.
 */
public class CsdnBlogDemo implements PageProcessor {

    private Site site = Site
            .me()
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    public void process(Page page) {

        page.putField("地址",page.getUrl().toString());
        page.putField("标题", page.getHtml().xpath("//span[@class='link_title']/a/text()"));
        page.putField("内容", page.getHtml().xpath("//div[@class='markdown_views']"));
        page.putField("标签",page.getHtml().xpath("//div[@class='article_l']/span/a/text()").all());
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new CsdnBlogDemo()).addUrl("http://blog.csdn.net/qinyuanpei/article/details/69787784")
                .addPipeline(new ConsolePipeline()) .run();
    }
}
