package team.AI.serviceIMP;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import team.AI.service.SenesitiveWordService;
import team.SensitiveWord.crawler.WebsiteProcessor;
import team.SensitiveWord.entity.UrlInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SensitiveWordServiceIMP implements SenesitiveWordService {
    ArrayList<UrlInfo> urlInfos ;
    Map<String,String> result= new HashMap<>();
    FileDowmLoadServiceIMP serviceIMP = new FileDowmLoadServiceIMP();
    private static Logger log = Logger.getLogger(SensitiveWordServiceIMP.class.getClass());

    @Override
    public int startCrawler(String url, int... type) {

        Runnable runner = new Runnable() {
            @Override
            public void run() {
                System.out.println("========================================");
                System.out.println("excute crawler ------>"+url);
                //获取结果信息
                urlInfos = WebsiteProcessor.StartCrawler(url, type);



            }
        };


        ScheduledExecutorService service  = Executors.newSingleThreadScheduledExecutor();
        //执行结束后的两分钟再次执行
        service.scheduleWithFixedDelay(runner,0,2, TimeUnit.MINUTES);
        //service.schedule(runner,10,TimeUnit.MILLISECONDS);
        return 0;
    }

    public String getFile(){
        if (null!=urlInfos&&urlInfos.size()!=0){
            FileDowmLoadServiceIMP serviceIMP= new FileDowmLoadServiceIMP();
            String name = serviceIMP.CreateFile(urlInfos);
            return name;
        }
        return null;
    }

    @Override
    public String getResult(ArrayList<UrlInfo> infolist) {
        //未爬取到信息
        if (infolist.size()==0){
            result.put("result","fail");
            result.put("info","请检查输入的url是否正确，或该站点禁制爬虫访问");
            System.out.println(JSONObject.toJSONString(result));
            return JSONObject.toJSONString(result);
        }else{
            return statisticsAll(infolist);
        }
    }

    /**
     *
     * @param infolist
     * @return
     */
    @Override
    public String statistics(ArrayList<UrlInfo> infolist) {
        int zero=0;
        int one=0;
        int two=0;
        int three=0;
        int four=0;
        int five=0;
        int total=0;
        /**
         * 0：暴恐敏感词
         * 1：反动敏感词
         * 2：色情敏感词
         * 3：其他敏感词
         * 4：民生敏感词
         * 5：自定义敏感词
         */
        for (UrlInfo info : infolist) {
            if (info.getHits()!="无敏感词"){
                System.out.println(info.getHits());
                if (info.getHits().contains("0")){
                    zero++;
                }if (info.getHits().contains("1")){
                    one++;
                }if (info.getHits().contains("2")){
                    two++;
                }if (info.getHits().contains("3")){
                    three++;
                }if (info.getHits().contains("4")){
                    four++;
                }if (info.getHits().contains("5")){
                    five++;
                }
            }
        }
        total=zero+one+two+three+four+five;
        result.put("data","["+zero+","+one+","+two+","+three+","+four+","+five+"]");
        result.put("file",serviceIMP.CreateFile(urlInfos));
        return JSONObject.toJSONString(result);
    }

    /**
     * 获取敏感词文件下载信息
     * @param infolist
     * @return
     */
    @Override
    public String statisticsAll(ArrayList<UrlInfo> infolist) {
        result.put("file",serviceIMP.CreateFile(urlInfos));
        return JSONObject.toJSONString(result);
    }


}