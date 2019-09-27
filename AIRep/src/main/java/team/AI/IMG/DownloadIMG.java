package team.AI.IMG;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.*;
import java.net.URL;
import java.util.*;

public class DownloadIMG {
    public  InputStream inStream = null;
    public  int i=0;
    public Map imgAddress(String url) {
        Map map=new HashMap();
        DeleteFiles deleteFiles=new DeleteFiles();
        String address=null;
        InputStream iStream = DownloadIMG.class.getClassLoader().getResourceAsStream("downloadAddr.properties");
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
                //deleteFiles.CreateFile(address);
                //newURL=domAttr.getValue();
                try{
                    String dealU=dealUrl.getNetwork(url);
                    if(!dealUrl.isNet(domAttr.getValue())){
                        newURL=dealU+dealUrl.getUrl(domAttr.getValue());
                    }

                        URL u = new URL(newURL);
                        DataInputStream dataInputStream = new DataInputStream(u.openStream());
                        FileOutputStream fileOutputStream = new FileOutputStream(new File(address+i+".jpg"));
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
                        Sample sample=new Sample();
                   ArrayList list = sample.PicText(address + i + ".jpg");
                    map.put(list,newURL);
                    File file = new File(address+i+".jpg");
                    deleteFiles.delete(file);

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
        return map;
    }




    public static void main(String[] args) throws FileNotFoundException {
//        InputStream iStream = DownloadIMG.class.getClassLoader().getResourceAsStream("downloadAddr.properties");
//        Properties properties = new Properties();
//        try {
//            properties.load(iStream);
//            String address=properties.getProperty("address");
//            DownloadIMG downloadIMG =new DownloadIMG();
            //String imgpath= downloadIMG.imgAddress("http://www.chzu.edu.cn",address);

            //FileOutputStream fos1 = new FileOutputStream(new File(imgpath+".zip"));
            //ZipUtils zipUtils=new ZipUtils();
            //Boolean toZip = zipUtils.toZip(imgpath, fos1, true);
            //if(toZip){
            //    ImgMd5 imgMd5=new ImgMd5();
             //   String md5 = imgMd5.getMd5(new File(imgpath + ".zip"));
            //}
            System.out.println("end");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
    }
}

