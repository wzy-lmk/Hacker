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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet("/CheckWebServlet")
public class CheckWebServlet extends HttpServlet {
    ArrayList arrayList=new ArrayList();
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
        ThreadLocalClientFactory threadLocalClientFactory=new ThreadLocalClientFactory();
        WebClient webClient = threadLocalClientFactory.getWebClient();
        //WebClient webClient = new WebClient(BrowserVersion.CHROME);
        HtmlPage page = null;
        try {
            //获取HTML页面
            page = webClient.getPage(url);
            List<DomAttr> byXPath = page.getByXPath("//a/@href");
            for (DomAttr domAttr : byXPath) {
                newUrl=domAttr.getValue();
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




    public String  NewimgAddress(String url) {

        int i=0;
        DeleteFiles deleteFiles=new DeleteFiles();
        String address=null;
        InputStream iStream = CheckWebServlet.class.getClassLoader().getResourceAsStream("downloadAddr.properties");
        Properties properties = new Properties();
        try {
            properties.load(iStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        address=properties.getProperty("address");
        ThreadLocalClientFactory threadLocalClientFactory=new ThreadLocalClientFactory();
        WebClient webClient = threadLocalClientFactory.getWebClient();
        //实例化客户端
        //WebClient webClient = new WebClient(BrowserVersion.CHROME);
        DealUrl dealUrl=new DealUrl();
        HtmlPage page=null;
        String newURL=null;
        try {
            //获取HTML页面
            page = webClient.getPage(url);
            List<DomAttr> byXPath = page.getByXPath("//img/@src");
            for (DomAttr domAttr : byXPath) {
                deleteFiles.CreateFile(address+i);
                newURL=domAttr.getValue();
                try{
                    String dealU=dealUrl.getNetwork(url);
                    if(!dealUrl.isNet(domAttr.getValue())){
                        newURL=dealU+dealUrl.getUrl(domAttr.getValue());
                    }

                    URL u = new URL(newURL);
                    DataInputStream dataInputStream = new DataInputStream(u.openStream());
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(address+i+"/"+"img"+i+".jpg"));
                    i++;
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int length;

                    while ((length = dataInputStream.read(buffer)) > 0) {
                        output.write(buffer, 0, length);
                    }
                    fileOutputStream.write(output.toByteArray());
                    dataInputStream.close();
                    fileOutputStream.close();
                    MD5IMP md5IMP=new MD5IMP();
                    MD5Bean md5Bean=new MD5Bean();
                    md5Bean.setNewurl(url);
                    md5Bean.setUrl(newURL);
                    FileOutputStream fos1 = new FileOutputStream(new File(address+i+".zip"));
                    ZipUtils zipUtils=new ZipUtils();
                    Boolean toZip = zipUtils.toZip(address, fos1, true);
                    if(toZip){
                        ImgMd5 imgMd5=new ImgMd5();
                        String md5= imgMd5.getMd5(new File(address+i + ".zip"));
                        MD5Bean md5Bean1 = md5IMP.NewUrlAndUrl(md5Bean);
                        if(md5IMP.SelectNewUrlAndUrl(md5Bean)){
                            md5Bean.setMd5(md5);
                            md5IMP.InsertMD5(md5Bean);
                            System.out.println("加入成功");
                        }else{
                            if(!md5Bean1.getMd5().equals(md5)){
                                arrayList.add(newURL);
                                System.out.println("发生了改变");
                            }else{
                                System.out.println("没有改变");
                            }
                        }
                    }

                    deleteFiles.deleteDir(address+i);
                    deleteFiles.deleteDir(address+i+".zip");

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            webClient.getCurrentWindow().getJobManager().removeAllJobs();
            System.gc();
            webClient.close();
        }
        return address;
    }



}
