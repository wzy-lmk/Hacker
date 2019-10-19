package team.AI.servlet;

import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import team.AI.IMG.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/CheckWebServlet")
public class CheckWebServlet extends HttpServlet {
    ArrayList arrayList = new ArrayList();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String url = request.getParameter("url");
        NewimgChange(url);
        String resultJson = JSONObject.toJSONString(arrayList);
        response.getWriter().print(resultJson);
    }

    public void NewimgChange(String url) {
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
                    System.out.println("加入成功");
                }else{
                    MD5Bean md5bean = md5IMP.NewUrlAndUrl(md5Bean);
                    if(md5bean.getMd5().equals(md5)){
                        System.out.println("没有改变");
                    }else{
                        System.out.println("newURL   "+newURL);
                        System.out.println("url   "+url);
                        System.out.println("md5   "+md5);
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
