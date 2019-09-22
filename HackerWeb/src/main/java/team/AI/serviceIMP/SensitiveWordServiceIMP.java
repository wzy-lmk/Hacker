package team.AI.serviceIMP;

import com.alibaba.fastjson.JSONObject;
import team.AI.service.SenesitiveWordService;
import team.SensitiveWord.crawler.WebsiteProcessor;
import team.SensitiveWord.entity.UrlInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SensitiveWordServiceIMP implements SenesitiveWordService {

    @Override
    public String startCrawler(String url, int... type) {
        ArrayList<UrlInfo> urlInfos = WebsiteProcessor.StartCrawler(url, type);
        System.out.println("==========="+urlInfos);
        return  getResult(urlInfos);
    }

    @Override
    public String getResult(ArrayList<UrlInfo> infolist) {
        //未爬取到信息
        if (infolist.size()==0){
            Map result= new HashMap();
            result.put("result","fail");
            result.put("info","请检查输入的url是否正确，或该站点禁制爬虫访问");
            System.out.println(JSONObject.toJSONString(result));
            return JSONObject.toJSONString(result);
        }else{
            return statistics(infolist);
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
        return "["+zero+","+one+","+two+","+three+","+four+","+five+"]";
    }


}
