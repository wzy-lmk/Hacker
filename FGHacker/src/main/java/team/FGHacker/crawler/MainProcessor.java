package team.FGHacker.crawler;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
import com.sun.deploy.util.SessionState;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * @author 王智源
 * 爬取反共黑客网最新消息，并将结果发送至邮箱
 */
public class MainProcessor extends BreadthCrawler {

    private  static  ScheduledThreadPoolExecutor TaskPool =  new ScheduledThreadPoolExecutor(2,new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
    private  static Map<String,ScheduledFuture> tasks= new HashMap<String, ScheduledFuture>();
    /**
     * 构造一个基于RocksDB的爬虫
     * RocksDB文件夹为crawlPath，crawlPath中维护了历史URL等信息
     * 不同任务不要使用相同的crawlPath
     * 两个使用相同crawlPath的爬虫并行爬取会产生错误
     *
     * @param crawlPath RocksDB使用的文件夹
     * @param autoParse 是否根据设置的正则自动探测新URL
     */
    public MainProcessor(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        this.addSeed("http://www.fangongheike.com/");
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        String url = page.url();

        String time =page.select("#Blog1 > div.blog-posts.hfeed > div:nth-child(2) > div > div:nth-child(1) > div > div.post-footer > div.post-footer-line.post-footer-line-1 > span.post-timestamp > a > abbr").first().text();
        String title = page.select("#Blog1 > div.blog-posts.hfeed > div:nth-child(2) > div > div:nth-child(1) > div > h3").first().text();
        String content = page.selectText("#Blog1 > div.blog-posts.hfeed > div:nth-child(2) > div > div:nth-child(1) > div > div.post-body.entry-content");


        System.out.println("time---->"+time);
        System.out.println("title---->"+title);
        System.out.println("content---->"+content);

        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(SessionState.Client.class.getClassLoader().getResourceAsStream("newinfo.properties"),"GBK"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (properties.getProperty("time").equals(time)){
            System.out.println("未更新");
        }else {
            System.out.println("更新："+time+"---->"+title);
            properties.clear();
            properties.setProperty("title",title);
            properties.setProperty("content",content);
            properties.setProperty("time",time);
            try {
                FileOutputStream fos = new FileOutputStream("src/newinfo.properties");
                properties.store(fos,"Copyright (c) Boxcode Studio");
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    public static void CloseThread(String time){
//        if (null!=tasks.get(time)){
//            tasks.get(time).cancel(true);
//            System.out.println("@@@@@@@@@@@@@@@@@@@"+time+"取消@@@@@@@@");
//        }
//    }

    public static void StartFGMonitor(){
        final long time = System.currentTimeMillis();
        //设置爬虫线程
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    new MainProcessor("/website2",false).start(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        tasks.put(Long.toString(time), TaskPool.scheduleWithFixedDelay(runnable, 0, 1, TimeUnit.MINUTES));
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

//        service.scheduleWithFixedDelay(runnable, 0, 2, TimeUnit.MINUTES);
    }

    public static void main(String[] args) {
        StartFGMonitor();
    }
}
