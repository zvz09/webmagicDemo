import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by zvz on 2017/3/30.
 *
 * 新游测试信息
 */
public class Grab17173Demo implements PageProcessor {

    public static final String URL_LIST = "http://newgame\\.17173\\.com/testing-list\\.html";

    public static final String URL_POST = "http://newgame.17173.com/game-info-.*html";

    private Site site = Site
            .me()
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");



    public void process(Page page) {

        if(page.getUrl().regex(URL_LIST).match()){

            page.addTargetRequests(page.getHtml().xpath("//table/tbody/tr/td[2]").regex("http://newgame.17173.com/game-info-.*html").all());
        }else if(page.getUrl().regex(URL_POST).match()){
            page.putField("游戏名称", page.getHtml().xpath("//div[@class='con']/h1/text()"));
            page.putField("游戏圖片地址", page.getHtml().xpath("//div[@class='mod-mater-avatar']/img/@src"));
            page.putField("游戲簡介", page.getHtml().xpath("//div[@class='mod-mater-intro']/p/text()"));
            page.putField("更多圖片地址", page.getHtml().xpath("//div[@class='scroll-con']/div/a/img/@src").all());
        }

      /*  page.putField("游戏名称", page.getHtml().xpath("//td").regex("title=\".*\"").all());
        page.putField("src", page.getHtml().xpath("//td").regex("game-info-.*html").all());*//*
        page.putField("测试时间", page.getHtml().xpath("//table/tbody/tr/td[1]/text()").all());
        page.putField("游戏名称", page.getHtml().xpath("//table/tbody/tr/td[2]").regex("http://newgame.17173.com/game-info-.*html").all());
        page.putField("测试名称", page.getHtml().xpath("//table/tbody/tr/td[3]/text()").all());
        page.putField("激活码", page.getHtml().xpath("//table/tbody/tr/td[4]/text()").all());
        page.putField("开发商", page.getHtml().xpath("//table/tbody/tr/td[5]/a/text()").all());
        page.putField("运营商", page.getHtml().xpath("//table/tbody/tr/td[6]/a/text()").all());*/
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new Grab17173Demo()).addUrl("http://newgame.17173.com/testing-list.html")
                .addPipeline(new DownloadPipeline()) .run();
    }
}
