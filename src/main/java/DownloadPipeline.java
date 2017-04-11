import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zvz on 2017/3/31.
 */
public class DownloadPipeline implements Pipeline {
    public void process(ResultItems resultItems, Task task) {
        String dirPath = "E:/360Downloads/";
       Map<String ,Object> map =  resultItems.getAll();

        List<String> urls = (List<String>) map.get("更多圖片地址");
        if(urls!=null){
            for(String url :urls){
                System.out.println(url);
                DownloadPictureTest.downloadPicture(url,dirPath,new Date().getTime()+".jpg");
            }
            for(int i=0;i<=4;i++){
                if(urls.get(i)!=null){
                    System.out.println(urls.get(i));
                    DownloadPictureTest.downloadPicture(urls.get(i),dirPath,new Date().getTime()+".jpg");
                }
            }
        }
    }
}
