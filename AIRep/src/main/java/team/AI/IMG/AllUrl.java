package team.AI.IMG;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.List;

public class AllUrl {

    public void imgChange(String url) {
        DownloadIMG downloadIMG = new DownloadIMG();
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
                    downloadIMG.imgAddress(newUrl);

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

    public static void main(String[] args) {



//        InputStream iStream = DownloadIMG.class.getClassLoader().getResourceAsStream("downloadAddr.properties");
//        Properties properties = new Properties();
//        try {
//            properties.load(iStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String address=properties.getProperty("address");

        AllUrl allUrl = new AllUrl();
        allUrl.imgChange("http://www.chzu.edu.cn");
//        try {
//            FileOutputStream fos1 = new FileOutputStream(new File(address + ".zip"));
//            ZipUtils zipUtils = new ZipUtils();
//            Boolean toZip = zipUtils.toZip(address, fos1, true);
//            if (toZip) {
//                ImgMd5 imgMd5 = new ImgMd5();
//                String md5 = imgMd5.getMd5(new File(address + ".zip"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
