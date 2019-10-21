package team.AI.serviceIMP;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;

import team.AI.bean.HistroyAct;
import team.AI.bean.TaskInfo;
import team.AI.bean.UserBean;
import team.AI.service.SenesitiveWordService;
import team.AI.utils.DBUtiles;
import team.AI.utils.SendHtmlMail;
import team.AI.utils.TaskPool;
import team.SensitiveWord.crawler.WebsiteProcessor;
import team.SensitiveWord.entity.UrlInfo;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class SensitiveWordServiceIMP implements SenesitiveWordService {
    UserBean userinfo = null;
    ArrayList<UrlInfo> urlInfos;
    Map<String,String> result= new HashMap<>();
    FileDowmLoadServiceIMP serviceIMP = new FileDowmLoadServiceIMP();
    private TaskInfo taskinfo;
    private static Logger log = Logger.getLogger(SensitiveWordServiceIMP.class.getClass());
    Runnable runner=null;
    ScheduledExecutorService service  = Executors.newSingleThreadScheduledExecutor();
    QueryRunner queryRunner  = new QueryRunner(DBUtiles.getDataSource());
    ScheduledFuture<?> scheduledFuture=null;
    String taskid=null;

    /**
     * 开始执行任务
     *
     * 设置执行时间间隔
     * @param url
     * @param type
     * @return
     */
    @Override
    public int startCrawler(String url, int... type) {

         runner = new Runnable() {
            @Override
            public void run() {
                System.out.println("========================================");
                System.out.println("excute crawler ------>"+url);
                //获取结果信息
                urlInfos = WebsiteProcessor.StartCrawler(url, type);
                //发送邮箱
                SendMial(urlInfos);
                //次数加一
                taskinfo.setRunNumber(taskinfo.getRunNumber()+1);
                //更新任务状态
            }
        };
        TaskPool.addTask(taskid,runner,1);
        return 0;
    }


    public void SendMial(ArrayList<UrlInfo> urlInfos){

        Map<String,Map<String,Integer>> result = new HashMap<>();
        Map<String,Integer> map = null;
        for (UrlInfo urlInfo : urlInfos) {
            if (!urlInfo.getHits().equals("无敏感词")){
                int num=urlInfo.getHits().split(",").length;
                map = new HashMap<>();
                map.put(urlInfo.getHits(),num);
                result.put(urlInfo.getUrl(),map);
            }
        }

        SendHtmlMail.SendSenitiveTaskMail(result,taskinfo);
    }

    /**
     * 记录用户提交的任务
     * @param url 任务url
     * @param userinfo 用户信息
     * @param type  任务类型
     */
    public TaskInfo RecordingTask(String url, UserBean userinfo, String type){
        taskid = String.valueOf(System.currentTimeMillis());
        userinfo=userinfo;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String times = simpleDateFormat.format(new Date());
        taskinfo = new TaskInfo(type,taskid,times,userinfo.getEmail(),0,true,url);
        try {
            queryRunner.insert("insert into tasks (type,taskid,startTime,email,runNumber,isrun,taskurl) values(?,?,?,?,?,?,?)",
                    new ScalarHandler<>(),new Object[]{taskinfo.getType(),taskid,taskinfo.getStarttime(),taskinfo.getEmail(),taskinfo.getRunNumber(),taskinfo.isIsrun(),taskinfo.getTaskurl()});

            //获取id
            int id = queryRunner.query("select id form tasks where startTime=? and taskurl=?",new ScalarHandler<>(),new Object[]{taskinfo.getStarttime(),taskinfo.getTaskurl()});
            taskinfo.setId(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //历史记录
        HistroyAct histroyAct=new HistroyAct();
        histroyAct.setUser(userinfo.getName());
        Date date=new Date();
        String time = date.toLocaleString();
        histroyAct.setActtime(time);
        histroyAct.setActname("敏感词检测");
        histroyAct.setActcontent("检测网站  "+url);
        UserServiceIMP userServiceIMP=new UserServiceIMP();
        userServiceIMP.InsertHistroyinfo(histroyAct);
        return taskinfo;
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
     * 获取敏感词文件下载信息
     * @param infolist
     * @return
     */
    @Override
    public String statisticsAll(ArrayList<UrlInfo> infolist) {
        result.put("file",serviceIMP.CreateFile(urlInfos));
        return JSONObject.toJSONString(result);
    }

    /**
     * 更新任务状态
     */
    public void UpdateTask(){

        try {
            queryRunner.update("update tasks set type=?,startTime=?,email=?,runNumber=?,isrun=?,taskurl=? where id=?",
                    new ScalarHandler<>(),new Object[]{taskinfo.getType(),taskinfo.getStarttime(),taskinfo.getEmail(),taskinfo.getRunNumber(),taskinfo.isIsrun(),taskinfo.getTaskurl(),taskinfo.getId()});
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 停止任务
     */
    public void FinishTask(){
        scheduledFuture.cancel(true);
        taskinfo.setIsrun(false);
        UpdateTask();
    }
}