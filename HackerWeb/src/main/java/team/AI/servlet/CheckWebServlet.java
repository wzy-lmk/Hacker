package team.AI.servlet;

import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import team.AI.IMG.*;
import team.AI.bean.HistroyAct;
import team.AI.bean.TaskInfo;
import team.AI.bean.UserBean;
import team.AI.serviceIMP.TaskInfoServiceIMP;
import team.AI.serviceIMP.UserServiceIMP;
import team.AI.utils.SendHtmlMail;
import team.AI.utils.TaskPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/CheckWebServlet")
public class CheckWebServlet extends HttpServlet {
    ArrayList arrayList=new ArrayList();//存放arrayList1，传递到发送邮件
    ArrayList arrayList1=new ArrayList();//存放url、文件名称和时间
    TaskInfo taskInfo=new TaskInfo();//放入任务表
    int runNumber=0;//运行次数
    Boolean isrun=null;//运行状态
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String url = request.getParameter("url");
        HttpSession session = request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("userinfo");
        String title = "文件检测";
        Runnable runnable = new Runnable(){

            public void run() {
                isrun=true;
                NewimgChange(url,title,userBean);
                isrun=false;
                runNumber++;
                arrayList=null;
                arrayList1=null;
            }
        };
        String time=String.valueOf(System.currentTimeMillis());
        TaskPool.addTask(time,runnable,1);

        //将任务加入到task表中
        taskInfo.setTaskid(time);
        taskInfo.setType("网站监控");
        taskInfo.setEmail(userBean.getEmail());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String times = simpleDateFormat.format(new Date());
        taskInfo.setStarttime(times);
        taskInfo.setRunNumber(runNumber);
        taskInfo.setTaskurl(url);
        taskInfo.setIsrun(isrun);
        TaskInfoServiceIMP taskInfoServiceIMP=new TaskInfoServiceIMP();
        taskInfoServiceIMP.Insertinfo(taskInfo);
        String resultJson = JSONObject.toJSONString(taskInfo);
        try {
            response.getWriter().print(resultJson);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void NewimgChange(String url,String title,UserBean userBean) {
        arrayList=new ArrayList();
        DealUrl dealUrl = new DealUrl();
        String newUrl = url;
        //实例化客户端
        ThreadLocalClientFactory threadLocalClientFactory = new ThreadLocalClientFactory();
        WebClient webClient = threadLocalClientFactory.getWebClient();
        //WebClient webClient = new WebClient(BrowserVersion.CHROME);
        HtmlPage page = null;
        try {
            //获取HTML页面
            page = webClient.getPage(url);
            List<DomAttr> byXPath = page.getByXPath("//a/@href");
            for (DomAttr domAttr : byXPath) {
                newUrl = domAttr.getValue();
                boolean net1 = dealUrl.isNet(domAttr.getValue());
                if (!net1) {
                    newUrl = dealUrl.getNetwork(url) + dealUrl.getUrl(domAttr.getValue());
                }
                if (dealUrl.getNetwork(newUrl).equals(dealUrl.getNetwork(url))) {
                    NewimgAddress(newUrl);

                }
            }
            Date date=new Date();
            String time = date.toLocaleString();



            if(arrayList.size()==0){
                arrayList1.add(url);
                arrayList1.add("全部文件");
                arrayList1.add("没有改变");
                arrayList.add(arrayList1);
            }
            //发送邮件
            SendHtmlMail.sendFileCheckMail(title,time,arrayList,userBean.getEmail());
            //添加历史记录
            UserServiceIMP userServiceIMP=new UserServiceIMP();
            HistroyAct histroyAct=new HistroyAct();
            histroyAct.setUser(userBean.getName());
            histroyAct.setActcontent(title+"   "+url);
            histroyAct.setActname("网站监控");
            histroyAct.setActtime(time);
            userServiceIMP.InsertHistroyinfo(histroyAct);




        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            webClient.getCurrentWindow().getJobManager().removeAllJobs();
            System.gc();
            webClient.close();
        }
    }


    public String NewimgAddress(String url) {

        ThreadLocalClientFactory threadLocalClientFactory = new ThreadLocalClientFactory();
        WebClient webClient = threadLocalClientFactory.getWebClient();
        //实例化客户端
        DealUrl dealUrl = new DealUrl();
        HtmlPage page = null;
        String newURL = null;
        try {
            //获取HTML页面
            page = webClient.getPage(url);
            List<DomAttr> byXPath = page.getByXPath("//img/@src");
            for (DomAttr domAttr : byXPath) {
                newURL = domAttr.getValue();
                if (!dealUrl.isNet(domAttr.getValue())) {
                    newURL = dealUrl.getNetwork(url) + dealUrl.getUrl(domAttr.getValue());
                }
                MD5Bean md5Bean=new MD5Bean();
                md5Bean.setNewurl(newURL);
                md5Bean.setUrl(url);
                MD5IMP md5IMP=new MD5IMP();
                ImgMd5 imgMd5=new ImgMd5();
                String md5 = imgMd5.getMD5(newURL);
                if(md5IMP.SelectNewUrlAndUrl(md5Bean)){
                    md5Bean.setMd5(md5);
                    md5IMP.InsertMD5(md5Bean);
                    arrayList1.add(url);
                    String substring = newURL.substring(newURL.lastIndexOf('/')+1, newURL.length());
                    arrayList1.add(substring);
                    arrayList1.add("新增");
                    System.out.println("newurl   "+newURL);
                    System.out.println("url   "+url);
                    arrayList.add(arrayList1);
                    System.out.println("加入成功");
                }else{
                    MD5Bean md5bean = md5IMP.NewUrlAndUrl(md5Bean);
                    if(md5bean.getMd5().equals(md5)){
                        System.out.println("没有改变");
                    }else{
                        arrayList1.add(url);
                        String substring = newURL.substring(newURL.lastIndexOf('/')+1, newURL.length());
                        arrayList1.add(substring);
                        arrayList1.add("修改");
                        arrayList.add(arrayList1);
                        System.out.println("发生改变");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            webClient.getCurrentWindow().getJobManager().removeAllJobs();
            System.gc();
            webClient.close();
        }

        return null;
    }


}
