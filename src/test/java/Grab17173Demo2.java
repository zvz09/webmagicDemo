import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by zvz on 2017/3/31.
 *
 * 热门游戏排行
 */
public class Grab17173Demo2 implements PageProcessor {

    private Site site = Site
            .me()
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    public void process(Page page) {

        page.putField("新游戏期待", page.getHtml().xpath("//div[@class='mod-rank-c1']/div/div[2]/div/div/ul/li/div[1]/text()").all());
        page.putField("新游戏名称", page.getHtml().xpath("//div[@class='mod-rank-c1']/div/div[2]/div/div/ul/li/div[2]/div/a[1]/text()").all());
        page.putField("新游戏图片", page.getHtml().xpath("//div[@class='mod-rank-c1']/div/div[2]/div/div/ul/li/div[3]/a/img/@data-src").all());
        page.putField("新游戏票数", page.getHtml().xpath("//div[@class='mod-rank-c1']/div/div[2]/div/div/ul/li/div[4]/text()").all());

        page.putField("热门游戏排名", page.getHtml().xpath("//div[@class='mod-rank-c2']/div/div[2]/div[2]/div[2]/div/ul/li/div[1]/text()").all());
        page.putField("热门游戏名称", page.getHtml().xpath("//div[@class='mod-rank-c2']/div/div[2]/div[2]/div[2]/div/ul/li/div[2]/div/a/text()").all());
        page.putField("热门游戏图片", page.getHtml().xpath("//div[@class='mod-rank-c2']/div/div[2]/div[2]/div[2]/div/ul/li/div[3]/a/img/@data-src").all());
        page.putField("热门游戏票数", page.getHtml().xpath("//div[@class='mod-rank-c2']/div/div[2]/div[2]/div[2]/div/ul/li/div[4]/text()").all());
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new Grab17173Demo2()).addUrl("http://www.17173.com/")
                .addPipeline(new ConsolePipeline()) .run();
    }
}
